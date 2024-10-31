package raf.draft.dsw.model.repository;

import lombok.Getter;
import raf.draft.dsw.controller.tree.DraftTreeImplementation;
import raf.draft.dsw.model.structures.ProjectExplorer;

@Getter
public class DraftRoomExplorerImplementation implements DraftRoomRepository {
    private ProjectExplorer root;
    private DraftTreeImplementation treeImplementation;
    @Override
    public ProjectExplorer getRoot() {
        if (root == null){
        root = new ProjectExplorer("Project Explorer", null);
        }
        return root;
    }
}
