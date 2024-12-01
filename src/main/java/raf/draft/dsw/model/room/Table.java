package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Table extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Table(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
    public Table(Table sto) {
        super(sto.getIme(), sto.getParent());
    }

    @Override
    public Prototype clone() {
        return new Table(this);
    }
}
