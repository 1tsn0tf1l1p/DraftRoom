package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.patterns.state.RoomState;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.commands.concrete_commands.MoveCommand;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class MoveState implements RoomState {

    private RoomView roomView;
    private List<Painter> selectedPainters;
    private double previousMouseX;
    private double previousMouseY;
    Map<RoomElement, int[]> initialPositions = new HashMap<>();

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
                boolean intersects = checkIntersection(element, 10);

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
        int padding = 10;
        boolean snapped = false;

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(element.getRotateRatio()),
                element.getX() + element.getWidth() / 2.0,
                element.getY() + element.getHeight() / 2.0);

        Point2D[] points = {
                transform.transform(new Point2D.Double(element.getX(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX(), element.getY() + element.getHeight()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY() + element.getHeight()), null)
        };

        double minX = Arrays.stream(points).mapToDouble(Point2D::getX).min().orElse(0);
        double maxX = Arrays.stream(points).mapToDouble(Point2D::getX).max().orElse(0);
        double minY = Arrays.stream(points).mapToDouble(Point2D::getY).min().orElse(0);
        double maxY = Arrays.stream(points).mapToDouble(Point2D::getY).max().orElse(0);

        int rotatedWidth = (int) (maxX - minX);
        int rotatedHeight = (int) (maxY - minY);

        if (minX < padding) {
            element.setX(padding);
            snapped = true;
        }
        if (minY < padding) {
            element.setY(padding);
            snapped = true;
        }
        if (maxX > roomWidth - padding) {
            element.setX(roomWidth - rotatedWidth);
            snapped = true;
        }
        if (maxY > roomHeight - padding) {
            element.setY(roomHeight - rotatedHeight);
            snapped = true;
        }

        return snapped;
    }


    private boolean checkIntersection(RoomElement element, int padding) {
        Rectangle rotatedBounds = getRotatedBounds(element);

        for (Painter otherPainter : roomView.getPainters()) {
            RoomElement otherElement = otherPainter.getElement();
            if (element == otherElement) continue;

            Rectangle otherRotatedBounds = getRotatedBounds(otherElement);

            if (rotatedBounds.intersects(new Rectangle(
                    otherRotatedBounds.x - padding,
                    otherRotatedBounds.y - padding,
                    otherRotatedBounds.width + 2 * padding,
                    otherRotatedBounds.height + 2 * padding
            ))) {
                return true;
            }
        }

        return false;
    }

    private Rectangle getRotatedBounds(RoomElement element) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(element.getRotateRatio()),
                element.getX() + element.getWidth() / 2.0,
                element.getY() + element.getHeight() / 2.0);

        Point2D[] points = {
                transform.transform(new Point2D.Double(element.getX(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX(), element.getY() + element.getHeight()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY() + element.getHeight()), null)
        };

        double minX = Arrays.stream(points).mapToDouble(Point2D::getX).min().orElse(0);
        double maxX = Arrays.stream(points).mapToDouble(Point2D::getX).max().orElse(0);
        double minY = Arrays.stream(points).mapToDouble(Point2D::getY).min().orElse(0);
        double maxY = Arrays.stream(points).mapToDouble(Point2D::getY).max().orElse(0);

        return new Rectangle((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
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
        initialPositions = new HashMap<>();
        if (!selectedPainters.isEmpty()) {

            for (Painter painter : selectedPainters) {
                RoomElement element = painter.getElement();
                initialPositions.put(element, new int[]{element.getX(), element.getY()});
            }
        }

    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        if (!selectedPainters.isEmpty()) {

            List<RoomElement> movedElements = new ArrayList<>();

            for (Painter painter : selectedPainters) {
                RoomElement element = painter.getElement();
                movedElements.add(element);
            }

            MoveCommand moveCommand = new MoveCommand(roomView, movedElements, initialPositions);
            roomView.getCommandManager().addCommand(moveCommand);
        }

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
