package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.RotateLeftState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class RotateLeftStateAction extends AbstractRoomAction {
    private RoomView roomView;
    public RotateLeftStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateLeftState.png"));
        putValue(NAME, "Rotate Left State");
        putValue(SHORT_DESCRIPTION, "Put the program into rotate left state.");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new RotateLeftState(roomView));
            MainFrame.getInstance().getPanel().setVisibilityAddPanel(false);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }

    }
}
