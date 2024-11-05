package raf.draft.dsw.model.factory;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

public class TreeFactory {
    public Project createProject(String name, DraftNode parent, String author, String path) {
        return new Project(name, parent, author, path);
    }

    public Building createBuilding(String name, DraftNode parent) {
        return new Building(name,parent);
    }

    public Room createRoom(String name, DraftNode parent) {
        return new Room(name, parent);
    }
}
