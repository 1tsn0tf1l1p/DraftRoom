package raf.draft.dsw.controller.logger;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.logger.Logger;
import raf.draft.dsw.model.messagegenerator.MessageType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private File file;

    public FileLogger() {
        file = new File("src/main/resources/log.txt");
        try {
            if (file.exists()) {
                new FileWriter(file, false).close();
            }
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Error initializing log file.");
        }
    }

    @Override
    public <T> void update(T t) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append((String) t);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Error with file creation.");
        }
    }
}
