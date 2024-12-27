package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

public class AddCommand implements Command {
    private RoomView roomView;
    private RoomElement newElement;
    private Painter painter;

    public AddCommand(RoomView roomView, RoomElement newElement) {
        this.roomView = roomView;
        this.newElement = newElement;
        this.painter = roomView.getFactory().createPainter(newElement);
    }

    @Override
    public void execute() {
        roomView.getPainters().add(painter);
        roomView.getRoom().addChild(newElement);
        roomView.repaint();
    }

    @Override
    public void unExecute() {
        roomView.getPainters().remove(painter);
        roomView.getRoom().removeChild(newElement);
        roomView.repaint();
    }
}
