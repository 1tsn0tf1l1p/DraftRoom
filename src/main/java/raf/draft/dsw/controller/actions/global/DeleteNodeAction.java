package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.frames.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractRoomAction {
    DraftTreeImplementation tree;

    public DeleteNodeAction() {
        tree = ApplicationFramework.getInstance().getTree();
        putValue(SMALL_ICON, loadIcon("/images/deleteNode.png"));
        putValue(NAME, "Delete Node");
        putValue(SHORT_DESCRIPTION, "Remove the node from the Project Explorer");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem selectedItem = (TreeItem) tree.getTreeView().getLastSelectedPathComponent();
        if (selectedItem == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No node to be deleted.");
        } else {
            if (selectedItem.getNode() instanceof ProjectExplorer) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Cannot delete Project Explorer.");
                MainFrame.getInstance().getTabContainer().getTabbedPane().notifySubscribers("delete");
            } else {
                if (selectedItem.getNode() instanceof Project) {
                    ApplicationFramework.getInstance().getTabbedPaneController().clearTabs();
                    MainFrame.getInstance().getTabContainer().getTabbedPane().notifySubscribers("delete");
                }
                tree.removeChild(selectedItem);
                MainFrame.getInstance().getTabContainer().getTabbedPane().notifySubscribers("delete");
            }
        }
    }
}
