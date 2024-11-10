package raf.draft.dsw.model.nodes;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The DraftNodeComposite class is an abstract class that extends DraftNode.
 * It represents a composite node in a draft, containing a list of child nodes.
 */
@Getter
public abstract class DraftNodeComposite extends DraftNode {

    private List<DraftNode> children;

    /**
     * Constructs a DraftNodeComposite with the specified name and parent.
     * Initializes the list of child nodes.
     *
     * @param ime the name of the node
     * @param parent the parent node
     */
    public DraftNodeComposite(String ime, DraftNodeComposite parent) {
        super(ime, parent);
        this.children = new ArrayList<>();
    }

    /**
     * Adds a child node to the list of children.
     *
     * @param child the child node to be added
     */
    public void addChild(DraftNode child) {
        children.add(child);
    }

    /**
     * Removes a child node from the list of children.
     *
     * @param child the child node to be removed
     */
    public void removeChild(DraftNode child) {
        children.remove(child);
    }
}