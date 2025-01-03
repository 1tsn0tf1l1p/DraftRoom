package raf.draft.dsw.controller.actions.global;

import lombok.Getter;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.frames.RenameNodeFrame;
import raf.draft.dsw.view.frames.RenameProjectFrame;
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
        }
        JFrame roomOrganizer = new RoomOrganizerFrame(MainFrame.getInstance().getPanel().getRoomView());
        roomOrganizer.setVisible(true);

    }
}
