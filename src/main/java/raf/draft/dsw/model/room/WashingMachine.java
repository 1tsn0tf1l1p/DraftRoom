package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class WashingMachine extends RoomElement {
    public WashingMachine(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public WashingMachine(WashingMachine washingMachine) {
        super(washingMachine);
    }

    public WashingMachine() {
    }

    @Override
    public Prototype clone() {
        return new WashingMachine(this);
    }
}
