package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@Getter
public abstract class Painter {
    protected RoomElement element;
    @Setter
    protected boolean selected;

    public Painter(RoomElement element) {
        this.element = element;
    }

    public void paint(Graphics2D g, RoomElement element) {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();
        AffineTransform transform = new AffineTransform();

        int centerX = scaledX + scaledWidth / 2;
        int centerY = scaledY + scaledHeight / 2;

        transform.rotate(Math.toRadians(element.getRotateRatio()), centerX, centerY);
        g.transform(transform);
    }

    ;

    public boolean elementAt(RoomElement element, Point pos) {
        int elementLeft = element.getScaledX();
        int elementTop = element.getScaledY();
        int elementRight = elementLeft + element.getScaledWidth();
        int elementBottom = elementTop + element.getScaledHeight();
        return pos.x >= elementLeft && pos.x < elementRight && pos.y >= elementTop && pos.y < elementBottom;
    }

    public Rectangle2D getBounds() {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        return new Rectangle2D.Double(scaledX, scaledY, scaledWidth, scaledHeight);
    }

    public void drawSelection(Graphics2D g) {
        if (selected) {
            Rectangle2D bounds = getBounds();
            if (bounds != null) {
                g.setColor(Color.RED);
                g.setStroke(new BasicStroke(2));
                g.draw(bounds);
            }
        }
    }
}
