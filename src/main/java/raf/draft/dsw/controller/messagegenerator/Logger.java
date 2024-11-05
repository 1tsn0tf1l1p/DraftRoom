package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.ISubscriber;

public interface Logger extends ISubscriber {
    @Override
    <T> void update(T t);
}
