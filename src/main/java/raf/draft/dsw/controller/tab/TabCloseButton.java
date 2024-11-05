package raf.draft.dsw.controller.tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabCloseButton extends JPanel {

    public TabCloseButton(final Component tab, String title) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

        JLabel lbTitle = new JLabel(title);
        add(lbTitle);

        JButton closeButton = new JButton("✕");
        closeButton.setPreferredSize(new Dimension(16, 16));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setToolTipText("Close tab");

        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(Color.BLACK);
            }
        });

        closeButton.addActionListener(e -> {
            Container parent = tab.getParent();
            while (parent != null && !(parent instanceof JTabbedPane)) {
                parent = parent.getParent();
            }
            if (parent instanceof JTabbedPane tabbedPane) {
                tabbedPane.remove(tab);
            }
        });

        add(closeButton);
    }
}
