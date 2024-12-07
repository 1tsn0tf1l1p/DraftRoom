package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class BedPainter extends Painter {
    private Dimension originalSize;
    public BedPainter(RoomElement element, Dimension originalSize) {
        super(element);
        this.originalSize = originalSize;
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        // Save the current transform
        Graphics2D g2d = (Graphics2D) g.create();

        // Apply rotation
        int centerX = scaledX + scaledWidth / 2;
        int centerY = scaledY + scaledHeight / 2;
        g2d.rotate(Math.toRadians(element.getRotateRatio()), centerX, centerY);

        // Draw the rectangle
        if (selected) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.BLACK);
        }

        g2d.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);

        // Draw the inner rectangle
        g2d.drawRect(scaledX + 5, scaledY + 5, scaledWidth - 10, scaledHeight / 10);

        // Restore the original transform
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