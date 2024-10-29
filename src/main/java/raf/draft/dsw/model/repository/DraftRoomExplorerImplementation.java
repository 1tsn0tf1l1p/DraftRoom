package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.structures.ProjectExplorer;

public class DraftRoomExplorerImplementation implements DraftRoomRepository {
    private ProjectExplorer root;
    @Override
    public ProjectExplorer getRoot() {
        if (root == null){
        root = new ProjectExplorer("Project Explorer", null);
        }
        return root;
    }
}
