package raf.draft.dsw.controller.messagegenerator;

public class ConsoleLogger implements Logger {

    @Override
    public <T> void update(T t)
        {
        System.out.println(t);
    }
}
