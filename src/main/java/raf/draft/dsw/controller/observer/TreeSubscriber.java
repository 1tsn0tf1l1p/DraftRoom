package raf.draft.dsw.controller.observer;

public interface TreeSubscriber {
    <T> void TreeUpdate(T t);
}
