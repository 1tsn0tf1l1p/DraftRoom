package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ToiletBowlPainter extends Painter {

    public ToiletBowlPainter(RoomElement element) {
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
        g2d.drawRect(scaledX, scaledY, scaledWidth, scaledHeight/5);
        int afterRecY = scaledY + scaledHeight / 5;
        g2d.drawArc(scaledX, afterRecY-scaledHeight, scaledWidth, scaledHeight*2-scaledHeight/5, 180,180 );

        g2d.drawLine(scaledX+scaledWidth/5, scaledY-scaledHeight/5 + scaledHeight/2, scaledX+scaledWidth-scaledWidth/5, scaledY-scaledHeight/5 + scaledHeight/2);
        g2d.drawArc(scaledX+scaledWidth/5, scaledY-scaledHeight/5, 3*scaledWidth/5, scaledHeight, 180,180 );

        g2d.dispose();
    }

}
