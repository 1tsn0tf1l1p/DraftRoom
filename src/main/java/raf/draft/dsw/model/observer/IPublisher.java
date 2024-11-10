package raf.draft.dsw.model.observer;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);

    void removeSubscriber(ISubscriber subscriber);

    <T> void notifySubscribers(T t);

}
