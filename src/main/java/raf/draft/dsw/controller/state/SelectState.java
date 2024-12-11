package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;

public class SelectState implements RoomState {
    private RoomView roomView;
    private Point startPoint;

    public SelectState(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        Point unscaledPoint = unscalePoint(e.getPoint());
        for (Painter painter : roomView.getPainters()) {
            painter.setSelected(painter.elementAt(painter.getElement(), unscaledPoint));
            System.out.println(painter.isSelected());
        }
        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (startPoint != null) {
            Point unscaledStartPoint = startPoint;
            Point unscaledCurrentPoint = unscalePoint(e.getPoint());

            int x = Math.min(unscaledStartPoint.x, unscaledCurrentPoint.x);
            int y = Math.min(unscaledStartPoint.y, unscaledCurrentPoint.y);
            int width = Math.abs(unscaledStartPoint.x - unscaledCurrentPoint.x);
            int height = Math.abs(unscaledStartPoint.y - unscaledCurrentPoint.y);

            if (roomView.getSelectionBox() == null) {
                roomView.setSelectionBox(new Rectangle(x, y, width, height));
            } else {
                roomView.getSelectionBox().setBounds(x, y, width, height);
            }

            roomView.repaint();
        }
    }

    private Point unscalePoint(Point point) {
        double zoomFactor = roomView.getZoomFactor();
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        if (roomView.getSelectionBox() != null) {
            Rectangle selectionBox = roomView.getSelectionBox();

            for (Painter painter : roomView.getPainters()) {
                Rectangle2D painterBounds = painter.getBounds();

                if (painterBounds != null && selectionBox.intersects(painterBounds)) {
                    painter.setSelected(true);
                } else {
                    painter.setSelected(false);
                }
            }
        }

        roomView.setSelectionBox(null);
        startPoint = null;
        roomView.repaint();
    }



    @Override
    public void handleMousePressed(MouseEvent e) {
        startPoint = unscalePoint(e.getPoint());
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
}
