package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.ActionManager;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    private ActionManager actionManager;

    public MyToolBar() {
        super(HORIZONTAL);
        actionManager = new ActionManager();
        setFloatable(false);
        add(actionManager.getAboutUsAction());
        add(actionManager.getAddNodeAction());
        add(actionManager.getDeleteNodeAction());
        add(Box.createHorizontalGlue());
        add(actionManager.getExitAction());
    }
}
