package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Wardrobe extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Wardrobe(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
    public Wardrobe(Wardrobe ormar) {
        super(ormar.getIme(), ormar.getParent());
    }

    @Override
    public Prototype clone() {
        return new Wardrobe(this);
    }
}
