package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private JTree projectExplorer;

    public Panel(JTree projectExplorer) {
        this.projectExplorer = projectExplorer;
        init();
    }

    private void init() {
        JPanel leftPanel = new JPanel();
        TabContainer tabContainer = new TabContainer();
        leftPanel.add(projectExplorer);
        leftPanel.setBackground(Color.GRAY);
        JScrollPane rightScrollPane = new JScrollPane();
        rightScrollPane.setBackground(Color.BLACK);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, tabContainer);
        splitPane.setResizeWeight(0.2);
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
    }
}
