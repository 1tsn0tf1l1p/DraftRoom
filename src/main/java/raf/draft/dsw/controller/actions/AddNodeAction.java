package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.tree.DraftTreeImplementation;
import raf.draft.dsw.controller.tree.mvc.TreeItem;
import raf.draft.dsw.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddNodeAction extends AbstractRoomAction {
    DraftTreeImplementation tree;
    public AddNodeAction() {
        tree = ApplicationFramework.getInstance().getTree();
        putValue(SMALL_ICON, loadIcon("/images/addNode.png"));
        putValue(NAME, "Add Node");
        putValue(SHORT_DESCRIPTION, "Add node to the Project Explorer");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem selectedItem = (TreeItem) tree.getTreeView().getLastSelectedPathComponent();
        tree.addChild(selectedItem);
    }
}
