package raf.draft.dsw.controller.actions.state;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.state.AddState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;

import java.awt.event.ActionEvent;

public class AddStateAction extends AbstractRoomAction {
    DraftTreeImplementation tree;
    RoomView roomView;

    public AddStateAction() {
        tree = ApplicationFramework.getInstance().getTree();
        putValue(NAME, "Add State");
        putValue(SHORT_DESCRIPTION, "Put the program into add state.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        roomView = MainFrame.getInstance().getPanel().getRoomView();
        roomView.changeState(new AddState(roomView));
    }
}
