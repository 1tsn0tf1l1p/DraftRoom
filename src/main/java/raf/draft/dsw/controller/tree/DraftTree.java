package raf.draft.dsw.controller.tree;

import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.tree.mvc.TreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;

public interface DraftTree extends IPublisher {
    JTree generateTree(ProjectExplorer projectExplorer);
    void addChild(TreeItem parent, boolean isRoom);
    void removeChild(TreeItem node);
    void setSelectedItem(DraftNode node);
    TreeItem getSelectedItem(DraftNode node);

}
