package raf.draft.dsw.model.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.prototype.Prototype;

import java.awt.*;

@Getter
@Setter
public abstract class RoomElement extends DraftNode implements Prototype {
    private int x;
    private int y;
    private int width;
    private int height;
    private int rotateRatio;
    private int scaledX;
    private int scaledY;
    private int scaledWidth;
    private int scaledHeight;

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
        super(roomElement.getIme(), roomElement.getParent());
    }

    @Override
    public Prototype clone() {
        return null;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setScaledPosition(int scaledX, int scaledY) {
        this.scaledX = scaledX;
        this.scaledY = scaledY;
    }

    public void setScaledSize(int scaledWidth, int scaledHeight) {
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
    }

    public boolean isPointInResizeArea(Point point, int threshold) {
        int resizeX = this.scaledX + this.scaledWidth - threshold;
        int resizeY = this.scaledY + this.scaledHeight - threshold;

        return point.x >= resizeX && point.x <= (resizeX + threshold)
                && point.y >= resizeY && point.y <= (resizeY + threshold);
    }

}
