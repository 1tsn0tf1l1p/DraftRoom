package raf.draft.dsw.view.commands.concrete_commands;

import lombok.Getter;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CopyPasteCommand implements Command {
    private RoomView roomView;
    private DraftTreeImplementation treeImplementation;
    private List<RoomElement> copiedElements;
    private List<Painter> copiedPainters;

    public CopyPasteCommand(RoomView roomView) {
        this.roomView = roomView;
        this.treeImplementation = ApplicationFramework.getInstance().getTree();
        this.copiedElements = new ArrayList<>();
        this.copiedPainters = new ArrayList<>();
    }

    public void addCopiedElement(RoomElement roomElement, Painter painter) {
        copiedElements.add(roomElement);
        copiedPainters.add(painter);
    }

    @Override
    public void execute() {
        for (int i = 0; i < copiedElements.size(); i++) {
            RoomElement roomElement = copiedElements.get(i);
            Painter painter = copiedPainters.get(i);

            roomView.getRoom().addChild(roomElement);
            roomView.getPainters().add(painter);

            TreeItem parentItem = treeImplementation.returnTreeItemForRoom(roomView.getRoom());
            if (parentItem != null) {
                treeImplementation.addChild(parentItem, false, roomElement);
            }
        }

        roomView.repaint();
    }

    @Override
    public void unExecute() {
        for (int i = 0; i < copiedElements.size(); i++) {
            RoomElement roomElement = copiedElements.get(i);
            Painter painter = copiedPainters.get(i);

            roomView.getRoom().removeChild(roomElement);
            roomView.getPainters().remove(painter);

            TreeItem treeItem = treeImplementation.returnTreeItemForRoom(roomElement);
            if (treeItem != null) {
                treeImplementation.removeChild(treeItem);
            }
        }

        roomView.repaint();
    }
}