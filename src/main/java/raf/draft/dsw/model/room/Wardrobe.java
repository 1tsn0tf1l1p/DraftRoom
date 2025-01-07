package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Wardrobe extends RoomElement {
    public Wardrobe(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Wardrobe(Wardrobe wardrobe) {
        super(wardrobe);
    }

    public Wardrobe() {
    }

    @Override
    public Prototype clone() {
        return new Wardrobe(this);
    }
}
