package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame() {
        initialize();

    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 4, screenHeight / 4);

        setLocationRelativeTo(null);
        setTitle("About us - DraftRoom");

        JTextPane student1 = new JTextPane();
        JTextPane student2 = new JTextPane();
        student1.setText("Filip Čobanin SI86/24");
        student2.setText("Dimitrije Šovljanski SI90/24");

        JPanel j1 = new JPanel();
        j1.setLayout(new FlowLayout());
        j1.add(student1);
        j1.add(student2);
        this.getContentPane().add(j1, BorderLayout.CENTER);
    }

}
