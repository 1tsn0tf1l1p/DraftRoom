package raf.draft.dsw.controller.observer;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    <T> void notifySubscribers(T t);

}
