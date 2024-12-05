package raf.draft.dsw.controller.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.RoomView;

public class ZoomState implements RoomState {
    private final RoomView roomView;
    private double zoomFactor = 1.0;

    public ZoomState(RoomView roomView) {
        this.roomView = roomView;
    }


    @Override
    public void handleMouseClick(MouseEvent e) {

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
        int notches = e.getWheelRotation();
        double zoomStep = 0.1;

        if (notches < 0) {
            zoomFactor += zoomStep;
        } else if (notches > 0) {
            zoomFactor = Math.max(0.1, zoomFactor - zoomStep);
        }

        roomView.setZoomFactor(zoomFactor);
        roomView.repaint();
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {

    }

    @Override
    public void enterState() {
        System.out.println("Entered ZoomState");
    }

    @Override
    public void exitState() {
        System.out.println("Exited ZoomState");
    }
}
