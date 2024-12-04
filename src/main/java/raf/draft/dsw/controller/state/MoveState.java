package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
        if (!selectedPainters.isEmpty() && previousMouseX >= 0 && previousMouseY >= 0) {
            int deltaX = e.getX() - previousMouseX;
            int deltaY = e.getY() - previousMouseY;

            for (Painter painter : selectedPainters) {
                RoomElement element = painter.getElement();
                element.setX(element.getX() + deltaX);
                element.setY(element.getY() + deltaY);
            }

            previousMouseX = e.getX();
            previousMouseY = e.getY();

            roomView.repaint();
        }
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