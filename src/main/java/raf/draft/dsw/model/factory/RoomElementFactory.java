package raf.draft.dsw.model.factory;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.room.BedPainter;
import raf.draft.dsw.view.room.Painter;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RoomElementFactory {
    Room room;
    Dimension originalSize;

    public RoomElementFactory(Room room, Dimension originalSize) {
        this.room = room;
        this.originalSize = originalSize;
    }

    public RoomElement create(String type, MouseEvent e) {
        if(type.equalsIgnoreCase("Bed")) {
            return new Bed("New Bed", room, e.getX(), e.getY());
        }
        return null;
    }
    public Painter createPainter(DraftNode element){
        if (element instanceof Bed){
            return new BedPainter((RoomElement) element, originalSize);
        }
        return null;
    }
}
