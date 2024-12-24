package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;
import raf.draft.dsw.model.structures.Room;

public class Doors extends RoomElement {
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Doors(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Doors(Doors vrata) {
        super(vrata.getIme(), vrata.getParent());
    }

    public Doors(String newDoors, Room room, int x, int y) {
        super(newDoors, room, x, y);
    }

    @Override
    public Prototype clone() {
        return new Doors(this);
    }
}
