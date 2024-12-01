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
    private int offsetX;
    private int offsetY;

    public MoveState(RoomView roomView) {
        this.roomView = roomView;
        this.selectedPainter = null;
        this.initialDragX = -1;
        this.initialDragY = -1;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        // Select the painter that is clicked on
        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                selectedPainter = painter;
                // Calculate offset when drag starts, so the element stays under the cursor
                RoomElement element = selectedPainter.getElement();
                offsetX = e.getX() - element.getX();  // X offset from the element's position
                offsetY = e.getY() - element.getY();  // Y offset from the element's position
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

            // Get the current mouse position
            int currentDragX = e.getX();
            int currentDragY = e.getY();

            // Calculate the new position of the element based on the mouse position
            int deltaX = currentDragX - selectedPainter.getElement().getScaledX();
            int deltaY = currentDragY - selectedPainter.getElement().getScaledY();

            // Update the RoomElement's position to the new mouse position
            element.setScaledX(currentDragX - deltaX);
            element.setScaledY(currentDragY - deltaY);

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
        offsetX = 0;
        offsetY = 0;
    }
}
