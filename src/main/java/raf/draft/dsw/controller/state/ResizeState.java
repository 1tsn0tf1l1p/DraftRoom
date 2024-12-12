package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ResizeState implements RoomState {
    private RoomView roomView;
    private RoomElement selectedElement;
    private boolean isResizing;
    private final int RESIZE_THRESHOLD = 10;

    private int initialX;
    private int initialY;
    private int initialWidth;
    private int initialHeight;

    public ResizeState(RoomView roomView) {
        this.roomView = roomView;
        this.isResizing = false;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
    }
    private boolean snapToEdge(RoomElement element) {
        int roomWidth = roomView.getRoom().getWidth();
        int roomHeight = roomView.getRoom().getHeight();

        boolean snapped = false;
        int padding = 10;
        if (element.getX() < padding) {
            element.setX(10);
            snapped = true;
        }
        if (element.getY() < padding) {
            element.setY(10);
            snapped = true;
        }
        if (element.getX() + element.getWidth() > roomWidth-padding) {
            element.setX(roomWidth - element.getWidth());
            snapped = true;
        }
        if (element.getY() + element.getHeight() > roomHeight-padding) {
            element.setY(roomHeight - element.getHeight());
            snapped = true;
        }

        return snapped;
    }
    @Override
    public void handleMousePressed(MouseEvent e) {
        Point scaledPoint = unscalePoint(e.getPoint());

        for (Painter painter : roomView.getPainters()) {
            RoomElement element = painter.getElement();
            if (element != null && isWithinResizeArea(element, scaledPoint)) {

                selectedElement = element;
                isResizing = true;

                initialWidth = element.getWidth();
                initialHeight = element.getHeight();

                initialX = scaledPoint.x;
                initialY = scaledPoint.y;

                break;
            }
        }
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (isResizing && selectedElement != null) {

            Point scaledPoint = unscalePoint(e.getPoint());

            int deltaX = scaledPoint.x - initialX;
            int deltaY = scaledPoint.y - initialY;

            int newWidth = initialWidth + deltaX;
            int newHeight = initialHeight + deltaY;

            newWidth = Math.max(newWidth, 10);
            newHeight = Math.max(newHeight, 10);
            int prevWidth = selectedElement.getWidth();
            int prevHeight = selectedElement.getHeight();
            int prevX = selectedElement.getX();
            int prevY = selectedElement.getY();
            selectedElement.setSize(newWidth, newHeight);

            boolean checkInt = checkIntersection(selectedElement,10);
            boolean snap = snapToEdge(selectedElement);

            if (checkInt || snap){
                selectedElement.setSize(prevWidth,prevHeight);
                selectedElement.setX(prevX);
                selectedElement.setY(prevY);
            }
            roomView.repaint();
        }
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        isResizing = false;
        selectedElement = null;
    }

    @Override
    public void enterState() {
        isResizing = false;
        selectedElement = null;
    }

    @Override
    public void exitState() {
        isResizing = false;
        selectedElement = null;
    }

    private boolean isWithinResizeArea(RoomElement element, Point point) {
        int x = element.getScaledX();
        int y = element.getScaledY();
        int width = element.getScaledWidth();
        int height = element.getScaledHeight();

        double zoomFactor = roomView.getZoomFactor();
        int scaledThreshold = (int) (RESIZE_THRESHOLD / zoomFactor);

        int resizeAreaWidth = scaledThreshold * 2;
        int resizeAreaHeight = scaledThreshold * 2;

        Rectangle resizeArea = new Rectangle(
                x + width - resizeAreaWidth,
                y + height - resizeAreaHeight,
                resizeAreaWidth,
                resizeAreaHeight
        );

        return resizeArea.contains(point);
    }

    private Point unscalePoint(Point point) {
        double zoomFactor = roomView.getZoomFactor();
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
    }
    private boolean checkIntersection(RoomElement element, int padding) {
        Rectangle elementBounds = element.getBounds();

        for (Painter otherPainter : roomView.getPainters()) {
            RoomElement otherElement = otherPainter.getElement();

            if (element == otherElement) {
                continue;
            }

            Rectangle otherBounds = otherElement.getBounds();

            int dx = Math.max(0, Math.max(otherBounds.x - (elementBounds.x + elementBounds.width), elementBounds.x - (otherBounds.x + otherBounds.width)));
            int dy = Math.max(0, Math.max(otherBounds.y - (elementBounds.y + elementBounds.height), elementBounds.y - (otherBounds.y + otherBounds.height)));

            if (Math.sqrt(dx * dx + dy * dy) <= padding) {
                return true;
            }
        }
        return false;


    }


}
