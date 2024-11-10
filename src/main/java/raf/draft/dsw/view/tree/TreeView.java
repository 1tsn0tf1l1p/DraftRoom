package raf.draft.dsw.view.tree;

import raf.draft.dsw.controller.tree.MyTreeSelectionListener;
import raf.draft.dsw.controller.tree.TreeCellEditor;

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
