package raf.draft.dsw.gui.swing;

import com.sun.source.tree.Tree;
import lombok.Getter;
import raf.draft.dsw.controller.tab.TabbedPaneController;
import raf.draft.dsw.controller.tree.DraftTree;
import raf.draft.dsw.controller.tree.mvc.TreeView;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tab.TabbedPane;

import javax.swing.*;
import java.awt.*;

@Getter
public class TabContainer extends JPanel {

    private final TabbedPane tabbedPane;
    private final TabbedPaneController tabbedPaneController;

    public TabContainer(TreeView tree) {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        this.tabbedPane = new TabbedPane();
        this.add(tabbedPane, BorderLayout.NORTH);
        this.tabbedPaneController = new TabbedPaneController(this);
        tabbedPaneController.generateTabs(tree, ApplicationFramework.getInstance().getTree());
    }
}
