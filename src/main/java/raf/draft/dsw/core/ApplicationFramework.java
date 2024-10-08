package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    private ApplicationFramework() {
        initialize();
    }

    public static ApplicationFramework getInstance() {
        if(instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }


    private void initialize() {
        MainFrame.getInstance();
    }
}
