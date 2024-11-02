package raf.draft.dsw.controller.actions;

import lombok.Getter;
import raf.draft.dsw.controller.tree.DraftTreeImplementation;
import raf.draft.dsw.controller.tree.mvc.TreeItem;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.AboutUsFrame;
import raf.draft.dsw.gui.swing.RenameNodeFrame;
import raf.draft.dsw.gui.swing.RenameProjectFrame;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Project;

import java.awt.event.ActionEvent;
@Getter
public class RenameNodeAction extends AbstractRoomAction{

    public RenameNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeImplementation tree = ApplicationFramework.getInstance().getTree();
        TreeItem selectedItem = (TreeItem) tree.getTreeView().getLastSelectedPathComponent();

        if(selectedItem.getNode() instanceof Project) {
            RenameProjectFrame renameProjectFrame = new RenameProjectFrame((Project) selectedItem.getNode());
            renameProjectFrame.setVisible(true);
        }
        else {
            RenameNodeFrame renameNodeFrame = new RenameNodeFrame(selectedItem.getNode());
            renameNodeFrame.setVisible(true);
        }
    }
}
