package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.DeleteState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.commands.CommandManager;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

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
        RoomView roomView = MainFrame.getInstance().getPanel().getRoomView();

        if (roomView != null) {
            commandManager = MainFrame.getInstance().getPanel().getRoomView().getCommandManager();
            commandManager.undoCommand();
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }


    }
}
