package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

public class BoilerPainter extends Painter {
    public BoilerPainter(RoomElement element) {
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
        super.paint(g2d, element);
        if (selected) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.BLACK);
        }


        g2d.drawOval(scaledX, scaledY, scaledWidth, scaledHeight);
        int size = Math.min(scaledWidth / 5, scaledHeight / 5);
        g2d.drawLine(centerX - size, centerY - size, centerX + size, centerY + size);
        g2d.drawLine(centerX + size, centerY - size, centerX - size, centerY + size);

        g2d.dispose();
    }
}
