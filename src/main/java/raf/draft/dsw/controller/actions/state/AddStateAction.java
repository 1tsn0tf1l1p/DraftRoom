package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.AddState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class AddStateAction extends AbstractRoomAction {
    private RoomView roomView;

    public AddStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/addState.png"));
        putValue(NAME, "Add State");
        putValue(SHORT_DESCRIPTION, "Put the program into add state.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new AddState(roomView));
            MainFrame.getInstance().getPanel().setVisibilityAddPanel(true);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }
    }
}
