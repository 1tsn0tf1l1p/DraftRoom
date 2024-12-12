package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.frames.CreateRoomFrame;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EditState implements RoomState {
    private RoomView roomView;

    public EditState(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                CreateRoomFrame createRoomFrame = new CreateRoomFrame(painter.getElement());
                createRoomFrame.setVisible(true);
                createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        roomView.repaint();
                    }
                });
            }
        }
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {

    }

    @Override
    public void handleMousePressed(MouseEvent e) {

    }

    @Override
    public void handleKeyPress(KeyEvent e) {

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
}
