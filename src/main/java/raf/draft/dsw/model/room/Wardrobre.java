package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Wardrobre extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Wardrobre(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
    public Wardrobre(Wardrobre ormar) {
        super(ormar.getIme(), ormar.getParent());
    }

    @Override
    public Prototype clone() {
        return new Wardrobre(this);
    }
}
