package raf.draft.dsw.model.factory;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.room.*;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.room.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.Socket;

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
        if(type.equalsIgnoreCase("bathtub")) {
            return new Bathtub("New Bathtub", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("boiler")) {
            return new Boiler("New Boiler", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("doors")) {
            return new Doors("New Doors", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("sink")) {
            return new Sink("New Sink", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("table")) {
            return new Table("New Table", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("toiletBowl")) {
            return new ToiletBowl("New Toilet bowl", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("wardrobe")) {
            return new Wardrobe("New Wardrobe", room, e.getX(), e.getY());
        }
        if(type.equalsIgnoreCase("washingmachine")) {
            return new WashingMachine("New Washing machine", room, e.getX(), e.getY());
        }

        return null;
    }
    public Painter createPainter(DraftNode element){
        if (element instanceof Bed){
            return new BedPainter((RoomElement) element);
        }
        if (element instanceof Table){
            return new TablePainter((RoomElement) element);
        }
        if (element instanceof Sink){
            return new SinkPainter((RoomElement) element);
        }
        if (element instanceof Doors){
            return new DoorPainter((RoomElement) element);
        }
        if (element instanceof Wardrobe){
            return new WardrobePainter((RoomElement) element);
        }
        if (element instanceof WashingMachine){
            return new WashingMachinePainter((RoomElement) element);
        }
        if (element instanceof Boiler){
            return new BoilerPainter((RoomElement) element);
        }
        if (element instanceof Bathtub){
            return new BathtubPainter((RoomElement) element);
        }
        if (element instanceof ToiletBowl){
            return new ToiletBowlPainter((RoomElement) element);
        }
        return null;
    }
}
