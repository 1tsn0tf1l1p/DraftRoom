package raf.draft.dsw.model.core;

import lombok.Getter;
import raf.draft.dsw.controller.tab.TabbedPaneController;
import raf.draft.dsw.model.factory.LoggerFactory;
import raf.draft.dsw.model.messagegenerator.MessageGenerator;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.view.frames.MainFrame;

@Getter
public class ApplicationFramework {
    private DraftRoomExplorerImplementation explorerImplementation;
    private MessageGenerator messageGenerator;
    private DraftTreeImplementation tree;
    private ProjectExplorer projectExplorer;
    private TabbedPaneController tabbedPaneController;

    private ApplicationFramework() {
        initialize();
    }

    public static ApplicationFramework getInstance() {
        return HelperHolder.INSTANCE;
    }

    private void initialize() {
        if (explorerImplementation == null) {
            explorerImplementation = new DraftRoomExplorerImplementation();
        }
        if (projectExplorer == null) {
            projectExplorer = explorerImplementation.getRoot();
        }
        if (tree == null) {
            tree = explorerImplementation.getTreeImplementation();
            tree.generateTree(projectExplorer);
        }
        if (messageGenerator == null) {
            messageGenerator = new MessageGenerator();
            messageGenerator.addSubscriber(LoggerFactory.create("ConsoleLogger"));
            messageGenerator.addSubscriber(LoggerFactory.create("FileLogger"));
        }
    }

    public void postInitialize() {
        messageGenerator.addSubscriber(MainFrame.getInstance());
        tabbedPaneController = new TabbedPaneController(MainFrame.getInstance().getTabContainer());
    }

    private static class HelperHolder {
        private static final ApplicationFramework INSTANCE = new ApplicationFramework();
    }
}
