package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements Command {
    private RoomView roomView;
    private List<Painter> deletedPainters;
    private List<TreeItem> deletedTreeItems;
    private DraftTreeImplementation treeImplementation;

    public DeleteCommand(RoomView roomView) {
        this.roomView = roomView;
        this.deletedPainters = new ArrayList<>();
        this.deletedTreeItems = new ArrayList<>();
        this.treeImplementation = ApplicationFramework.getInstance().getTree();
    }

    @Override
    public void execute() {
        List<Painter> paintersToRemove = new ArrayList<>();
        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                deletedPainters.add(painter);
                TreeItem treeItem = treeImplementation.returnTreeItemForRoom(painter.getElement());
                if (treeItem != null) {
                    deletedTreeItems.add(treeItem);
                    treeImplementation.removeChild(treeItem);
                }
                roomView.getRoom().removeChild(painter.getElement());
                paintersToRemove.add(painter);
            }
        }

        roomView.getPainters().removeAll(paintersToRemove);
        roomView.repaint();
    }

    @Override
    public void unExecute() {
        for (int i = 0; i < deletedPainters.size(); i++) {
            Painter painter = deletedPainters.get(i);
            painter.setSelected(false);
            TreeItem treeItem = deletedTreeItems.get(i);

            roomView.getRoom().addChild(painter.getElement());
            roomView.getPainters().add(painter);

            TreeItem parentItem = treeImplementation.returnTreeItemForRoom(roomView.getRoom());
            if (parentItem != null) {
                treeImplementation.addChild(parentItem, false, painter.getElement());
            }
        }

        SwingUtilities.updateComponentTreeUI(treeImplementation.getTreeView());
        roomView.repaint();
    }
}