package raf.draft.dsw.controller.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.frames.MainFrame;

import java.io.File;
import java.io.IOException;

public class SaveTemplateController {
    private static final String TEMPLATE_FOLDER = "src/main/resources/templates/";
    private Room room;
    private String templateName;

    public SaveTemplateController(String templateName) {
        this.templateName = templateName;
    }

    public boolean saveTemplate() {
        if (MainFrame.getInstance().getPanel().getRoomView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "No room created, create a room before saving the template!");
            return false;
        }
        room = MainFrame.getInstance().getPanel().getRoomView().getRoom();
        if (templateName == null || templateName.isEmpty()) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No name entered.");
            return false;
        }

        File file = new File(TEMPLATE_FOLDER + templateName + ".json");

        try {
            saveRoomToJson(file);
            return true;
        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Failed to create room template.");
            return false;
        }
    }

    private void saveRoomToJson(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(file, room);
    }
}