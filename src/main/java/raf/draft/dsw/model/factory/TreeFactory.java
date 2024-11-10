package raf.draft.dsw.model.factory;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

public class TreeFactory {

    private void createName(DraftNode node, DraftNodeComposite parent) {
        int cx = 1;
        String type = "";
        if (node instanceof Project) type = "Project";
        else if (node instanceof Building) type = "Building";
        else if (node instanceof Room) type = "Room";
        while (parent.getChildren().contains(node)) {
            node.setIme("New " + type + " " + cx);
            cx++;
        }
    }

    public Project createProject(DraftNodeComposite parent) {
        String path = "~/Documents";
        String author = "";
        int cx = 1;
        Project project = new Project("New Project 1", parent, author, path);
        createName(project, parent);
        return project;
    }

    public Building createBuilding(DraftNodeComposite parent) {
        Building building = new Building("New Building 1", parent);
        createName(building, parent);
        return building;
    }

    public Room createRoom(DraftNodeComposite parent) {
        Room room = new Room("New Room 1", parent);
        createName(room, parent);
        return room;

    }
}
