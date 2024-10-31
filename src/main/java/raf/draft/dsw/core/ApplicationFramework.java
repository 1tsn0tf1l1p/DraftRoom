package raf.draft.dsw.core;

import lombok.Getter;
import raf.draft.dsw.controller.messagegenerator.LoggerFactory;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;

@Getter
public class ApplicationFramework {
    private DraftRoomExplorerImplementation explorerImplementation;
    private MessageGenerator messageGenerator;

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
        messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(LoggerFactory.create("ConsoleLogger"));
        messageGenerator.addSubscriber(LoggerFactory.create("FileLogger"));
    }

    public void postInitialize() {
        messageGenerator.addSubscriber(MainFrame.getInstance());
    }
}
