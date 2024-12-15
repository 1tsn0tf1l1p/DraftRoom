package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class DeleteState implements RoomState {
    private DraftTreeImplementation treeImplementation;

    private RoomView roomView;

    public DeleteState(RoomView roomView) {
        this.roomView = roomView;
        treeImplementation = ApplicationFramework.getInstance().getTree();
        deleteSelected();
    }

    private void deleteSelected() {
        for (Painter painter : roomView.getPainters()) {
            if (painter.isSelected()) {
                TreeItem item = treeImplementation.returnTreeItemForRoom(painter.getElement());
                treeImplementation.removeChild(item);
                roomView.getRoom().removeChild(painter.getElement());
                roomView.getPainters().remove(painter);
                roomView.repaint();
            }
        }
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        for (Painter painter : roomView.getPainters()) {
            if (painter.elementAt(painter.getElement(), e.getPoint())) {
                TreeItem item = treeImplementation.returnTreeItemForRoom(painter.getElement());
                treeImplementation.removeChild(item);
                roomView.getRoom().removeChild(painter.getElement());
                roomView.getPainters().remove(painter);
            }
            roomView.repaint();
        }
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {

    }

    @Override
    public void handleMousePressed(MouseEvent e) {

    }

    @Override
    public void handleKeyPress(KeyEvent e) {

    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void handleMouseRelease(MouseEvent e) {

    }

    @Override
    public void enterState() {

    }

    @Override
    public void exitState() {

    }
}
