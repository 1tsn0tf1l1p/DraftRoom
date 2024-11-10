package raf.draft.dsw.model.nodes;

import lombok.Getter;

import java.util.Objects;

/**
 * The DraftNode class is an abstract class that represents a node in a draft.
 * It contains a name and a reference to its parent node.
 */
@Getter
public abstract class DraftNode {
    private String ime;
    private DraftNodeComposite parent;

    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime the name of the node
     * @param parent the parent node
     */
    public DraftNode(String ime, DraftNodeComposite parent) {
        this.ime = ime;
        this.parent = parent;
    }

    /**
     * Sets the name of the node.
     *
     * @param ime the new name of the node
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Sets the parent of the node.
     *
     * @param parent the new parent of the node
     */
    public void setParent(DraftNodeComposite parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DraftNode draftNode = (DraftNode) object;
        return Objects.equals(ime, draftNode.ime) && Objects.equals(parent, draftNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, parent);
    }
}