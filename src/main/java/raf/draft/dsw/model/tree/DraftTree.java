package raf.draft.dsw.model.tree;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.patterns.observer.IPublisher;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;

/**
 * The DraftTree interface extends IPublisher and provides methods to generate a tree structure,
 * add and remove child nodes, and manage the selected item in the tree.
 */
public interface DraftTree extends IPublisher {
    /**
     * Generates a JTree based on the given project tree.
     *
     * @param projectExplorer the project tree to generate the tree from
     * @return the generated JTree
     */
    JTree generateTree(ProjectExplorer projectExplorer);

    /**
     * Adds a child node to the specified parent node.
     *
     * @param parent the parent node to add the child to
     * @param isRoom flag indicating if the child is a room
     */
    void addChild(TreeItem parent, boolean isRoom, RoomElement roomElement);

    /**
     * Removes the specified child node.
     *
     * @param node the child node to be removed
     */
    void removeChild(TreeItem node);

    /**
     * Gets the currently selected item in the tree.
     *
     * @return the currently selected item
     */
    TreeItem getSelectedItem();

    /**
     * Sets the specified node as the selected item in the tree.
     *
     * @param node the node to be set as the selected item
     */
    void setSelectedItem(DraftNode node);
}