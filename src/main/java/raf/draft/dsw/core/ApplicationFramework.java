package raf.draft.dsw.core;

import lombok.Getter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;

@Getter
public class ApplicationFramework {
    private DraftRoomExplorerImplementation explorerImplementation;

    private ApplicationFramework() {
        initialize();
    }

    private static class HelperHolder {
        private static final ApplicationFramework INSTANCE = new ApplicationFramework();
    }

    public static ApplicationFramework getInstance() {
        return HelperHolder.INSTANCE;
    }


    private void initialize() {
        explorerImplementation = new DraftRoomExplorerImplementation();
        MainFrame.getInstance();
    }
}
