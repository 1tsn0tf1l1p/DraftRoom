package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.structures.ProjectExplorer;

/**
 * The DraftRoomRepository interface provides a method to get the root of the project tree.
 */
public interface DraftRoomRepository {
    /**
     * Gets the root of the project tree.
     *
     * @return the root of the project tree
     */
    ProjectExplorer getRoot();
}