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
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;

        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                RoomElement element = painter.getElement();
                double uniformScale = calculateUniformScale();

                int elementX = (int) (element.getX() * uniformScale);
                int elementY = (int) (element.getY() * uniformScale);
                int elementWidth = (int) (element.getWidth() * uniformScale);
                int elementHeight = (int) (element.getHeight() * uniformScale);

                if (e.getX() >= elementX && e.getX() <= elementX + elementWidth &&
                        e.getY() >= elementY && e.getY() <= elementY + elementHeight) {

                    selectedPainter = painter;
                    initialDragX = e.getX() - elementX;
                    initialDragY = e.getY() - elementY;
                    break;
                }
            }
        }

        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (selectedPainter != null && initialDragX >= 0 && initialDragY >= 0) {
            RoomElement element = selectedPainter.getElement();
            double uniformScale = calculateUniformScale();
            int elementX = (int) (element.getX() * uniformScale);
            int elementY = (int) (element.getY() * uniformScale);
            int elementWidth = (int) (element.getWidth() * uniformScale);
            int elementHeight = (int) (element.getHeight() * uniformScale);
            if (e.getX() >= elementX && e.getX() <= elementX + elementWidth &&
                    e.getY() >= elementY && e.getY() <= elementY + elementHeight) {
                element.setX((int) ((e.getX() - initialDragX) / uniformScale));
                element.setY((int) ((e.getY() - initialDragY) / uniformScale));
                roomView.repaint();
            } else {
                selectedPainter = null;
                initialDragX = -1;
                initialDragY = -1;
            }
        }
    }

    @Override
    public void handleMousePressed(MouseEvent e) {

    }

    @Override
    public void handleKeyPress(KeyEvent e) {

    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;
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

    private double calculateUniformScale() {
        double scaleX = roomView.getWidth() / (double) roomView.getRoom().getWidth();
        double scaleY = roomView.getHeight() / (double) roomView.getRoom().getHeight();
        return Math.min(scaleX, scaleY);
    }
}