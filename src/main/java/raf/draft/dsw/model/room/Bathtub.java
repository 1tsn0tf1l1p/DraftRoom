package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Bathtub extends RoomElement {
    public Bathtub(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Bathtub(Bathtub bathtub) {
        super(bathtub);
    }

    public Bathtub() {
    }

    @Override
    public Prototype clone() {
        return new Bathtub(this);
    }
}
