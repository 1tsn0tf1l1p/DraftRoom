package raf.draft.dsw.controller.templates;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DeleteSelectedTemplateController implements ActionListener {
    private static final String TEMPLATE_FOLDER = "src/main/resources/templates/";

    private final JFrame parentFrame;
    private final JList<String> templateList;
    private final DefaultListModel<String> listModel;

    public DeleteSelectedTemplateController(JFrame parentFrame, JList<String> templateList, DefaultListModel<String> listModel) {
        this.parentFrame = parentFrame;
        this.templateList = templateList;
        this.listModel = listModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTemplate = templateList.getSelectedValue();
        if (selectedTemplate == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(
                    MessageType.WARNING,
                    "Please, select a template first!"
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                parentFrame,
                "Are you sure you want to delete the template \"" + selectedTemplate + "\"?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            File fileToDelete = new File(TEMPLATE_FOLDER + selectedTemplate + ".json");
            if (!fileToDelete.exists()) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(
                        MessageType.ERROR,
                        "File not found."
                );
                return;
            }

            boolean deleted = fileToDelete.delete();
            if (deleted) {
                listModel.removeElement(selectedTemplate);
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(
                        MessageType.INFO,
                        "Template \"" + selectedTemplate + "\" deleted successfully."
                );
            } else {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(
                        MessageType.ERROR,
                        "Unable to delete template."
                );
            }
        }
    }
}