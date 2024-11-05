package raf.draft.dsw.controller.tree.mvc;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class TreeView extends JTree {

    public TreeView(DefaultTreeModel dftTreeModel) {
        super.setModel(dftTreeModel);
        CustomTreeCellRenderer treeCellRenderer = new CustomTreeCellRenderer();
        super.addTreeSelectionListener(new MyTreeSelectionListener());
        super.setCellEditor(new TreeCellEditor(this, treeCellRenderer));
        super.setCellRenderer(treeCellRenderer);
        super.setEditable(true);
    }
}
