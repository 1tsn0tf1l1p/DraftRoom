package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;

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
        super.g = g;
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        System.out.println(scaledX);
        System.out.println(scaledY);
        System.out.println(element.getX());
        System.out.println(element.getY());
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        if (selected) g.setColor(Color.BLUE);
        else g.setColor(Color.BLACK);
        g.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);
        g.drawRect(scaledX+5,scaledY+5,scaledWidth-10,scaledHeight/10);
    }

    @Override
    public boolean elementAt(RoomElement element, Point pos) {
        int elementLeft = element.getScaledX();
        int elementTop = element.getScaledY();
        int elementRight = elementLeft + element.getScaledWidth();
        int elementBottom = elementTop + element.getScaledHeight();
        return pos.x >= elementLeft && pos.x < elementRight && pos.y >= elementTop && pos.y < elementBottom;
    }


}