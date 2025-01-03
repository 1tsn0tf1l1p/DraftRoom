package raf.draft.dsw.model.patterns.observer;

/**
 * The ISubscriber interface provides a method to update the subscriber with a given message.
 */
public interface ISubscriber {
    /**
     * Updates the subscriber with the given message.
     *
     * @param t   the message to be updated
     * @param <T> the type of the message
     */
    <T> void update(T t);
}