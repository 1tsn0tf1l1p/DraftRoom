package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.patterns.state.RoomState;
import raf.draft.dsw.view.commands.concrete_commands.DeleteCommand;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class DeleteState implements RoomState {
    private RoomView roomView;

    public DeleteState(RoomView roomView) {
        this.roomView = roomView;

        DeleteCommand deleteCommand = new DeleteCommand(roomView);
        roomView.getCommandManager().addCommand(deleteCommand);
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
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