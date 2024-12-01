package raf.draft.dsw.controller.actions;

import lombok.Getter;
import raf.draft.dsw.controller.actions.global.*;
import raf.draft.dsw.controller.actions.state.AddStateAction;

@Getter

public class ActionManager {
    private AbstractRoomAction exitAction;
    private AbstractRoomAction aboutUsAction;
    private AbstractRoomAction addNodeAction;
    private AbstractRoomAction deleteNodeAction;
    private AbstractRoomAction addRoomNodeAction;
    private AbstractRoomAction renameNodeAction;
    private AbstractRoomAction addStateAction;
    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.addRoomNodeAction = new AddRoomNodeAction();
        this.renameNodeAction = new RenameNodeAction();
        this.addStateAction = new AddStateAction();
    }

}
