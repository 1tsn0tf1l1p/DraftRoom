package raf.draft.dsw.controller.actions;

import lombok.Getter;

@Getter

public class ActionManager {
    private AbstractRoomAction exitAction;
    private AbstractRoomAction aboutUsAction;
    private AbstractRoomAction addNodeAction;
    private AbstractRoomAction deleteNodeAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
    }

}
