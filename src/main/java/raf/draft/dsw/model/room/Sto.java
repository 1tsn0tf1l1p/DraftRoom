package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Sto extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Sto(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
    public Sto(Sto sto) {
        super(sto.getIme(), sto.getParent());
    }

    @Override
    public Prototype clone() {
        return new Sto(this);
    }
}
