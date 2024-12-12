package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements RoomState {

    private RoomView roomView;
    private List<Painter> selectedPainters;
    private double previousMouseX;
    private double previousMouseY;

    public MoveState(RoomView roomView) {
        this.roomView = roomView;
        this.selectedPainters = new ArrayList<>();
        this.previousMouseX = -1;
        this.previousMouseY = -1;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        selectedPainters.clear();
        previousMouseX = -1;
        previousMouseY = -1;

        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                selectedPainters.add(painter);
            }
        }

        double zoomFactor = roomView.getZoomFactor();

        if (!selectedPainters.isEmpty()) {
            Painter nearestPainter = findNearestPainter(e.getPoint());
            RoomElement nearestElement = nearestPainter.getElement();
            previousMouseX = e.getX() / zoomFactor;
            previousMouseY = e.getY() / zoomFactor;
        } else {
            previousMouseX = e.getX() / zoomFactor;
            previousMouseY = e.getY() / zoomFactor;
        }

        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        double zoomFactor = roomView.getZoomFactor();

        if (previousMouseX >= 0 && previousMouseY >= 0) {
            double currentMouseX = e.getX() / zoomFactor;
            double currentMouseY = e.getY() / zoomFactor;

            double deltaX = currentMouseX - previousMouseX;
            double deltaY = currentMouseY - previousMouseY;

            boolean movementHalted = false;

            List<Painter> paintersToMove = selectedPainters.isEmpty() ? roomView.getPainters() : selectedPainters;

            for (Painter painter : paintersToMove) {
                RoomElement element = painter.getElement();

                int originalX = element.getX();
                int originalY = element.getY();

                element.setX((int) (originalX + deltaX));
                element.setY((int) (originalY + deltaY));

                boolean snapped = snapToEdge(element);
                boolean intersects = checkIntersection(element,10);

                if (snapped || intersects) {
                    element.setX(originalX);
                    element.setY(originalY);
                    movementHalted = true;
                    break;
                }
            }

            if (!movementHalted) {
                previousMouseX = currentMouseX;
                previousMouseY = currentMouseY;

                roomView.repaint();
            }
        }
    }




    private boolean snapToEdge(RoomElement element) {
        int roomWidth = roomView.getRoom().getWidth();
        int roomHeight = roomView.getRoom().getHeight();

        boolean snapped = false;
        int padding = 10;
        if (element.getX() < padding) {
            element.setX(10);
            snapped = true;
        }
        if (element.getY() < padding) {
            element.setY(10);
            snapped = true;
        }
        if (element.getX() + element.getWidth() > roomWidth-padding) {
            element.setX(roomWidth - element.getWidth());
            snapped = true;
        }
        if (element.getY() + element.getHeight() > roomHeight-padding) {
            element.setY(roomHeight - element.getHeight());
            snapped = true;
        }

        return snapped;
    }



    private boolean checkIntersection(RoomElement element, int padding) {
        Rectangle elementBounds = element.getBounds();

        for (Painter otherPainter : roomView.getPainters()) {
            RoomElement otherElement = otherPainter.getElement();

            if (element == otherElement) {
                continue;
            }

            Rectangle otherBounds = otherElement.getBounds();

            int dx = Math.max(0, Math.max(otherBounds.x - (elementBounds.x + elementBounds.width), elementBounds.x - (otherBounds.x + otherBounds.width)));
            int dy = Math.max(0, Math.max(otherBounds.y - (elementBounds.y + elementBounds.height), elementBounds.y - (otherBounds.y + otherBounds.height)));

            if (Math.sqrt(dx * dx + dy * dy) <= padding) {
                return true;
            }
        }
        return false;


    }


    private Painter findNearestPainter(Point cursorPoint) {
        Painter nearestPainter = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Painter painter : selectedPainters) {
            RoomElement element = painter.getElement();

            Point center = new Point(
                    element.getX() + element.getWidth() / 2,
                    element.getY() + element.getHeight() / 2
            );

            double distance = cursorPoint.distance(center);

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPainter = painter;
            }
        }

        return nearestPainter;
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        handleMouseClick(e);
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        selectedPainters.clear();
        previousMouseX = -1;
        previousMouseY = -1;
    }

    @Override
    public void enterState() {
        selectedPainters.clear();
        previousMouseX = -1;
        previousMouseY = -1;
    }

    @Override
    public void exitState() {
        selectedPainters.clear();
        previousMouseX = -1;
        previousMouseY = -1;
    }
}
