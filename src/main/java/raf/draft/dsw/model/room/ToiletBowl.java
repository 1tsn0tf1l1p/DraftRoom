package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

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

    @Override
    public Prototype clone() {
        return new ToiletBowl(this);
    }
}
