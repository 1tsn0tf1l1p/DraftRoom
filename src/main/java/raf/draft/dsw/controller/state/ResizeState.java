package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

    @Override
    public void handleMousePressed(MouseEvent e) {
        for (Painter painter : roomView.getPainters()) {
            RoomElement element = painter.getElement();
            if (element != null && isWithinResizeArea(element, e.getPoint())) {
                selectedElement = element;
                isResizing = true;

                // Store the initial dimensions of the element
                initialWidth = element.getWidth();
                initialHeight = element.getHeight();

                // Store the initial mouse position
                initialX = e.getX();
                initialY = e.getY();

                break;
            }
        }
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (isResizing && selectedElement != null) {
            int deltaX = e.getX() - initialX;
            int deltaY = e.getY() - initialY;

            int newWidth = initialWidth + deltaX;
            int newHeight = initialHeight + deltaY;

            newWidth = Math.max(newWidth, 10);
            newHeight = Math.max(newHeight, 10);

            selectedElement.setSize(newWidth, newHeight);

            roomView.repaint();
        }
    }



    @Override
    public void handleKeyPress(KeyEvent e) {
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

        int resizeAreaWidth = RESIZE_THRESHOLD * 2;
        int resizeAreaHeight = RESIZE_THRESHOLD * 2;

        Rectangle resizeArea = new Rectangle(
                x + width - resizeAreaWidth,
                y + height - resizeAreaHeight,
                resizeAreaWidth,
                resizeAreaHeight
        );

        return resizeArea.contains(point);
    }
}
