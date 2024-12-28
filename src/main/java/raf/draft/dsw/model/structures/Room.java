package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.observer.IPublisher;
import raf.draft.dsw.model.patterns.observer.ISubscriber;
import raf.draft.dsw.model.room.RoomElement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The `Room` class represents a composite node that acts as a container for room elements.
 * It implements the Observer pattern to notify subscribers of changes in the room's state.
 */
@Setter
@Getter
public class Room extends DraftNodeComposite implements IPublisher {

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("x")
    private int x;

    @JsonProperty("y")
    private int y;
    @JsonIgnore
    private List<ISubscriber> subscribers;

    /**
     * Constructs a `Room` with the specified name and parent node.
     *
     * @param ime    the name of the room.
     * @param parent the parent node of the room.
     */
    public Room(String ime, DraftNodeComposite parent) {
        super(ime, parent);
        this.subscribers = new CopyOnWriteArrayList<>();
        this.width = 0;
        this.height = 0;
        this.x = 0;
        this.y = 0;
    }

    // Default constructor for Jackson
    public Room() {
        this.subscribers = new CopyOnWriteArrayList<>();
        this.width = 0;
        this.height = 0;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Adds a subscriber to the list of subscribers.
     *
     * @param subscriber the subscriber to add.
     */
    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    /**
     * Removes a subscriber from the list of subscribers.
     *
     * @param subscriber the subscriber to remove.
     */
    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notifies all subscribers of changes in the room's state.
     *
     * @param t the object containing the updated state.
     * @param <T> the type of the update message.
     */
    @Override
    public <T> void notifySubscribers(T t) {
        subscribers.forEach(e -> e.update(t));
    }

    /**
     * Sets the name of the room and notifies subscribers of the change.
     *
     * @param ime the new name of the room.
     */
    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubscribers(this);
    }

    /**
     * Sets the size of the room.
     *
     * @param width  the width of the room.
     * @param height the height of the room.
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Moves all elements in the room by a specified delta.
     *
     * @param deltaX the amount to move the room along the X-axis.
     * @param deltaY the amount to move the room along the Y-axis.
     */
    public void moveRoom(int deltaX, int deltaY) {
        for (DraftNode child : this.getChildren()) {
            if (child instanceof RoomElement) {
                RoomElement element = (RoomElement) child;
                element.setX(element.getX() + deltaX);
                element.setY(element.getY() + deltaY);
            }
        }
        notifySubscribers(this);
    }
}