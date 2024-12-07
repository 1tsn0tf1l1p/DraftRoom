package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.Bed;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CopyPasteRoomState implements RoomState {
    private RoomView roomView;
    public CopyPasteRoomState(RoomView roomView){
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        for (Painter painter : roomView.getPainters()){
            if (painter.elementAt(painter.getElement(), e.getPoint())) {
                RoomElement roomElement = (RoomElement) painter.getElement().clone();
                Painter newPainter = roomView.getFactory().createPainter(roomElement);
                roomView.getRoom().addChild(roomElement);
                roomView.getPainters().add(newPainter);
                roomView.repaint();
            }
        }
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
    public void handleMouseWheelMoved(MouseWheelEvent e) {

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
