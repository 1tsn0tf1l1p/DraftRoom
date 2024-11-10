package raf.draft.dsw.model.observer;

public interface ISubscriber {
    <T> void update(T t);

}
