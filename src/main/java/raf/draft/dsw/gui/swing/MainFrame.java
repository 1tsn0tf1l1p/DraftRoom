package raf.draft.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.messagegenerator.MessageType;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.controller.tree.DraftTreeImplementation;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    DraftTreeImplementation tree = ApplicationFramework.getInstance().getTree();
    private static MainFrame instance;

    private MainFrame() {
        initialize();
    }

    private static class HelperHolder {
        private static final MainFrame INSTANCE = new MainFrame();
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        add(new Panel(tree.getTreeView()), BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public <T> void update(T t) {
        String message = t.toString();
        String splitMessage = message.split("]")[2].strip();
        MessageType type = MessageType.valueOf(message.substring(1,message.indexOf("]")));
        if (type == MessageType.ERROR) {
            JOptionPane.showMessageDialog(null, splitMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else if(type == MessageType.INFO) {
            JOptionPane.showMessageDialog(null, splitMessage, "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(type == MessageType.WARNING) {
            JOptionPane.showMessageDialog(null, splitMessage, "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static MainFrame getInstance() {
        return HelperHolder.INSTANCE;
    }
}