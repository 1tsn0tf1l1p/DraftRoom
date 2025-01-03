package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

public class AddCommand implements Command {
    private RoomView roomView;
    private RoomElement newElement;
    private Painter painter;
    private DraftTreeImplementation tree;

    public AddCommand(RoomView roomView, RoomElement newElement) {
        this.roomView = roomView;
        this.newElement = newElement;
        this.tree = ApplicationFramework.getInstance().getTree();
        this.painter = roomView.getFactory().createPainter(newElement);
    }

    @Override
    public void execute() {
        roomView.getPainters().add(painter);
        painter.setSelected(false);
        roomView.getRoom().addChild(newElement);
        TreeItem treeItem = tree.returnTreeItemForRoom(roomView.getRoom());
        tree.addChild(treeItem, false, newElement);
        roomView.repaint();
        roomView.setProjectChanged();
    }

    @Override
    public void unExecute() {
        TreeItem item = tree.returnTreeItemForRoom(painter.getElement());
        tree.removeChild(item);
        roomView.getRoom().removeChild(painter.getElement());
        roomView.getPainters().remove(painter);
        roomView.repaint();
    }
}