package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

public class BedPainter extends Painter {

    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        g.setColor(Color.BLUE);
        g.fillRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        g.drawString(element.getIme(), element.getX() + 20, element.getY() + element.getHeight() / 2);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        int x = element.getX();
        int y = element.getY();
        int width = element.getWidth();
        int height = element.getHeight();

        return pos.x >= x && pos.x <= x + width && pos.y >= y && pos.y <= y + height;
    }
}
