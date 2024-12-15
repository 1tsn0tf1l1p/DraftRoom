package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DoorPainter extends Painter {
    public DoorPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        Graphics2D g2d = (Graphics2D) g.create();

        super.paint(g2d,element);
        if (selected) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.BLACK);
        }
        g2d.drawArc(scaledX+scaledWidth/2,scaledY,scaledWidth,scaledHeight*2,90,90);

        g2d.drawLine(scaledX+scaledWidth,scaledY,scaledX+scaledWidth,scaledY+ scaledHeight);
        g2d.dispose();
    }
}
