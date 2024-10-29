package raf.draft.dsw.controller.messagegenerator;

import java.io.Console;

public class ConsoleLogger implements Logger {

    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
