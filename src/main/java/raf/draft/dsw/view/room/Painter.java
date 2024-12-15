package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.room.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * The `Painter` class serves as an abstract base for rendering visual representations
 * of `RoomElement` objects within the `raf.draft.dsw` application. It provides utility
 * methods for painting elements, determining if a point is within an element's bounds,
 * retrieving bounding rectangles, and highlighting selected elements.
 *
 * <p>
 * This class relies on the properties of the `RoomElement` to calculate positions,
 * dimensions, and transformations. Subclasses are expected to extend this class
 * to implement specific painting logic for different types of room elements.
 * </p>
 */
@Getter
public abstract class Painter {

    /**
     * The `RoomElement` associated with this painter. Used to obtain the element's
     * properties such as position, size, and rotation.
     */
    protected RoomElement element;

    /**
     * A flag indicating whether the element is currently selected.
     * If true, a visual selection highlight will be drawn around the element.
     */
    @Setter
    protected boolean selected;

    /**
     * Constructs a new `Painter` for the given `RoomElement`.
     *
     * @param element the `RoomElement` this painter will render.
     */
    public Painter(RoomElement element) {
        this.element = element;
    }

    /**
     * Paints the associated `RoomElement` using the given `Graphics2D` object.
     * This includes applying transformations such as rotation based on the element's properties.
     *
     * @param g       the `Graphics2D` object used for rendering.
     * @param element the `RoomElement` to be painted.
     */
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

    /**
     * Determines whether a given point lies within the bounds of the `RoomElement`.
     *
     * @param element the `RoomElement` to check against.
     * @param pos     the point to check.
     * @return true if the point is within the element's bounds; false otherwise.
     */
    public boolean elementAt(RoomElement element, Point pos) {
        int elementLeft = element.getScaledX();
        int elementTop = element.getScaledY();
        int elementRight = elementLeft + element.getScaledWidth();
        int elementBottom = elementTop + element.getScaledHeight();
        return pos.x >= elementLeft && pos.x < elementRight && pos.y >= elementTop && pos.y < elementBottom;
    }

    /**
     * Returns the bounding rectangle of the associated `RoomElement`.
     *
     * @return a `Rectangle2D` representing the bounds of the element.
     */
    public Rectangle2D getBounds() {
        int scaledX = element.getScaledX();
        int scaledY = element.getScaledY();
        int scaledWidth = element.getScaledWidth();
        int scaledHeight = element.getScaledHeight();

        return new Rectangle2D.Double(scaledX, scaledY, scaledWidth, scaledHeight);
    }

    /**
     * Draws a visual indicator (e.g., a red border) around the associated
     * `RoomElement` if it is selected.
     *
     * @param g the `Graphics2D` object used for rendering.
     */
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
