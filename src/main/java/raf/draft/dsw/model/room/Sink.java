package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Sink extends RoomElement {
    public Sink(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Sink(Sink sink) {
        super(sink);
    }

    public Sink() {
    }

    @Override
    public Prototype clone() {
        return new Sink(this);
    }
}
