package raf.draft.dsw.controller.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadSelectedTemplateController implements ActionListener {
    private static final String TEMPLATE_FOLDER = "src/main/resources/templates/";
    private final String selectedTemplate;

    public LoadSelectedTemplateController(String selectedTemplate) {
        this.selectedTemplate = selectedTemplate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getPanel().getRoomView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No room created!");
            return;
        }
        try {
            Room currentRoom = MainFrame.getInstance().getPanel().getRoomView().getRoom();

            if (currentRoom.getWidth() > 0 || currentRoom.getHeight() > 0 || !currentRoom.getChildren().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The current room is already initialized or contains elements. Cannot load a template.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File templateFile = new File(TEMPLATE_FOLDER + selectedTemplate + ".json");
            if (!templateFile.exists()) {
                JOptionPane.showMessageDialog(null, "Template file not found: " + templateFile.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Room templateRoom = objectMapper.readValue(templateFile, Room.class);

            currentRoom.setWidth(templateRoom.getWidth());
            currentRoom.setHeight(templateRoom.getHeight());
            currentRoom.setX(templateRoom.getX());
            currentRoom.setY(templateRoom.getY());

            for (DraftNode child : templateRoom.getChildren()) {
                child.setParent(currentRoom);
                currentRoom.addChild(child);
            }
            MainFrame.getInstance().getPanel().getRoomView().addChildren();
            ApplicationFramework.getInstance().getTree().addRoomElementsToTree(currentRoom);
            MainFrame.getInstance().getPanel().getRoomView().repaint();
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.INFO, "Template loaded successfuly!");
        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Failed to load a template.");
        }
    }
}