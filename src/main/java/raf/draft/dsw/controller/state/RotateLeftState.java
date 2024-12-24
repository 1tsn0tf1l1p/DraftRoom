package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

public class RotateLeftState implements RoomState {
    private RoomView roomView;

    public RotateLeftState(RoomView roomView) {
        this.roomView = roomView;
        rotateSelectedItems();
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        rotateSelectedItems();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            rotateSelectedItems();
        }
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
    }

    @Override
    public void enterState() {
    }

    @Override
    public void exitState() {
    }

    private void rotateSelectedItems() {
        List<Painter> painters = roomView.getPainters();

        for (Painter painter : painters) {
            if (painter.isSelected()) {
                RoomElement element = (RoomElement) painter.getElement();

                int currentRotation = element.getRotateRatio();
                int newRotation = (currentRotation - 90) % 360;
                element.setRotateRatio(newRotation);
            }
        }

        roomView.repaint();
    }
}
