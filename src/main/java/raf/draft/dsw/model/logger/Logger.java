package raf.draft.dsw.model.logger;

import raf.draft.dsw.model.observer.ISubscriber;

/**
 * The Logger interface extends the ISubscriber interface.
 * It provides a method to update the logger with a given message.
 */
public interface Logger extends ISubscriber {
    /**
     * Updates the logger with the given message.
     *
     * @param t   the message to be logged
     * @param <T> the type of the message
     */
    @Override
    <T> void update(T t);
}