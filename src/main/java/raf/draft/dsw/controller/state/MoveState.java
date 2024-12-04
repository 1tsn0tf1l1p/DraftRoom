package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements RoomState {

    private RoomView roomView;
    private List<Painter> selectedPainters;
    private int previousMouseX;
    private int previousMouseY;

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

        previousMouseX = e.getX();
        previousMouseY = e.getY();

        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (previousMouseX >= 0 && previousMouseY >= 0) {
            int deltaX = e.getX() - previousMouseX;
            int deltaY = e.getY() - previousMouseY;

            if (!selectedPainters.isEmpty()) {
                for (Painter painter : selectedPainters) {
                    RoomElement element = painter.getElement();

                    int originalX = element.getX();
                    int originalY = element.getY();

                    element.setX(element.getX() + deltaX);
                    element.setY(element.getY() + deltaY);

                    snapToEdge(element);

                    if (checkIntersection(element)) {
                        element.setX(originalX);
                        element.setY(originalY);
                    }
                }
            } else {
                roomView.getRoom().moveRoom(deltaX, deltaY);
                System.out.println("Pozvao se move Room");
            }

            previousMouseX = e.getX();
            previousMouseY = e.getY();

            roomView.repaint();
        }
    }


    private void snapToEdge(RoomElement element) {
        int roomWidth = roomView.getRoom().getWidth();
        int roomHeight = roomView.getRoom().getHeight();

        if (element.getX() < 10) element.setX(0);
        if (element.getY() < 10) element.setY(0);
        if (element.getX() + element.getWidth() > roomWidth - 10) {
            element.setX(roomWidth - element.getWidth());
        }
        if (element.getY() + element.getHeight() > roomHeight - 10) {
            element.setY(roomHeight - element.getHeight());
        }
    }


    private boolean checkIntersection(RoomElement element) {
        Rectangle elementBounds = element.getBounds();
        for (Painter otherPainter : roomView.getPainters()) {
            if (!selectedPainters.contains(otherPainter)) {
                RoomElement otherElement = otherPainter.getElement();
                if (elementBounds.intersects(otherElement.getBounds())) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void handleMousePressed(MouseEvent e) {
        handleMouseClick(e);
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
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
