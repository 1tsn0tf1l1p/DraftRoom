package raf.draft.dsw.controller.logger;

import raf.draft.dsw.model.logger.Logger;

public class ConsoleLogger implements Logger {

    @Override
    public <T> void update(T t) {
        System.out.println(t);
    }
}
