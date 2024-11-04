package raf.draft.dsw.controller.tree.mvc;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object cell, boolean selectionHighlight,
                                                  boolean isExpanded, boolean isLeaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, cell, selectionHighlight, isExpanded, isLeaf, row, hasFocus);

        if (selectionHighlight) {
            setBackground(Color.DARK_GRAY);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.GRAY);
            setForeground(Color.BLACK);
        }
        setOpaque(true);

        if (cell instanceof TreeItem) {
            DraftNode node = ((TreeItem) cell).getNode();
            Icon icon = getIconForNode(node);
            setIcon(icon);
        }

        return this;
    }

    private Icon getIconForNode(DraftNode node) {
        String iconPath = null;

        if (node instanceof ProjectExplorer) {
            iconPath = "/images/project_explorer.png";
        } else if (node instanceof Project) {
            iconPath = "/images/project.png";
        } else if (node instanceof Building) {
            iconPath = "/images/building.png";
        } else if (node instanceof Room) {
            iconPath = "/images/room.png";
        }

        if (iconPath != null) {
            URL imageURL = getClass().getResource(iconPath);
            if (imageURL != null) {
                return new ImageIcon(imageURL);
            }
        }

        return null;
    }
}
