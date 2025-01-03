package raf.draft.dsw.model.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

import java.awt.*;

/**
 * The `RoomElement` class represents an abstract base for elements within a room in the application.
 * It extends `DraftNode` and implements the `Prototype` interface, allowing room elements to function
 * as nodes in a tree structure and to be cloned using the Prototype design pattern.
 *
 * <p>
 * This class provides properties for defining position, dimensions, scaling, and rotation of elements.
 * It also includes utility methods for resizing, positioning, and determining interaction areas.
 * </p>
 */
@Getter
@Setter
public abstract class RoomElement extends DraftNode implements Prototype {

    @JsonProperty("x")
    private int x;

    @JsonProperty("y")
    private int y;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("rotateRatio")
    private int rotateRatio;

    private int scaledX;

    private int scaledY;

    private int scaledWidth;

    private int scaledHeight;

    /**
     * Constructs a `RoomElement` with the specified name and parent node.
     *
     * @param ime    the name of the element.
     * @param parent the parent node of the element.
     */
    public RoomElement(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    /**
     * Default constructor for Jackson (required for deserialization).
     */
    public RoomElement() {
    }

    /**
     * Constructs a `RoomElement` by copying the properties of another `RoomElement`.
     * Adjusts the position of the new element to avoid overlapping with the original.
     *
     * @param roomElement the `RoomElement` to copy.
     */
    public RoomElement(RoomElement roomElement) {
        super(roomElement.getIme(), roomElement.getParent());
        this.setWidth(roomElement.getWidth());
        this.setHeight(roomElement.getHeight());
        this.setX(roomElement.getX() + roomElement.getWidth() + 10);
        this.setY(roomElement.getY() + 10);
        this.setScaledSize(roomElement.getScaledWidth(), roomElement.getScaledHeight());
        this.setScaledX(roomElement.getScaledX());
        this.setScaledY(roomElement.getScaledY());
    }

    /**
     * Constructs a `RoomElement` with the specified name, parent, and position.
     *
     * @param ime    the name of the element.
     * @param parent the parent node of the element.
     * @param x      the X-coordinate of the element's position.
     * @param y      the Y-coordinate of the element's position.
     */
    public RoomElement(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent);
        setX(x);
        setY(y);
    }

    /**
     * Creates a clone of this `RoomElement`.
     * Subclasses are expected to override this method to provide specific cloning logic.
     *
     * @return a cloned instance of this `RoomElement`.
     */
    @Override
    public Prototype clone() {
        return null;
    }

    /**
     * Sets the size of the element.
     *
     * @param width  the width of the element.
     * @param height the height of the element.
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the position of the element.
     *
     * @param x the X-coordinate of the element's position.
     * @param y the Y-coordinate of the element's position.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the scaled position of the element.
     *
     * @param scaledX the X-coordinate of the element's scaled position.
     * @param scaledY the Y-coordinate of the element's scaled position.
     */
    public void setScaledPosition(int scaledX, int scaledY) {
        this.scaledX = scaledX;
        this.scaledY = scaledY;
    }

    /**
     * Sets the scaled size of the element.
     *
     * @param scaledWidth  the scaled width of the element.
     * @param scaledHeight the scaled height of the element.
     */
    public void setScaledSize(int scaledWidth, int scaledHeight) {
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
    }

    /**
     * Determines whether a given point is within the resize area of the element.
     * The resize area is located at the bottom-right corner of the element, within a specified threshold.
     *
     * @param point     the point to check.
     * @param threshold the threshold for the resize area.
     * @return true if the point is within the resize area; false otherwise.
     */
    public boolean isPointInResizeArea(Point point, int threshold) {
        int resizeX = this.scaledX + this.scaledWidth - threshold;
        int resizeY = this.scaledY + this.scaledHeight - threshold;

        return point.x >= resizeX && point.x <= (resizeX + threshold)
                && point.y >= resizeY && point.y <= (resizeY + threshold);
    }

    /**
     * Returns the bounding rectangle of the element, based on its position and size.
     *
     * @return a `Rectangle` representing the bounds of the element.
     */
    @JsonIgnore
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}