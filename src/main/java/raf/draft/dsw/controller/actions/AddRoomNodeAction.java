package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.tree.DraftTreeImplementation;
import raf.draft.dsw.controller.tree.mvc.TreeItem;
import raf.draft.dsw.core.ApplicationFramework;

import java.awt.event.ActionEvent;

public class AddRoomNodeAction extends AbstractRoomAction {
    DraftTreeImplementation tree;
    public AddRoomNodeAction() {
        tree = ApplicationFramework.getInstance().getTree();
        // putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "Add Room");
        putValue(SHORT_DESCRIPTION, "Add room to a Project or Building");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
