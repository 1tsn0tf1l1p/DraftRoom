package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;
import raf.draft.dsw.model.structures.Room;

public class WashingMachine extends RoomElement {
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public WashingMachine(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public WashingMachine() {
    }

    public WashingMachine(WashingMachine vesMasina) {
        super(vesMasina.getIme(), vesMasina.getParent());
    }

    public WashingMachine(String newWashingMachine, Room room, int x, int y) {
        super(newWashingMachine, room, x, y);
    }

    @Override
    public Prototype clone() {
        return new WashingMachine(this);
    }
}
