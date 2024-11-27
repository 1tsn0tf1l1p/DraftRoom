package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public abstract class RoomElement extends DraftNode {
    private int x;
    private int y;
    private int width;
    private int height;
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public RoomElement(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
}
