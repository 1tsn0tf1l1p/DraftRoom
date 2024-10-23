package raf.draft.dsw.controller.observer;

import java.util.ArrayList;

public interface IPublisher {
    public void addSubscriber(ISubscriber subscriber);
    public void removeSubscriber(ISubscriber subscriber);
    public void notifySubscribers(String object);

}
