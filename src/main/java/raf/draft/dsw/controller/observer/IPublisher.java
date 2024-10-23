package raf.draft.dsw.controller.observer;

public interface IPublisher {
    public void addSubscriber(ISubscriber subscriber);
    public void removeSubscriber(ISubscriber subscriber);
    public void notifySubscribers(Object object);


}
