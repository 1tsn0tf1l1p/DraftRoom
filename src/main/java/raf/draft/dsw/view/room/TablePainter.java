package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TablePainter extends Painter{
    public TablePainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        Graphics2D g2d = (Graphics2D) g.create();

        int centerX = scaledX + scaledWidth / 2;
        int centerY = scaledY + scaledHeight / 2;
        g2d.rotate(Math.toRadians(element.getRotateRatio()), centerX, centerY);

        if (selected) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.BLACK);
        }

        g2d.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);

        int platesize = Math.min(scaledWidth/5,scaledHeight/5);
        g2d.drawOval(scaledX+5, scaledY+5, platesize, platesize);
        g2d.drawOval(scaledX+ scaledWidth- platesize -5,  scaledY+5, platesize, platesize);
        g2d.drawOval(scaledX+5,  scaledY+scaledHeight-platesize-5, platesize, platesize);
        g2d.drawOval(scaledX+ scaledWidth- platesize -5,  scaledY+scaledHeight-platesize-5, platesize, platesize);

        g2d.dispose();
    }


    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        int elementLeft = element.getScaledX();
        int elementTop = element.getScaledY();
        int elementRight = elementLeft + element.getScaledWidth();
        int elementBottom = elementTop + element.getScaledHeight();
        return pos.x >= elementLeft && pos.x < elementRight && pos.y >= elementTop && pos.y < elementBottom;

    }

    @Override
    public Rectangle2D getBounds() {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        return new Rectangle2D.Double(scaledX, scaledY, scaledWidth, scaledHeight);
    }
}
