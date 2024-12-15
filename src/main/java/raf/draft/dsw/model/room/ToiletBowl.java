package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;
import raf.draft.dsw.model.structures.Room;

public class ToiletBowl extends RoomElement {
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public ToiletBowl(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public ToiletBowl(ToiletBowl wcSolja) {
        super(wcSolja.getIme(), wcSolja.getParent());
    }

    public ToiletBowl(String newToiletBowl, Room room, int x, int y) {
        super(newToiletBowl, room, x, y);
    }

    @Override
    public Prototype clone() {
        return new ToiletBowl(this);
    }
}
