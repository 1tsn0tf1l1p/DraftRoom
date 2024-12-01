package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.Graphics2D;
import java.awt.Point;

@Getter
public abstract class Painter {
    protected RoomElement element;
    protected Graphics2D g;
    @Setter
    protected boolean selected;

    public Painter(RoomElement element) {
        this.element=element;
    }

    public abstract void paint(Graphics2D g, RoomElement element);

    public abstract boolean elementAt(RoomElement element, Point pos);


}