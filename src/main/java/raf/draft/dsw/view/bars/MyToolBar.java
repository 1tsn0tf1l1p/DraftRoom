package raf.draft.dsw.view.bars;

import raf.draft.dsw.controller.actions.ActionManager;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MyToolBar extends JToolBar {
    private ActionManager actionManager;

    public MyToolBar() {
        super(HORIZONTAL);
        actionManager = new ActionManager();
        setFloatable(false);
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        add(actionManager.getAddNodeAction());
        add(actionManager.getAddRoomNodeAction());
        add(actionManager.getDeleteNodeAction());
        add(actionManager.getRenameNodeAction());
        add(Box.createHorizontalGlue());

        add(actionManager.getSelectStateAction());
        add(actionManager.getMoveStateAction());
        add(actionManager.getAddStateAction());
        add(actionManager.getDeleteStateAction());
        add(actionManager.getEditStateAction());
        add(actionManager.getResizeStateAction());
        add(actionManager.getRotateLeftStateAction());
        add(actionManager.getRotateRightStateAction());
        add(actionManager.getCopyPasteRoomStateAction());
        add(actionManager.getZoomStateAction());

        add(Box.createHorizontalGlue());
        add(actionManager.getAboutUsAction());
        add(actionManager.getExitAction());
    }
}
