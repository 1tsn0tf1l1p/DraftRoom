package raf.draft.dsw.controller.tree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class TreeView extends JTree {

    public TreeView(DefaultTreeModel dftTreeModel) {
        super.setModel(dftTreeModel);
        TreeCellRenderer treeCellRenderer = new TreeCellRenderer();
        super.addTreeSelectionListener(new MyTreeSelectionListener());
        super.setCellEditor(new TreeCellEditor(this, treeCellRenderer));
        super.setCellRenderer(treeCellRenderer);
        super.setEditable(true);
    }
}
