package raf.draft.dsw.controller.actions;

import lombok.Getter;

@Getter

public class ActionManager {
    private AbstractRoomAction exitAction;
    private AbstractRoomAction aboutUsAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
    }

}
