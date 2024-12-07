package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;
import raf.draft.dsw.model.structures.Room;

public class Sink extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Sink(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Sink(Sink lavabo) {
        super(lavabo.getIme(), lavabo.getParent());
    }

    public Sink(String newSink, Room room, int x, int y) {
        super(newSink,room,x,y);
    }

    @Override
    public Prototype clone() {
        return new Sink(this);
    }
}
