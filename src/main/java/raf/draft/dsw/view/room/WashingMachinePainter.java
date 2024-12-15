package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

public class WashingMachinePainter extends Painter {
    public WashingMachinePainter(RoomElement element) {
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

        g2d.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);
        int circleSize = Math.min(3 * scaledWidth / 5, 3 * scaledHeight / 5);
        g2d.drawOval(centerX - circleSize / 2, centerY - circleSize / 2, circleSize, circleSize);
        g2d.dispose();
    }
}
