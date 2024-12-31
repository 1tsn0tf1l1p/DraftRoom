package raf.draft.dsw.controller.actions;

import lombok.Getter;
import raf.draft.dsw.controller.actions.global.*;
import raf.draft.dsw.controller.actions.state.*;

@Getter

public class ActionManager {
    private AbstractRoomAction exitAction;
    private AbstractRoomAction aboutUsAction;
    private AbstractRoomAction addNodeAction;
    private AbstractRoomAction deleteNodeAction;
    private AbstractRoomAction addRoomNodeAction;
    private AbstractRoomAction renameNodeAction;
    private AbstractRoomAction undoAction;
    private AbstractRoomAction redoAction;
    private AbstractRoomAction saveAction;
    private AbstractRoomAction loadAction;

    private AbstractRoomAction addStateAction;
    private AbstractRoomAction copyPasteRoomStateAction;
    private AbstractRoomAction deleteStateAction;
    private AbstractRoomAction editStateAction;
    private AbstractRoomAction moveStateAction;
    private AbstractRoomAction resizeStateAction;
    private AbstractRoomAction rotateLeftStateAction;
    private AbstractRoomAction rotateRightStateAction;
    private AbstractRoomAction selectStateAction;
    private AbstractRoomAction zoomStateAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.addRoomNodeAction = new AddRoomNodeAction();
        this.renameNodeAction = new RenameNodeAction();
        this.undoAction = new UndoAction();
        this.redoAction = new RedoAction();
        this.saveAction = new SaveAction();
        this.loadAction = new LoadAction();

        this.addStateAction = new AddStateAction();
        this.copyPasteRoomStateAction = new CopyPasteRoomStateAction();
        this.deleteStateAction = new DeleteStateAction();
        this.editStateAction = new EditStateAction();
        this.moveStateAction = new MoveStateAction();
        this.resizeStateAction = new ResizeStateAction();
        this.rotateLeftStateAction = new RotateLeftStateAction();
        this.rotateRightStateAction = new RotateRightStateAction();
        this.selectStateAction = new SelectStateAction();
        this.zoomStateAction = new ZoomStateAction();
    }

}
