package raf.draft.dsw.controller.tree.mvc;

import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class TreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;

    public TreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object cell, boolean selectionHighlight, boolean isExpanded, boolean isLeaf, int row) {
        clickedOn = cell;
        JTextField tfEdit = new JTextField(cell.toString());
        tfEdit.addActionListener(this);
        return tfEdit;
    }


    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) {
            return ((MouseEvent) e).getClickCount() == 3;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof TreeItem)) return;
        ((TreeItem) clickedOn).setName(e.getActionCommand());
        ((TreeItem) clickedOn).getNode().setIme(e.getActionCommand());
        stopCellEditing();
        MainFrame.getInstance().getTabContainer().getTabbedPane().notifySubscribers(null);
    }

}
