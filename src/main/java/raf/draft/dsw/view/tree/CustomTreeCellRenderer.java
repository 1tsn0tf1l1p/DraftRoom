package raf.draft.dsw.view.tree;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.tree.TreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
        }
        setOpaque(true);

        if (cell instanceof TreeItem) {
            DraftNode node = ((TreeItem) cell).getNode();
            Icon icon = getIconForNode(node);

            if (selectionHighlight && icon != null) {
                icon = invertIcon(icon);
            }

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

    private Icon invertIcon(Icon originalIcon) {
        if (originalIcon == null) return null;

        BufferedImage image = new BufferedImage(originalIcon.getIconWidth(), originalIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        originalIcon.paintIcon(null, g, 0, 0);
        g.dispose();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgba = image.getRGB(x, y);
                Color color = new Color(rgba, true);
                Color invertedColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(), color.getAlpha());
                image.setRGB(x, y, invertedColor.getRGB());
            }
        }

        return new ImageIcon(image);
    }
}
