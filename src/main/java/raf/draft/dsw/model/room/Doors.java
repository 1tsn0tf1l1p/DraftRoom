package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Doors extends RoomElement {
    public Doors(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Doors(Doors doors) {
        super(doors);
    }

    public Doors() {
    }

    @Override
    public Prototype clone() {
        return new Doors(this);
    }
}
