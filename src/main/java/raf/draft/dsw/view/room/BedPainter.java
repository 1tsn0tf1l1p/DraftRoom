package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

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
        System.out.println(scaledX);
        System.out.println(scaledY);
        System.out.println(element.getX());
        System.out.println(element.getY());
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        g.setColor(Color.BLUE);
        g.setColor(Color.BLACK);
        g.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);
        g.drawRect(scaledX+5,scaledY+5,scaledWidth-10,scaledHeight/10);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
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