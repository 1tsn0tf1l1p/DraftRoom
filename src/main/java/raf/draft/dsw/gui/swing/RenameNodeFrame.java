package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.messagegenerator.MessageType;
import raf.draft.dsw.controller.tree.mvc.TreeView;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenameNodeFrame extends JFrame {
    private JTextField nameField;
    private JButton confirmButton;
    private DraftNode draftNode;
    private TabContainer tabContainer;

    public RenameNodeFrame(DraftNode draftNode) {
        this.draftNode = draftNode;
        this.tabContainer = MainFrame.getInstance().getTabContainer();
        initialize();
        actions();
    }

    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Rename Project");
        setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(draftNode.getIme(), 20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        confirmButton = new JButton("Confirm");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        panel.add(confirmButton, gbc);

        add(panel);
    }

    private void actions() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (DraftNode child : draftNode.getParent().getChildren()) {
                    if(child.getIme().equals(nameField.getText())){
                        ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR,"Node with name " + nameField.getText() + " already exists.");
                        return;
                    }
                }
                draftNode.setIme(nameField.getText());
                TreeView treeView = ApplicationFramework.getInstance().getTree().getTreeView();
                treeView.expandPath(treeView.getSelectionPath());
                SwingUtilities.updateComponentTreeUI(treeView);
                tabContainer.getTabbedPane().notifySubscribers(null);
                dispose();
            }
        });
    }
}
