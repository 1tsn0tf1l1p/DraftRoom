package raf.draft.dsw.model.factory;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.room.BedPainter;
import raf.draft.dsw.view.room.Painter;

import java.awt.event.MouseEvent;

public class RoomElementFactory {
    Room room;

    public RoomElementFactory(Room room) {
        this.room = room;
    }

    public RoomElement create(String type, MouseEvent e) {
        if(type.equalsIgnoreCase("Bed")) {
            return new Bed("Dimijeva kolevka za lepe tete", room, e.getX(), e.getY());
        }
        return null;
    }
    public Painter createPainter(DraftNode element){
        if (element instanceof Bed){
            return new BedPainter((RoomElement) element);
        }
        return null;
    }
}
