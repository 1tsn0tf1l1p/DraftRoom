package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;

import java.awt.event.ActionEvent;

public class TemplateChooserAction extends AbstractRoomAction {
    public TemplateChooserAction() {
        putValue(NAME, "Template Chooser");
        putValue(SMALL_ICON, loadIcon("/images/template.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
