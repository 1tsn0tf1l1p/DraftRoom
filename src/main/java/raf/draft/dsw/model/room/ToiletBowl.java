package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class ToiletBowl extends RoomElement {
    public ToiletBowl(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public ToiletBowl(ToiletBowl toiletBowl) {
        super(toiletBowl);
    }

    public ToiletBowl() {
    }

    @Override
    public Prototype clone() {
        return new ToiletBowl(this);
    }
}
