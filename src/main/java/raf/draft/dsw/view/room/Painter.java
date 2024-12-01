package raf.draft.dsw.view.room;

import lombok.Getter;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.Graphics2D;
import java.awt.Point;

@Getter
public abstract class Painter {
    protected RoomElement element;

    public Painter(RoomElement element) {
        this.element=element;
    }

    public abstract void paint(Graphics2D g, RoomElement element);

    public abstract boolean elementAt(RoomElement element, Point pos);


}