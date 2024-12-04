package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Getter
public abstract class Painter {
    protected RoomElement element;
    @Setter
    protected boolean selected;

    public Painter(RoomElement element) {
        this.element = element;
    }

    public abstract void paint(Graphics2D g, RoomElement element);

    public abstract boolean elementAt(RoomElement element, Point pos);

    public abstract Rectangle2D getBounds();

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
