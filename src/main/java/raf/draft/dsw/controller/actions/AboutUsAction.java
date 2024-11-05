package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.AboutUsFrame;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractRoomAction {

    AboutUsFrame aboutUsFrame;

    public AboutUsAction() {
        this.aboutUsFrame = new AboutUsFrame();
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        aboutUsFrame.setVisible(true);
    }
}
