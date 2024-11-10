package raf.draft.dsw.view.bars;

import raf.draft.dsw.model.observer.ISubscriber;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.view.tab.TabView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Panel extends JPanel implements ISubscriber {
    private JTree projectExplorer;
    private TabContainer tabContainer;
    private JLabel projectLbl;
    private JLabel authorLbl;
    private JLabel pathLbl;
    private JLabel objectLbl;

    public Panel(TabContainer tabContainer, JTree projectExplorer) {
        this.projectExplorer = projectExplorer;
        this.tabContainer = tabContainer;
        tabContainer.getTabbedPane().addSubscriber(this);
        this.projectLbl = new JLabel("Project: /");
        this.authorLbl = new JLabel("Author: /");
        this.pathLbl = new JLabel("Path: /");
        this.objectLbl = new JLabel("Building: /");

        projectLbl.setBorder(new EmptyBorder(5, 10, 5, 10));
        authorLbl.setBorder(new EmptyBorder(5, 10, 5, 10));
        pathLbl.setBorder(new EmptyBorder(5, 10, 5, 10));
        objectLbl.setBorder(new EmptyBorder(5, 10, 5, 10));

        init();
    }

    private void init() {
        JPanel leftPanel = new JPanel();
        leftPanel.add(projectExplorer);
        leftPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane rightScrollPane = new JScrollPane();
        rightScrollPane.setBackground(Color.BLACK);
        rightScrollPane.add(tabContainer);

        JPanel labelsPanel = new JPanel(new GridLayout(2, 2, 10, 0));
        labelsPanel.add(projectLbl);
        labelsPanel.add(objectLbl);
        labelsPanel.add(authorLbl);
        labelsPanel.add(pathLbl);

        projectLbl.setHorizontalAlignment(SwingConstants.CENTER);
        authorLbl.setHorizontalAlignment(SwingConstants.CENTER);
        pathLbl.setHorizontalAlignment(SwingConstants.CENTER);
        objectLbl.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(tabContainer, BorderLayout.CENTER);
        rightPanel.add(labelsPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.2);

        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public <T> void update(T t) {
        Component selectedTab = tabContainer.getTabbedPane().getSelectedTab();
        if (t != null && t.equals("delete")) {
            projectLbl.setText("Project: /");
            authorLbl.setText("Author: /");
            pathLbl.setText("Path: /");
            objectLbl.setText("Building: /");
        } else {
            if (selectedTab != null && selectedTab instanceof TabView) {
                if (((TabView) selectedTab).getRoom().getParent() instanceof Project) {
                    projectLbl.setText("Project: " + ((TabView) selectedTab).getRoom().getParent().getIme());
                    authorLbl.setText("Author: " + ((Project) ((TabView) selectedTab).getRoom().getParent()).getAuthor());
                    pathLbl.setText("Path: " + ((Project) ((TabView) selectedTab).getRoom().getParent()).getPath());
                    objectLbl.setText("Building: /");
                } else if (((TabView) selectedTab).getRoom().getParent() instanceof Building) {
                    projectLbl.setText("Project: " + ((TabView) selectedTab).getRoom().getParent().getParent().getIme());
                    authorLbl.setText("Author: " + ((Project) ((TabView) selectedTab).getRoom().getParent().getParent()).getAuthor());
                    pathLbl.setText("Path: " + ((Project) ((TabView) selectedTab).getRoom().getParent().getParent()).getPath());
                    objectLbl.setText("Building: " + ((TabView) selectedTab).getRoom().getParent().getIme());
                }
            }
        }
    }
}
