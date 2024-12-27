package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.view.commands.CommandManager;
import raf.draft.dsw.view.frames.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRoomAction {
    CommandManager commandManager;
    public UndoAction() {
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo the last action.");
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commandManager = MainFrame.getInstance().getPanel().getRoomView().getCommandManager();
        commandManager.undoCommand();
    }
}
