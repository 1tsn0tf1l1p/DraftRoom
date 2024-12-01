package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SelectState implements RoomState {
    private RoomView roomView;

    // Track the currently selected Painter and initial drag position
    private Painter selectedPainter;
    private int initialDragX;
    private int initialDragY;

    public SelectState(RoomView roomView) {
        this.roomView = roomView;
        this.selectedPainter = null;
        this.initialDragX = -1;
        this.initialDragY = -1;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        for (Painter painter : roomView.getPainters()) {
            if (painter.elementAt(painter.getElement(), e.getPoint())) {
                painter.setSelected(true);
                selectedPainter = painter; // Store the selected painter for dragging
            } else {
                painter.setSelected(false);
            }
        }
        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (selectedPainter != null) {
            // Get the RoomElement associated with the selected Painter
            var element = selectedPainter.getElement();

            // Get the current mouse position
            int currentDragX = e.getX();
            int currentDragY = e.getY();

            if (initialDragX == -1 && initialDragY == -1) {
                // Set initial drag position
                initialDragX = currentDragX;
                initialDragY = currentDragY;
            }

            // Calculate the drag difference
            int deltaX = currentDragX - initialDragX;
            int deltaY = currentDragY - initialDragY;

            // Update the RoomElement's position (using x and y variables)
            element.setX(element.getX() + deltaX);
            element.setY(element.getY() + deltaY);

            // Update initial drag position
            initialDragX = currentDragX;
            initialDragY = currentDragY;

            // Repaint the RoomView to reflect changes
            roomView.repaint();
        }
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
        // Handle key press events if needed
    }

    @Override
    public void enterState() {
        // Perform any setup when entering this state
    }

    @Override
    public void exitState() {
        // Cleanup when exiting this state
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;
    }
}
