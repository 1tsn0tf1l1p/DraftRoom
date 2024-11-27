package raf.draft.dsw.model.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

@Getter
@Setter
public abstract class RoomElement extends DraftNode implements Prototype {
    private int x;
    private int y;
    private int width;
    private int height;
    private int rotateRatio;
    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public RoomElement(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }
    public RoomElement(RoomElement roomElement) {
        super (roomElement.getIme(),roomElement.getParent());
    }

}
