package raf.draft.dsw.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.model.structures.Project;

import java.io.File;
import java.io.IOException;

public class ProjectManager {

    public static void saveProject(Project project, String filePath) {
        try {
            ObjectMapper mapper = CustomObjectMapper.getMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), project);
            System.out.println("Project saved successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving project: " + e.getMessage());
        }
    }

    public static Project loadProject(String filePath) {
        try {
            ObjectMapper mapper = CustomObjectMapper.getMapper();
            return mapper.readValue(new File(filePath), Project.class);
        } catch (IOException e) {
            System.err.println("Error loading project: " + e.getMessage());
            return null;
        }
    }
}