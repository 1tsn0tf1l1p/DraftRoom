package raf.draft.dsw.controller.tab;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TabCloseButton extends JPanel {

    public TabCloseButton(final Component tab, String title) {
        setOpaque(false);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
        setLayout(flowLayout);
        JLabel lbTitle = new JLabel(title);
        add(lbTitle);
        JButton button = new JButton(MetalIconFactory.getInternalFrameCloseIcon(13));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.addMouseListener((MouseListener) new CloseListener(tab));
        add(button);
    }

    private static class CloseListener implements MouseListener {
        private final Component tab;

        public CloseListener(Component tab) {
            this.tab = tab;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof JButton clickedButton) {
                Container parent = clickedButton.getParent();
                while (parent != null && !(parent instanceof JTabbedPane)) {
                    parent = parent.getParent();
                }
                if (parent instanceof JTabbedPane tabbedPane) {
                    tabbedPane.remove(tab);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}