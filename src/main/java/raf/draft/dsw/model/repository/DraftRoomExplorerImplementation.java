package raf.draft.dsw.model.repository;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;

@Getter
public class DraftRoomExplorerImplementation implements DraftRoomRepository {
    @Setter
    private ProjectExplorer root;
    private DraftTreeImplementation treeImplementation;

    public DraftRoomExplorerImplementation() {
        this.treeImplementation = new DraftTreeImplementation();
        this.root = new ProjectExplorer("Project Explorer", null);
    }

    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

}