package raf.draft.dsw.controller.actions.global;

import lombok.Getter;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.frames.RenameNodeFrame;
import raf.draft.dsw.view.frames.RenameProjectFrame;

import java.awt.event.ActionEvent;

@Getter
public class RenameNodeAction extends AbstractRoomAction {

    public RenameNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeImplementation tree = ApplicationFramework.getInstance().getTree();
        TreeItem selectedItem = (TreeItem) tree.getTreeView().getLastSelectedPathComponent();

        if (selectedItem == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No node selected.");
            return;
        }

        if (selectedItem.getNode() instanceof Project) {
            RenameProjectFrame renameProjectFrame = new RenameProjectFrame((Project) selectedItem.getNode());
            renameProjectFrame.setVisible(true);
        } else if (selectedItem.getNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Cannot rename Project Explorer!");
        } else {
            RenameNodeFrame renameNodeFrame = new RenameNodeFrame(selectedItem.getNode());
            renameNodeFrame.setVisible(true);
        }
    }
}
