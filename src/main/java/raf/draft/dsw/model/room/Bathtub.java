package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;
import raf.draft.dsw.model.structures.Room;

public class Bathtub extends RoomElement {
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Bathtub(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Bathtub(Bathtub kada) {
        super(kada.getIme(), kada.getParent());
    }

    public Bathtub(String newBathtub, Room room, int x, int y) {
        super(newBathtub, room, x, y);
    }

    @Override
    public Prototype clone() {
        return new Bathtub(this);
    }
}
