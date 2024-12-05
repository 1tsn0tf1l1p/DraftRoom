package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class SelectState implements RoomState {
    private RoomView roomView;
    private Point startPoint;
    private Rectangle selectionBox;

    public SelectState(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        Point scaledPoint = scalePoint(e.getPoint());
        for (Painter painter : roomView.getPainters()) {
            painter.setSelected(painter.elementAt(painter.getElement(), scaledPoint));
        }
        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (startPoint != null) {
            Point scaledStartPoint = startPoint;
            Point scaledCurrentPoint = unscalePoint(e.getPoint());

            int x = Math.min(scaledStartPoint.x, scaledCurrentPoint.x);
            int y = Math.min(scaledStartPoint.y, scaledCurrentPoint.y);
            int width = Math.abs(scaledStartPoint.x - scaledCurrentPoint.x);
            int height = Math.abs(scaledStartPoint.y - scaledCurrentPoint.y);

            selectionBox = new Rectangle(x, y, width, height);
            roomView.setSelectionBox(selectionBox);
            roomView.repaint();
        }
    }

    private Point unscalePoint(Point point) {
        double zoomFactor = roomView.getZoomFactor();
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
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
    public void handleMousePressed(MouseEvent e) {
        startPoint = unscalePoint(e.getPoint());

        for (Painter painter : roomView.getPainters()) {
            painter.setSelected(false);
        }

        roomView.setSelectionBox(null);
        roomView.repaint();
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void enterState() {
    }

    @Override
    public void exitState() {
    }

    private Point scalePoint(Point point) {
        double zoomFactor = roomView.getZoomFactor();
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
    }
}
