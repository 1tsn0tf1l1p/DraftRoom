package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.RotateRightState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class RotateRightStateAction extends AbstractRoomAction {
    private RoomView roomView;
    public RotateRightStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateRightState.png"));
        putValue(NAME, "Rotate Right State");
        putValue(SHORT_DESCRIPTION, "Put the program into right rotate state.");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new RotateRightState(roomView));
            MainFrame.getInstance().getPanel().setVisibilityAddPanel(false);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }

    }
}
