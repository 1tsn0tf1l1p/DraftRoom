package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
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
        RoomView roomView = MainFrame.getInstance().getPanel().getRoomView();

        if (roomView != null) {
            commandManager = MainFrame.getInstance().getPanel().getRoomView().getCommandManager();
            commandManager.redoCommand();
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }
    }
}
