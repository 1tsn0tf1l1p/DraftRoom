package raf.draft.dsw.model.logger;

import raf.draft.dsw.model.observer.ISubscriber;

public interface Logger extends ISubscriber {
    @Override
    <T> void update(T t);
}
