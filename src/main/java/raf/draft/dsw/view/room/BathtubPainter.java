package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BathtubPainter extends Painter {
    public BathtubPainter(RoomElement element) {
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

        g2d.drawRoundRect(scaledX, scaledY, scaledWidth, scaledHeight,scaledX/5,scaledHeight/5);

        g2d.drawRoundRect(scaledX+scaledWidth/5, scaledY + scaledHeight/8, 3*scaledWidth/5, 3*scaledHeight/4, 3*scaledWidth/10,3*scaledHeight/8);

        g2d.dispose();
    }
}
