package raf.draft.dsw.controller.actions;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.AboutUsAction;
import raf.draft.dsw.controller.ExitAction;

@Getter

public class ActionManager {
    private AbstractRoomAction exitAction;
    private AbstractRoomAction aboutUsAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
    }

}
