package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.room.RoomView;

import java.util.HashMap;
import java.util.Map;

public class ResizeCommand implements Command {
    private RoomView roomView;
    private Map<RoomElement, int[]> initialSizes;
    private Map<RoomElement, int[]> newSizes;

    public ResizeCommand(RoomView roomView) {
        this.roomView = roomView;
        this.initialSizes = new HashMap<>();
        this.newSizes = new HashMap<>();
    }

    public void addElement(RoomElement element, int initialWidth, int initialHeight, int initialX, int initialY,
                           int newWidth, int newHeight, int newX, int newY) {
        initialSizes.put(element, new int[]{initialWidth, initialHeight, initialX, initialY});

        newSizes.put(element, new int[]{newWidth, newHeight, newX, newY});
    }

    @Override
    public void execute() {
        for (Map.Entry<RoomElement, int[]> entry : newSizes.entrySet()) {
            RoomElement element = entry.getKey();
            int[] newSize = entry.getValue();

            element.setSize(newSize[0], newSize[1]);
            element.setX(newSize[2]);
            element.setY(newSize[3]);
        }

        roomView.repaint();
    }

    @Override
    public void unExecute() {
        for (Map.Entry<RoomElement, int[]> entry : initialSizes.entrySet()) {
            RoomElement element = entry.getKey();
            int[] initialSize = entry.getValue();

            element.setSize(initialSize[0], initialSize[1]);
            element.setX(initialSize[2]);
            element.setY(initialSize[3]);
        }

        roomView.repaint();
    }
}