package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MoveState implements RoomState {

    private RoomView roomView;
    private Painter selectedPainter;
    private int initialDragX;
    private int initialDragY;

    public MoveState(RoomView roomView) {
        this.roomView = roomView;
        this.selectedPainter = null;
        this.initialDragX = -1;
        this.initialDragY = -1;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                selectedPainter = painter;
                RoomElement element = selectedPainter.getElement();
                double uniformScale = calculateUniformScale();
                initialDragX = e.getX() - (int) (element.getX() * uniformScale);
                initialDragY = e.getY() - (int) (element.getY() * uniformScale);
                break;
            }
        }
        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (selectedPainter != null) {
            RoomElement element = selectedPainter.getElement();
            double uniformScale = calculateUniformScale();
            element.setX((int) ((e.getX() - initialDragX) / uniformScale));
            element.setY((int) ((e.getY() - initialDragY) / uniformScale));
            roomView.repaint();
        }
    }

    private double calculateUniformScale() {
        double scaleX = roomView.getWidth() / (double) roomView.getRoom().getWidth();
        double scaleY = roomView.getHeight() / (double) roomView.getRoom().getHeight();
        return Math.min(scaleX, scaleY);
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void enterState() {
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;
    }

    @Override
    public void exitState() {
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;
    }
}
