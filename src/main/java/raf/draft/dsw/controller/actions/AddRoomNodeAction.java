package raf.draft.dsw.controller.actions;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.frames.CreateRoomFrame;

import java.awt.event.ActionEvent;

public class AddRoomNodeAction extends AbstractRoomAction {
    private DraftTreeImplementation tree;
    private CreateRoomFrame roomFrame;

    public AddRoomNodeAction() {
        tree = ApplicationFramework.getInstance().getTree();
        putValue(SMALL_ICON, loadIcon("/images/addApartment.png"));
        putValue(NAME, "Add Room");
        putValue(SHORT_DESCRIPTION, "Add room to a Project or Building");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem selectedItem = (TreeItem) tree.getTreeView().getLastSelectedPathComponent();
        if (selectedItem == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No node is selected.");
        } else {
            if (selectedItem.getNode() instanceof Project || selectedItem.getNode() instanceof Building) {
                tree.addChild(selectedItem, true);
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Cannot instance Room object outside of Project");
            }
        }
    }
}
