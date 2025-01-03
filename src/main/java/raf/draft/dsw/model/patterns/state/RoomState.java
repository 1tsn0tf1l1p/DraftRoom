package raf.draft.dsw.model.patterns.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * The `RoomState` interface defines the behavior of a room in different states within the
 * application. This is part of a State design pattern implementation, where each state
 * of a room can define specific behavior for handling user interactions and state transitions.
 *
 * <p>
 * Implementations of this interface are expected to define specific logic for handling
 * mouse and keyboard events, as well as transitions when entering and exiting the state.
 * </p>
 */
public interface RoomState {

    /**
     * Handles a mouse click event within the current state.
     *
     * @param e the `MouseEvent` representing the mouse click.
     */
    void handleMouseClick(MouseEvent e);

    /**
     * Handles a mouse drag event within the current state.
     *
     * @param e the `MouseEvent` representing the mouse drag.
     */
    void handleMouseDrag(MouseEvent e);

    /**
     * Handles a mouse press event within the current state.
     *
     * @param e the `MouseEvent` representing the mouse press.
     */
    void handleMousePressed(MouseEvent e);

    /**
     * Handles a key press event within the current state.
     *
     * @param e the `KeyEvent` representing the key press.
     */
    void handleKeyPress(KeyEvent e);

    /**
     * Handles a mouse wheel movement event within the current state.
     *
     * @param e the `MouseWheelEvent` representing the mouse wheel movement.
     */
    void handleMouseWheelMoved(MouseWheelEvent e);

    /**
     * Handles a mouse release event within the current state.
     *
     * @param e the `MouseEvent` representing the mouse release.
     */
    void handleMouseRelease(MouseEvent e);

    /**
     * Called when entering this state. Used for setup or initialization
     * specific to the state.
     */
    void enterState();

    /**
     * Called when exiting this state. Used for cleanup or transitioning
     * out of the state.
     */
    void exitState();
}
