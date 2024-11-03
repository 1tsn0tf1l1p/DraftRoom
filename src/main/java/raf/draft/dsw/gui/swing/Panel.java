package raf.draft.dsw.gui.swing;

import raf.draft.dsw.core.ApplicationFramework;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private JTree projectExplorer;
    private TabContainer tabContainer;
    public Panel(TabContainer tabContainer, JTree projectExplorer) {
        this.projectExplorer = projectExplorer;
        this.tabContainer = tabContainer;
        init();
    }

    private void init() {
        JPanel leftPanel = new JPanel();
        leftPanel.add(projectExplorer);
        leftPanel.setBackground(Color.GRAY);
        JScrollPane rightScrollPane = new JScrollPane();
        rightScrollPane.setBackground(Color.BLACK);
        rightScrollPane.add(tabContainer);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, tabContainer);
        splitPane.setResizeWeight(0.2);
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
    }
}
