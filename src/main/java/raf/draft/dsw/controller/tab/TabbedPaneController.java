package raf.draft.dsw.controller.tab;

import raf.draft.dsw.controller.tree.DraftTree;
import raf.draft.dsw.controller.tree.mvc.TreeItem;
import raf.draft.dsw.controller.tree.mvc.TreeView;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.TabContainer;
import raf.draft.dsw.gui.swing.tab.TabView;
import raf.draft.dsw.gui.swing.tab.TabbedPane;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

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
            }
        }
    }

    private void refreshProjectAndTabs(Project selectedProject) {
        TabbedPane tabbedPane = tabContainer.getTabbedPane();

        tabbedPane.repaint();
        tabbedPane.setProject(selectedProject);

        selectedProject.getChildren().forEach(this::addTabToTabbedPane);
    }

    private void addTabToTabbedPane(DraftNode child) {
        TabbedPane tabbedPane = tabContainer.getTabbedPane();
        System.out.println(child.getIme());
        if(child instanceof Room) {
            TabView tab = new TabView((Room) child);
            tabbedPane.addTab(child.getIme(), tab);
        }
        else if (child instanceof Building) {
            System.out.println(((Building) child).getChildren());
            for (DraftNode draftNode : ((Building) child).getChildren()) {
                System.out.println(draftNode.getIme());
                TabView tab = new TabView((Room) draftNode);
                tabbedPane.addTab(draftNode.getIme(), tab);
            }
        }
    }

}