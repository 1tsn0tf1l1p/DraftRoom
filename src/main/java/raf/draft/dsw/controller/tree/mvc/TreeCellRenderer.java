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

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object cell, boolean selectionHighlight, boolean isExpanded, boolean isLeaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, cell, selectionHighlight, isExpanded, isLeaf, row, hasFocus);

        DraftNode node = ((TreeItem) cell).getNode();
        Icon icon = getIconForNode(node);
        setIcon(icon);

        return this;
    }

    private Icon getIconForNode(DraftNode node) {
        String iconPath = null;

        if (node instanceof ProjectExplorer) {
            iconPath = "/images/project_explorer.png";
        }
        else if (node instanceof Project) {
            iconPath = "/images/project.png";
        }
        else if (node instanceof Building){
            iconPath = "/images/building.png";
        }
        else if(node instanceof Room) {
            iconPath = "/images/room.png";
        }

        if (iconPath != null) {
            // Loading images from resources folder
            URL imageURL = getClass().getResource(iconPath);
            if (imageURL != null) {
                return new ImageIcon(imageURL);
            }
        }

        return null;
    }

    @Override
    public Color getBackgroundNonSelectionColor() {
        return new Color(240, 240, 240);
    }

    @Override
    public Color getBackgroundSelectionColor() {
        return new Color(170, 170, 170);
    }

    @Override
    public Color getBackground() {
        return new Color(240, 240, 240);
    }


    @Override
    public Color getTextNonSelectionColor() {
        return Color.BLACK;
    }

    @Override
    public Color getTextSelectionColor() {
        return Color.WHITE;
    }
}
