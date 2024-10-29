package raf.draft.dsw.controller.tree;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreePath;
import java.awt.*;

public class CustomTreeUI extends BasicTreeUI {

    @Override
    protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
        g.setColor(Color.BLACK);
        super.paintHorizontalLine(g, c, y, left, right);
    }

    @Override
    protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
        g.setColor(Color.BLACK);
        super.paintVerticalLine(g, c, x, top, bottom);
    }

    @Override
    protected void paintExpandControl(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds,
                                      TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded,
                                      boolean isLeaf) {
        g.setColor(Color.BLACK);
        super.paintExpandControl(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
    }

}
