package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Sink extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Sink(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Sink(Sink lavabo) {
        super(lavabo.getIme(), lavabo.getParent());
    }

    @Override
    public Prototype clone() {
        return new Sink(this);
    }
}
