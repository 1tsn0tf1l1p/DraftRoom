package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

public class BedPainter extends Painter {
    private Dimension originalSize; // Keep track of the original size of the canvas

    public BedPainter(RoomElement element, Dimension originalSize) {
        super(element);
        this.originalSize = originalSize;
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {
        // Use the scaled position and dimensions
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        // Draw the scaled bed
        g.setColor(Color.BLUE);
        g.fillRect(scaledX, scaledY, scaledWidth, scaledHeight);
        g.setColor(Color.BLACK);
        g.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);

        // Draw the element name
        g.drawString(element.getIme(), scaledX + 20, scaledY + scaledHeight / 2);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        // Check if the point lies within the scaled rectangle
        Dimension currentSize = Toolkit.getDefaultToolkit().getScreenSize();
        double scaleX = currentSize.getWidth() / originalSize.getWidth();
        double scaleY = currentSize.getHeight() / originalSize.getHeight();

        int x = (int) (element.getX() * scaleX);
        int y = (int) (element.getY() * scaleY);
        int width = (int) (element.getWidth() * scaleX);
        int height = (int) (element.getHeight() * scaleY);

        return pos.x >= x && pos.x <= x + width && pos.y >= y && pos.y <= y + height;
    }
}