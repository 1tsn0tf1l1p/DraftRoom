package raf.draft.dsw.controller.actions.global;

import lombok.Getter;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.frames.RoomOrganizerFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Getter
public class RoomOrganizerAction extends AbstractRoomAction {

    public RoomOrganizerAction() {
        putValue(SMALL_ICON, loadIcon("/images/room-organizer.png"));
        putValue(NAME, "Room organizer");
        putValue(SHORT_DESCRIPTION, "Room organizer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getPanel().getRoomView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No room opened.");
            return;
        } else if (MainFrame.getInstance().getPanel().getRoomView().getRoom().getWidth() == 0) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "You must initialize the room first!");
            return;
        } else if (!MainFrame.getInstance().getPanel().getRoomView().getRoom().getChildren().isEmpty()) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "You cannot run Room Organizer on a room with elements.");
            return;
        }
        JFrame roomOrganizer = new RoomOrganizerFrame(MainFrame.getInstance().getPanel().getRoomView());
        roomOrganizer.setVisible(true);

    }
}
