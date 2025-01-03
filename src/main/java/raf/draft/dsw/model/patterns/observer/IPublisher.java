package raf.draft.dsw.model.patterns.observer;

/**
 * The IPublisher interface provides methods to manage subscribers and notify them with updates.
 */
public interface IPublisher {
    /**
     * Adds a subscriber to the list of subscribers.
     *
     * @param subscriber the subscriber to be added
     */
    void addSubscriber(ISubscriber subscriber);

    /**
     * Removes a subscriber from the list of subscribers.
     *
     * @param subscriber the subscriber to be removed
     */
    void removeSubscriber(ISubscriber subscriber);

    /**
     * Notifies all subscribers with the given message.
     *
     * @param t   the message to be sent to subscribers
     * @param <T> the type of the message
     */
    <T> void notifySubscribers(T t);
}