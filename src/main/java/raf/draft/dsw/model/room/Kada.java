package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Kada extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Kada(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Kada(Kada kada) {
        super(kada.getIme(), kada.getParent());
    }

    @Override
    public Prototype clone() {
        return new Kada(this);
    }
}
