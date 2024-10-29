package raf.draft.dsw.controller.observer;

import raf.draft.dsw.controller.observer.TreeSubscriber;

public interface TreePublisher {
    void addSubscriber(TreeSubscriber subscriber);
    void removeSubscriber(TreeSubscriber subscriber);
    <T> void notify(T t);
}
