package raf.draft.dsw.model.state;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface RoomState {
    void handleMouseClick(MouseEvent e);
    void handleMouseDrag(MouseEvent e);
    void handleKeyPress(KeyEvent e);
    void enterState();
    void exitState();
}
