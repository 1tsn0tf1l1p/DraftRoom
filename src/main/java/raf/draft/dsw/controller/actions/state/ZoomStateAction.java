package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.ZoomState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class ZoomStateAction extends AbstractRoomAction {
    private RoomView roomView;

    public ZoomStateAction() {
        putValue(NAME, "Zoom State");
        putValue(SMALL_ICON, loadIcon("/images/zoomState.png"));
        putValue(SHORT_DESCRIPTION, "Put the program in the Zoom state.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPanel().setVisibilityAddPanel(false);
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        if (roomView != null) {
            roomView.changeState(new ZoomState(roomView));
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room initialized.");
        }
    }
}
