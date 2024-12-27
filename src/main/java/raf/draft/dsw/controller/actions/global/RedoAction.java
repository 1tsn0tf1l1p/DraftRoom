package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.view.commands.CommandManager;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractRoomAction {
    private CommandManager commandManager;
    public RedoAction() {
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo the last action.");
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commandManager = MainFrame.getInstance().getPanel().getRoomView().getCommandManager();
        commandManager.redoCommand();
    }
}
