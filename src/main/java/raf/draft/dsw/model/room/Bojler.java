package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class Bojler extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public Bojler(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    public Bojler(Bojler bojler) {
        super(bojler.getIme(), bojler.getParent());
    }

    @Override
    public Prototype clone() {
        return new Bojler(this);
    }
}
