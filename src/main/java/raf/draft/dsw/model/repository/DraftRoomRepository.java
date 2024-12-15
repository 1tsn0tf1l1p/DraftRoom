package raf.draft.dsw.model.repository;

import raf.draft.dsw.model.structures.ProjectExplorer;

/**
 * The DraftRoomRepository interface provides a method to get the root of the project explorer.
 */
public interface DraftRoomRepository {
    /**
     * Gets the root of the project explorer.
     *
     * @return the root of the project explorer
     */
    ProjectExplorer getRoot();
}