package raf.draft.dsw.model.tree;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.observer.IPublisher;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;

public interface DraftTree extends IPublisher {
    JTree generateTree(ProjectExplorer projectExplorer);

    void addChild(TreeItem parent, boolean isRoom);

    void removeChild(TreeItem node);

    TreeItem getSelectedItem();

    void setSelectedItem(DraftNode node);

}
