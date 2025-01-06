package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Boiler extends RoomElement {
    public Boiler(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Boiler(Boiler boiler) {
        super(boiler);
    }

    public Boiler() {
    }

    @Override
    public Prototype clone() {
        return new Boiler(this);
    }
}
