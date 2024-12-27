package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.room.RoomView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveCommand implements Command {
    private RoomView roomView;
    private Map<RoomElement, int[]> previousPositions;
    private Map<RoomElement, int[]> newPositions;

    public MoveCommand(RoomView roomView, List<RoomElement> movedElements, Map<RoomElement, int[]> initialPositions) {
        this.roomView = roomView;
        this.previousPositions = new HashMap<>(initialPositions);

        this.newPositions = new HashMap<>();
        for (RoomElement element : movedElements) {
            newPositions.put(element, new int[]{element.getX(), element.getY()});
        }
    }

    @Override
    public void execute() {
        for (Map.Entry<RoomElement, int[]> entry : newPositions.entrySet()) {
            RoomElement element = entry.getKey();
            int[] newPos = entry.getValue();

            element.setX(newPos[0]);
            element.setY(newPos[1]);
        }

        roomView.repaint();
    }

    @Override
    public void unExecute() {
        for (Map.Entry<RoomElement, int[]> entry : previousPositions.entrySet()) {
            RoomElement element = entry.getKey();
            int[] oldPos = entry.getValue();

            element.setX(oldPos[0]);
            element.setY(oldPos[1]);
        }

        roomView.repaint();
    }
}