package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;

import java.awt.event.ActionEvent;

public class SaveTemplateAction extends AbstractRoomAction {
    public SaveTemplateAction() {
        putValue(NAME, "Save Template");
        putValue(SMALL_ICON, loadIcon("/images/saveTemplate.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
