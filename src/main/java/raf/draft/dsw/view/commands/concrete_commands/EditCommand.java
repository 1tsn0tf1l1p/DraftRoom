package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.frames.CreateRoomFrame;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

public class EditCommand implements Command {
    private RoomView roomView;
    private Painter painter;
    private RoomElement previousState;
    private RoomElement newState;

    public EditCommand(RoomView roomView, Painter painter) {
        this.roomView = roomView;
        this.painter = painter;

        this.previousState = (RoomElement) painter.getElement().clone();
        this.newState = null;
    }

    @Override
    public void execute() {

        this.previousState = (RoomElement) painter.getElement().clone();
        if (newState != null) {
            painter.getElement().setWidth(newState.getWidth());
            painter.getElement().setHeight(newState.getHeight());
            painter.getElement().setRotateRatio(newState.getRotateRatio());

            roomView.repaint();
            roomView.setProjectChanged();
        } else {
            CreateRoomFrame createRoomFrame = new CreateRoomFrame(painter.getElement());
            createRoomFrame.setVisible(true);

            createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    newState = (RoomElement) painter.getElement().clone();
                    roomView.repaint();
                    roomView.setProjectChanged();
                }
            });
        }

    }

    @Override
    public void unExecute() {
        if (previousState != null) {
            painter.getElement().setWidth(previousState.getWidth());
            painter.getElement().setHeight(previousState.getHeight());
            painter.getElement().setRotateRatio(previousState.getRotateRatio());

            roomView.repaint();
        }
    }
}