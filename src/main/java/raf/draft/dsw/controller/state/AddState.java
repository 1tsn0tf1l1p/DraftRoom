package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.factory.RoomElementFactory;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.frames.CreateRoomFrame;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AddState implements RoomState {
    private Dimension originalSize;
    private RoomView roomView;
    private RoomElementFactory factory;
    public AddState(RoomView roomView) {
        this.roomView = roomView;
        this.originalSize = roomView.getOriginalSize();
        this.factory = roomView.getFactory();
    }
    @Override
    public void handleMouseClick(MouseEvent e) {
        this.originalSize = new Dimension(800, 600);
        RoomElement newBed = factory.create("bed", e);
        CreateRoomFrame createRoomFrame = new CreateRoomFrame(newBed);
        createRoomFrame.setVisible(true);

        createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                Painter painter = factory.createPainter(newBed);
                roomView.getPainters().add(painter);
                roomView.getRoom().addChild(newBed);
                roomView.getRoomRectangle().repaint();
            }
        });
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {

    }

    @Override
    public void handleMousePressed(MouseEvent e) {

    }

    @Override
    public void handleKeyPress(KeyEvent e) {

    }

    @Override
    public void handleMouseRelease(MouseEvent e) {

    }

    @Override
    public void enterState() {

    }

    @Override
    public void exitState() {

    }
}
