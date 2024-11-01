package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.ActionManager;
import raf.draft.dsw.controller.actions.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    private ActionManager actionManager;
    public MyMenuBar() {
        actionManager = new ActionManager();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(actionManager.getAddNodeAction());
        fileMenu.add(actionManager.getDeleteNodeAction());
        fileMenu.add(actionManager.getExitAction());
        add(fileMenu);
    }
}
