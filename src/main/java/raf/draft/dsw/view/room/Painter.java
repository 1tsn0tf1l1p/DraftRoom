package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.Graphics2D;
import java.awt.Point;


/**
 * ElementPainter je apstraktna klasa koja deklariše metode za iscrtavnje Diagram
 * elementa i detekciju pogotka
 * @author Filip C.
 *
 */
public abstract class Painter {

    public Painter(RoomElement element) {}

    public abstract void paint(Graphics2D g, RoomElement element);

    public abstract boolean elementAt(RoomElement element, Point pos);


}