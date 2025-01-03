package raf.draft.dsw.view.commands;

import raf.draft.dsw.model.patterns.command.Command;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void addCommand(Command command) {
        undoStack.push(command);
        redoStack.clear();
        command.execute();
    }

    public void undoCommand() {
        if (canUndo()) {
            Command command = undoStack.pop();
            command.unExecute();
            redoStack.push(command);
        }
    }

    public void redoCommand() {
        if (canRedo()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}
