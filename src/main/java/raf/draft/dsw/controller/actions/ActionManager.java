package raf.draft.dsw.controller.actions;

import lombok.Getter;

@Getter

public class ActionManager {
    private AbstractRoomAction exitAction;
    private AbstractRoomAction aboutUsAction;
    private AbstractRoomAction addNodeAction;
    private AbstractRoomAction deleteNodeAction;
    private AbstractRoomAction addRoomNodeAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.addRoomNodeAction = new AddRoomNodeAction();
    }

}
