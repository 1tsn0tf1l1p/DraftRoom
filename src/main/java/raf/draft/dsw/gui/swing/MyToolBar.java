package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.ExitAction;
import raf.draft.dsw.controller.actions.ActionManager;

import javax.swing.*;

import static java.util.Collections.addAll;

public class MyToolBar extends JToolBar {
    private ActionManager actionManager;

    public MyToolBar() {
        super(HORIZONTAL);
        actionManager = new ActionManager();
        setFloatable(false);
        add(actionManager.getAboutUsAction());
        add(Box.createHorizontalGlue());
        add(actionManager.getExitAction());
    }
}
