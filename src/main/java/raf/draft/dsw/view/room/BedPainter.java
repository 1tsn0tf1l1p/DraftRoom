package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

public class BedPainter extends Painter{

    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        g.setColor(Color.BLUE);
        g.fillRect(100, 100, 150, 80); // Example dimensions
        g.setColor(Color.BLACK);
        g.drawRect(100, 100, 150, 80);
        g.drawString("Bed", 120, 140);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        return false;
    }
}
