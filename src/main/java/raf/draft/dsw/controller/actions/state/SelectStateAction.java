package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.SelectState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class SelectStateAction extends AbstractRoomAction {
    private RoomView roomView;

    public SelectStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/selectState.png"));
        putValue(NAME, "Select State");
        putValue(SHORT_DESCRIPTION, "Put the program in Select state");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPanel().setVisibilityAddPanel(false);
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new SelectState(roomView));
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }
    }
}
