package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.AddState;
import raf.draft.dsw.controller.state.MoveState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class MoveStateAction extends AbstractRoomAction {
    private RoomView roomView;

    public MoveStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/moveState.png"));
        putValue(NAME, "Move State");
        putValue(SHORT_DESCRIPTION, "Put the program into move state.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new MoveState());
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }
    }
}
