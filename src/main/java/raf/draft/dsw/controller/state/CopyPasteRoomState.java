package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.patterns.state.RoomState;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.commands.concrete_commands.CopyPasteCommand;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CopyPasteRoomState implements RoomState {
    private RoomView roomView;
    private DraftTreeImplementation treeImplementation;

    public CopyPasteRoomState(RoomView roomView) {
        this.roomView = roomView;
        this.treeImplementation = ApplicationFramework.getInstance().getTree();
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        CopyPasteCommand copyPasteCommand = new CopyPasteCommand(roomView);

        for (Painter painter : roomView.getPainters()) {
            if (painter.elementAt(painter.getElement(), e.getPoint())) {
                RoomElement roomElement = (RoomElement) painter.getElement().clone();
                Painter newPainter = roomView.getFactory().createPainter(roomElement);

                copyPasteCommand.addCopiedElement(roomElement, newPainter);
            }
        }

        if (!copyPasteCommand.getCopiedElements().isEmpty()) {
            roomView.getCommandManager().addCommand(copyPasteCommand);
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
