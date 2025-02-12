package raf.draft.dsw.view.frames;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.patterns.observer.ISubscriber;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.view.bars.MyMenuBar;
import raf.draft.dsw.view.bars.MyToolBar;
import raf.draft.dsw.view.bars.Panel;
import raf.draft.dsw.view.bars.TabContainer;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private DraftTreeImplementation tree;
    private TabContainer tabContainer;
    private Panel panel;

    private MainFrame() {
        initialize();
    }

    public static MainFrame getInstance() {
        return HelperHolder.INSTANCE;
    }

    private void initialize() {
        this.tree = ApplicationFramework.getInstance().getTree();
        this.tabContainer = new TabContainer(tree.getTreeView());

        Image appIcon = Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/logo.png"));
        try {
            if (Taskbar.isTaskbarSupported()) {
                Taskbar taskbar = Taskbar.getTaskbar();
                if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                    taskbar.setIconImage(appIcon);
                } else {
                    setIconImage(appIcon);
                }
            } else {
                setIconImage(appIcon);
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Taskbar feature is not supported: " + e.getMessage());
            setIconImage(appIcon);
        }

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(2*screenWidth / 3 + screenWidth / 6, 2*screenHeight / 3);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        panel = new Panel(tabContainer, tree.getTreeView());
        add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public <T> void update(T t) {
        String message = t.toString();
        String splitMessage = message.split("]")[2].strip();
        MessageType type = MessageType.valueOf(message.substring(1, message.indexOf("]")));
        if (type == MessageType.ERROR) {
            JOptionPane.showMessageDialog(null, splitMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (type == MessageType.INFO) {
            JOptionPane.showMessageDialog(null, splitMessage, "INFO", JOptionPane.INFORMATION_MESSAGE);
        } else if (type == MessageType.WARNING) {
            JOptionPane.showMessageDialog(null, splitMessage, "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static class HelperHolder {
        private static final MainFrame INSTANCE = new MainFrame();
    }
}
