package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SelectState implements RoomState {
    private RoomView roomView;

    public SelectState(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {

    }

    @Override
    public void handleMouseDrag(MouseEvent e) {

    }

    @Override
    public void handleKeyPress(KeyEvent e) {

    }

    @Override
    public void enterState() {

    }

    @Override
    public void exitState() {

    }
}
