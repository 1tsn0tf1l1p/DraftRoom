package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.CopyPasteRoomState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class CopyPasteRoomStateAction extends AbstractRoomAction {
    private RoomView roomView;

    public CopyPasteRoomStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/copyPasteState.png"));
        putValue(NAME, "Copy/paste State");
        putValue(SHORT_DESCRIPTION, "Put the program into copy/paste state.");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new CopyPasteRoomState(roomView));
            MainFrame.getInstance().getPanel().setVisibilityAddPanel(false);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }
    }
}
