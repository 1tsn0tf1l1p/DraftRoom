package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WardrobePainter extends Painter {
    public WardrobePainter(RoomElement element) {
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
        g2d.drawLine(scaledX+scaledWidth/2, scaledY, scaledX+scaledWidth/2, scaledY+scaledHeight);

        int circleSize = scaledWidth/8;

        g2d.drawOval(scaledX+scaledWidth/3 - circleSize/2, centerY-circleSize/2, circleSize, circleSize);
        g2d.drawOval(scaledX+2*scaledWidth/3 - circleSize/2, centerY-circleSize/2, circleSize, circleSize);

        g2d.dispose();
    }
}
