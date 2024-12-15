package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter
@Setter
public class BedPainter extends Painter {
    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, RoomElement element) {

        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        Graphics2D g2d = (Graphics2D) g.create();
        super.paint(g2d,element);
        if (selected) {
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.BLACK);
        }

        g2d.drawRect(scaledX, scaledY, scaledWidth, scaledHeight);
        g2d.drawRect(scaledX + scaledWidth / 6, scaledY + scaledHeight / 10, 2 * scaledWidth / 3, scaledHeight / 10);

        g2d.dispose();
    }

}