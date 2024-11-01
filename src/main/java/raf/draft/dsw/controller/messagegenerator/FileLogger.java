package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.core.ApplicationFramework;

import java.io.*;

public class FileLogger implements Logger{
    @Override
    public <T> void update(T t){
        try {
            File file = new File("src/main/resources/log.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write((String)t);
            bufferedWriter.close();

        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Error with file creation.");
        }
    }
}
