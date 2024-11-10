package raf.draft.dsw.view.frames;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.view.bars.TabContainer;
import raf.draft.dsw.view.tree.TreeView;

import javax.swing.*;
import java.awt.*;

public class RenameProjectFrame extends JFrame {

    private JTextField nameField;
    private JTextField authorField;
    private JTextField pathField;
    private JButton confirmBtn;
    private Project draftNode;
    private TabContainer tabContainer;

    public RenameProjectFrame(Project node) {
        this.draftNode = node;
        this.tabContainer = MainFrame.getInstance().getTabContainer();
        initialize();
        actions();
    }

    private void actions() {
        confirmBtn.addActionListener(e -> {
            for (DraftNode child : draftNode.getParent().getChildren()) {
                if (child.getIme().equals(nameField.getText()) &&
                        ((Project) child).getPath().equals(pathField.getText())) {
                    ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Project with name " + nameField.getText() + " already exists.");
                    return;
                }
            }
            draftNode.setIme(nameField.getText());
            draftNode.setAuthor(authorField.getText());
            draftNode.setPath(pathField.getText());
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

        nameField = new JTextField(draftNode.getIme());
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        JLabel authorLbl = new JLabel("Author:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(authorLbl, gbc);

        authorField = new JTextField(draftNode.getAuthor());
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(authorField, gbc);

        JLabel pathLbl = new JLabel("Path:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(pathLbl, gbc);

        pathField = new JTextField(draftNode.getPath());
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(pathField, gbc);

        confirmBtn = new JButton("Confirm");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(confirmBtn, gbc);

        add(panel);
    }
}
