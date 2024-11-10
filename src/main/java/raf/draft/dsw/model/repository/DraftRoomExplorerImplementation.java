package raf.draft.dsw.model.repository;

import lombok.Getter;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;

@Getter
public class DraftRoomExplorerImplementation implements DraftRoomRepository {
    private ProjectExplorer root;
    private DraftTreeImplementation treeImplementation;

    public DraftRoomExplorerImplementation() {
        this.treeImplementation = new DraftTreeImplementation();
    }

    @Override
    public ProjectExplorer getRoot() {
        if (root == null) {
            root = new ProjectExplorer("Project Explorer", null);
        }
        return root;
    }
}
