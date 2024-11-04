package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.tree.mvc.TreeView;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.*;

public class RenameProjectFrame extends JFrame {

    private JTextField name;
    private JTextField author;
    private JTextField path;
    private JButton confirmBtn;
    private Project node;
    private TabContainer tabContainer;

    public RenameProjectFrame(Project node) {
        this.node = node;
        this.tabContainer = MainFrame.getInstance().getTabContainer();
        initialize();
        actions();
    }

    private void actions() {
        confirmBtn.addActionListener(e -> {
            node.setIme(name.getText());
            node.setAuthor(author.getText());
            node.setPath(path.getText());
            TreeView treeView = ApplicationFramework.getInstance().getTree().getTreeView();
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            tabContainer.getTabbedPane().notifySubscribers(null);
            dispose();
        });
    }

    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Rename Project");
        setSize(400, 250);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLbl = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLbl, gbc);

        name = new JTextField(node.getIme());
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(name, gbc);

        JLabel authorLbl = new JLabel("Author:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(authorLbl, gbc);

        author = new JTextField(node.getAuthor());
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(author, gbc);

        JLabel pathLbl = new JLabel("Path:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(pathLbl, gbc);

        path = new JTextField(node.getPath());
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(path, gbc);

        confirmBtn = new JButton("Confirm");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(confirmBtn, gbc);

        add(panel);
    }
}
