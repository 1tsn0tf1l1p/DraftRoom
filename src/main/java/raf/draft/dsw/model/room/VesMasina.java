package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

public class VesMasina extends RoomElement{
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public VesMasina(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
    public VesMasina(VesMasina vesMasina) {
        super(vesMasina.getIme(), vesMasina.getParent());
    }

    @Override
    public Prototype clone() {
        return new VesMasina(this);
    }
}
