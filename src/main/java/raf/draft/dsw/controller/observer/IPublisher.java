package raf.draft.dsw.controller.observer;

import java.util.ArrayList;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    <T> void notifySubscribers(T t);

}
