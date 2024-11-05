package raf.draft.dsw.controller.observer;

public interface ISubscriber {
    <T> void update(T t);

}
