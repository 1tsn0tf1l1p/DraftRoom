package raf.draft.dsw.view.frames;

import raf.draft.dsw.controller.templates.SaveTemplateController;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;

import javax.swing.*;
import java.awt.*;

public class SaveTemplateFrame extends JFrame {
    private JButton saveButton;
    private JTextField templateNameField;

    public SaveTemplateFrame() {
        init();
        actions();
    }

    private void actions() {
        saveButton.addActionListener(e -> {
            String templateName = templateNameField.getText().trim();
            if (templateName.isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Template name cannot be empty!");
            } else {
                SaveTemplateController controller = new SaveTemplateController(templateName);
                boolean success = controller.saveTemplate();
                if (success) {
                    ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.INFO, "Template created!");
                    dispose();
                }
            }
        });
    }

    private void init() {
        setTitle("Save Template");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel templateNameLabel = new JLabel("Template Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(templateNameLabel, gbc);

        templateNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(templateNameField, gbc);

        saveButton = new JButton("Save");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(saveButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}