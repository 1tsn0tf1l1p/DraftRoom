package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

public class SinkPainter extends Painter {

    public SinkPainter(RoomElement element) {
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
        super.paint(g2d, element);

        if (selected) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.BLACK);
        }
        g2d.drawLine(scaledX, scaledY, scaledX + scaledWidth, scaledY);
        g2d.drawLine(scaledX, scaledY, centerX, scaledY + scaledHeight);
        g2d.drawLine(scaledX + scaledWidth, scaledY, centerX, scaledY + scaledHeight);

        int circleSize = Math.min(scaledWidth / 4, scaledHeight / 4);
        g2d.drawOval(centerX - circleSize / 2, centerY - circleSize, circleSize, circleSize);
        g2d.dispose();
    }

}
