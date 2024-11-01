package raf.draft.dsw.controller.messagegenerator;

import java.io.Console;

public class ConsoleLogger implements Logger {

    @Override
    public <T> void update(T t)
        {
        System.out.println(t);
    }
}
