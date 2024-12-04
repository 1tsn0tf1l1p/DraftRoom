package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.frames.CreateRoomFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EditRoomState implements RoomState {
    private RoomView roomView;
    public EditRoomState(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        CreateRoomFrame createRoomFrame = new CreateRoomFrame(roomView.getRoom());
        createRoomFrame.setVisible(true);
        createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                roomView.getRoomRectangle().repaint();
                if (roomView.getRoom().getWidth()!=0) roomView.changeState(new SelectState(roomView));
            }
        });

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
    public void handleMouseRelease(MouseEvent e) {

    }

    @Override
    public void enterState() {

    }

    @Override
    public void exitState() {

    }
}
