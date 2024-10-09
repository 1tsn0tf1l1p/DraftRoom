package raf.draft.dsw.gui.swing;
import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {
        initialize();
    }

    private static class HelperHolder{
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
        this.setVisible(true);
    }

    public static MainFrame getInstance() {
        return HelperHolder.INSTANCE;
    }
}
