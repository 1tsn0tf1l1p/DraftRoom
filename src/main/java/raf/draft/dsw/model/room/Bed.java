package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Bed extends RoomElement {
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Bed(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Bed(Bed bed) {
        super(bed);
    }

    public Bed() {
    }

    @Override
    public Prototype clone() {
        return new Bed(this);
    }
}
