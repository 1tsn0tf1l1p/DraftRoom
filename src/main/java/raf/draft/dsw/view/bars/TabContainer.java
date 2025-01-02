package raf.draft.dsw.view.bars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import raf.draft.dsw.controller.tab.TabbedPaneController;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.view.tab.TabbedPane;
import raf.draft.dsw.view.tree.TreeView;

import javax.swing.*;
import java.awt.*;

@Getter
public class TabContainer extends JPanel {
    @JsonIgnore
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
