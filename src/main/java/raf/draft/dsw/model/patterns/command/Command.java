package raf.draft.dsw.model.patterns.command;

public interface Command {
    void execute();
    void unExecute();
}
