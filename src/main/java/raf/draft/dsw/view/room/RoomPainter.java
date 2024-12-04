package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RoomPainter extends Painter {
    public RoomPainter(RoomElement element) {
        super(element);
        this.selected=false;
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        int x = element.getX();
        int y = element.getY();
        g.setColor(Color.BLUE);
        g.fillRect(x, y, element.getWidth(), element.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(x, y, element.getWidth(), element.getHeight());
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        int x = element.getX();
        int y = element.getY();

        return pos.x >= x && pos.x <= x + element.getWidth() && pos.y >= y && pos.y <= y + element.getHeight();
    }

    @Override
    public Rectangle2D getBounds() {
        return null;
    }
}
