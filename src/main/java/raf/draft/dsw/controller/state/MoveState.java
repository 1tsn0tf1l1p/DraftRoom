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
        // Select the painter that is clicked on
        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                selectedPainter = painter;

                // Calculate the offset between the cursor and the element's position
                RoomElement element = selectedPainter.getElement();
                initialDragX = e.getX() - element.getX(); // Offset X from element's top-left corner
                initialDragY = e.getY() - element.getY(); // Offset Y from element's top-left corner
                break;
            }
        }
        roomView.repaint();
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (selectedPainter != null) {
            // Get the RoomElement associated with the selected Painter
            RoomElement element = selectedPainter.getElement();

            // Update the element's position using the cursor position minus the offset
            element.setX(e.getX() - initialDragX); // Keep the same relative cursor position
            element.setY(e.getY() - initialDragY);

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
        // Reset state when entering the MoveState
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;
    }

    @Override
    public void exitState() {
        // Cleanup when exiting MoveState
        selectedPainter = null;
        initialDragX = -1;
        initialDragY = -1;
    }
}
