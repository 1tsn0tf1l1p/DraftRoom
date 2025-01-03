package raf.draft.dsw.view.commands.concrete_commands;

import raf.draft.dsw.model.patterns.command.Command;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotateRightCommand implements Command {
    private RoomView roomView;
    private Map<RoomElement, Integer> previousRotations;
    private Map<RoomElement, Integer> newRotations;

    public RotateRightCommand(RoomView roomView) {
        this.roomView = roomView;
        this.previousRotations = new HashMap<>();
        this.newRotations = new HashMap<>();

        List<Painter> painters = roomView.getPainters();
        for (Painter painter : painters) {
            if (painter.isSelected()) {
                RoomElement element = painter.getElement();

                int currentRotation = element.getRotateRatio();
                previousRotations.put(element, currentRotation);

                int newRotation = (currentRotation + 90) % 360;
                newRotations.put(element, newRotation);
            }
        }
    }

    @Override
    public void execute() {
        for (Map.Entry<RoomElement, Integer> entry : newRotations.entrySet()) {
            RoomElement element = entry.getKey();
            int newRotation = entry.getValue();
            element.setRotateRatio(newRotation);
        }

        roomView.repaint();
        roomView.setProjectChanged();
    }

    @Override
    public void unExecute() {
        for (Map.Entry<RoomElement, Integer> entry : previousRotations.entrySet()) {
            RoomElement element = entry.getKey();
            int previousRotation = entry.getValue();
            element.setRotateRatio(previousRotation);
        }

        roomView.repaint();
    }
}