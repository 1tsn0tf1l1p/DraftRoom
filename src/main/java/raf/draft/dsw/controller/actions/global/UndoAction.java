package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRoomAction {
    public UndoAction() {
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo the last action.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
