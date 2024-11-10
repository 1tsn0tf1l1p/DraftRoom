package raf.draft.dsw.controller.tab;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.tree.DraftTree;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.bars.TabContainer;
import raf.draft.dsw.view.tab.TabView;
import raf.draft.dsw.view.tab.TabbedPane;
import raf.draft.dsw.view.tree.TreeView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabbedPaneController {

    private final TabContainer tabContainer;

    public TabbedPaneController(TabContainer tabContainer) {
        this.tabContainer = tabContainer;
    }

    public void generateTabs(TreeView projectExplorer, DraftTree tree) {
        projectExplorer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleProjectExplorerMousePress(e, tree);
            }
        });
    }

    private void handleProjectExplorerMousePress(MouseEvent e, DraftTree tree) {
        if (e.getClickCount() == 2) {
            TreeItem treeItem = tree.getSelectedItem();
            if (treeItem.getNode() instanceof Project) {
                refreshProjectAndTabs((Project) treeItem.getNode());
                tabContainer.getTabbedPane().notifySubscribers(null);
            }
        }
    }

    private void refreshProjectAndTabs(Project selectedProject) {
        TabbedPane tabbedPane = tabContainer.getTabbedPane();

        tabbedPane.repaint();
        tabbedPane.setProject(selectedProject);

        selectedProject.getChildren().forEach(this::addTabToTabbedPane);
        tabContainer.getTabbedPane().notifySubscribers(null);
    }

    public void clearTabs() {
        TabbedPane tabbedPane = tabContainer.getTabbedPane();
        tabbedPane.setProject(null);
        tabContainer.getTabbedPane().notifySubscribers(null);
    }

    private void addTabToTabbedPane(DraftNode child) {
        TabbedPane tabbedPane = tabContainer.getTabbedPane();
        if (child instanceof Room) {
            TabView tab = new TabView((Room) child);
            tabbedPane.addTab(child.getIme(), tab);
            tabContainer.getTabbedPane().notifySubscribers(null);
        } else if (child instanceof Building) {
            for (DraftNode draftNode : ((Building) child).getChildren()) {
                TabView tab = new TabView((Room) draftNode);
                tabbedPane.addTab(draftNode.getIme(), tab);
                tabContainer.getTabbedPane().notifySubscribers(null);
            }
        }
    }

}