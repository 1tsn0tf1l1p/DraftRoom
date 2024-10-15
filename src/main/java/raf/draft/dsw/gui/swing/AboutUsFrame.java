package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame() {
        initialize();

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("About us - DraftRoom");
        this.setVisible(true);
        JTextPane student1 = new JTextPane();
        JTextPane student2 = new JTextPane();

        student1.setText("Filip Čobanin SI86/24");
        student2.setText("Dimitrije Šovljanski SI90/24");
        JPanel j1 = new JPanel();
        j1.setLayout(new BorderLayout());
        j1.add(student1, BorderLayout.NORTH);
        j1.add(student2, BorderLayout.SOUTH);
        this.getContentPane().add(j1, BorderLayout.CENTER);
    }

}
