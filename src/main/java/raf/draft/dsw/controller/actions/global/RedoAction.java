package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractRoomAction {
    public RedoAction() {
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo the last action.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
