package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SelectState implements RoomState {
    private RoomView roomView;

    private Point startPoint;
    private Rectangle selectionBox;

    public SelectState(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        for (Painter painter : roomView.getPainters()) {
            painter.setSelected(painter.elementAt(painter.getElement(), e.getPoint()));
        }
        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (startPoint != null) {
            int x = Math.min(startPoint.x, e.getX());
            int y = Math.min(startPoint.y, e.getY());
            int width = Math.abs(startPoint.x - e.getX());
            int height = Math.abs(startPoint.y - e.getY());
            selectionBox = new Rectangle(x, y, width, height);

            roomView.setSelectionBox(selectionBox);
            roomView.repaint();
        }
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        if (selectionBox != null) {
            for (Painter painter : roomView.getPainters()) {
                if (selectionBox.intersects(painter.getBounds())) {
                    painter.setSelected(true);
                } else {
                    painter.setSelected(false);
                }
            }
        }
        selectionBox = null;
        startPoint = null;
        roomView.setSelectionBox(null);
        roomView.repaint();
    }

    @Override
    public void enterState() {
    }

    @Override
    public void exitState() {
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        startPoint = e.getPoint();

        for (Painter painter : roomView.getPainters()) {
            painter.setSelected(false);
        }

        roomView.setSelectionBox(null);
        roomView.repaint();
    }
}
