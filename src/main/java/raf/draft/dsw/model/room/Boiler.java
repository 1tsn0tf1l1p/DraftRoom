package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;
import raf.draft.dsw.model.structures.Room;

public class Boiler extends RoomElement {
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Boiler(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Boiler(Boiler bojler) {
        super(bojler.getIme(), bojler.getParent());
    }

    public Boiler() {
    }

    public Boiler(String newBoiler, Room room, int x, int y) {
        super(newBoiler, room, x, y);
    }

    @Override
    public Prototype clone() {
        return new Boiler(this);
    }
}
