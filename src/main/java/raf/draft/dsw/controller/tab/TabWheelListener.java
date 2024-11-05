package raf.draft.dsw.controller.tab;

import raf.draft.dsw.gui.swing.tab.TabView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TabWheelListener extends MouseAdapter implements MouseWheelListener {

    private final JScrollBar vScrollBar;
    private final TabKeyListener tabKeyListener;
    private final TabView tabView;

    public TabWheelListener(JScrollBar vScrollBar, TabKeyListener tabKeyListener, TabView tabView) {
        this.vScrollBar = vScrollBar;
        this.tabKeyListener = tabKeyListener;
        this.tabView = tabView;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

}