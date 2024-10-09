package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.MainFrame;

public class ApplicationFramework {

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
        MainFrame.getInstance();
    }
}
