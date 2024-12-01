package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.AddState;
import raf.draft.dsw.controller.state.RotateState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class RotateStateAction extends AbstractRoomAction {
    private RoomView roomView;
    public RotateStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateState.png"));
        putValue(NAME, "Rotate State");
        putValue(SHORT_DESCRIPTION, "Put the program into rotate state.");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new RotateState());
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }

    }
}
