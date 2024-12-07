package raf.draft.dsw.view.bars;

import lombok.Getter;
import raf.draft.dsw.controller.state.AddState;
import raf.draft.dsw.model.observer.ISubscriber;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.room.RoomView;
import raf.draft.dsw.view.tab.TabView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

@Getter
public class Panel extends JPanel implements ISubscriber {
    private JTree projectExplorer;
    private TabContainer tabContainer;
    private JLabel projectLbl;
    private JLabel authorLbl;
    private JLabel pathLbl;
    private JLabel objectLbl;
    private JPanel rightPanel;
    private RoomView roomView;
    private JToolBar addComponents;
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
        labelsPanel.setBackground(Color.WHITE);
        labelsPanel.setBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        labelsPanel.add(projectLbl);
        labelsPanel.add(objectLbl);
        labelsPanel.add(authorLbl);
        labelsPanel.add(pathLbl);

        projectLbl.setHorizontalAlignment(SwingConstants.CENTER);
        authorLbl.setHorizontalAlignment(SwingConstants.CENTER);
        pathLbl.setHorizontalAlignment(SwingConstants.CENTER);
        objectLbl.setHorizontalAlignment(SwingConstants.CENTER);

        addComponents = new AddStateToolBar();
        addComponents.setVisible(false);
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(tabContainer, BorderLayout.NORTH);
        rightPanel.add(labelsPanel, BorderLayout.SOUTH);
        rightPanel.add(addComponents,BorderLayout.EAST);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.2);
        splitPane.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public <T> void update(T t) {
        Component selectedTab = tabContainer.getTabbedPane().getSelectedTab();

        Component centerComponent = ((BorderLayout) rightPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);

        if (centerComponent != null) {
            rightPanel.remove(centerComponent);
            setVisibilityAddPanel(false);
        }

        if (selectedTab != null) {
            roomView = new RoomView(((TabView)selectedTab).getRoom());
            rightPanel.add(roomView, BorderLayout.CENTER);
        }

        if (t != null && t.equals("delete")) {
            projectLbl.setText("Project: /");
            authorLbl.setText("Author: /");
            pathLbl.setText("Path: /");
            objectLbl.setText("Building: /");
        } else {
            if (selectedTab instanceof TabView) {
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
        rightPanel.revalidate();
        rightPanel.repaint();
    }
    public void setVisibilityAddPanel(Boolean bool){
        addComponents.setVisible(bool);
    }
}
