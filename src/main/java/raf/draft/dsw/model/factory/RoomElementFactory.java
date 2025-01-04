package raf.draft.dsw.model.factory;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.room.*;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.room.*;

import java.awt.event.MouseEvent;

public class RoomElementFactory {
    Room room;


    public RoomElementFactory(Room room) {
        this.room = room;
    }

    public RoomElement create(String type, MouseEvent e) {
        String name = findNextName(type);
        int x, y;
        if (e == null) {
            x = 0;
            y = 0;
        } else {
            x = e.getX();
            y = e.getY();
        }
        if (type.equalsIgnoreCase("Bed")) {
            return new Bed(name, room, x, y);
        }
        if (type.equalsIgnoreCase("bathtub")) {
            return new Bathtub(name, room, x, y);
        }
        if (type.equalsIgnoreCase("boiler")) {
            return new Boiler(name, room, x, y);
        }
        if (type.equalsIgnoreCase("doors")) {
            return new Doors(name, room, x, y);
        }
        if (type.equalsIgnoreCase("sink")) {
            return new Sink(name, room, x, y);
        }
        if (type.equalsIgnoreCase("table")) {
            return new Table(name, room, x, y);
        }
        if (type.equalsIgnoreCase("toiletBowl")) {
            return new ToiletBowl(name, room, x, y);
        }
        if (type.equalsIgnoreCase("wardrobe")) {
            return new Wardrobe(name, room, x, y);
        }
        if (type.equalsIgnoreCase("washingmachine")) {
            return new WashingMachine(name, room, x, y);
        }

        return null;
    }

    public Painter createPainter(DraftNode element) {
        if (element instanceof Bed) {
            return new BedPainter((RoomElement) element);
        }
        if (element instanceof Table) {
            return new TablePainter((RoomElement) element);
        }
        if (element instanceof Sink) {
            return new SinkPainter((RoomElement) element);
        }
        if (element instanceof Doors) {
            return new DoorPainter((RoomElement) element);
        }
        if (element instanceof Wardrobe) {
            return new WardrobePainter((RoomElement) element);
        }
        if (element instanceof WashingMachine) {
            return new WashingMachinePainter((RoomElement) element);
        }
        if (element instanceof Boiler) {
            return new BoilerPainter((RoomElement) element);
        }
        if (element instanceof Bathtub) {
            return new BathtubPainter((RoomElement) element);
        }
        if (element instanceof ToiletBowl) {
            return new ToiletBowlPainter((RoomElement) element);
        }
        return null;
    }

    private String findNextName(String type) {
        int counter = 1;
        String name;

        while (true) {
            name = "New " + type + " " + counter;
            boolean exists = false;
            for (DraftNode node : room.getChildren()) {
                if (node.getIme().equalsIgnoreCase(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                break;
            }
            counter++;
        }

        return name;
    }


}
