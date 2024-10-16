package raf.draft.dsw.gui.swing;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame() {
        initialize();
    }

    private void initialize() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setTitle("About us - DraftRoom");

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 4, screenHeight / 4);

        JPanel labelPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("About us", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        JLabel description = new JLabel("U ovom projekatu su učestvovali:", SwingConstants.CENTER);

        JLabel student1 = new JLabel("Filip Čobanin SI86/24", SwingConstants.CENTER);
        JLabel student2 = new JLabel("Dimitrije Šovljanski SI90/24", SwingConstants.CENTER);

        labelPanel.add(title);
        labelPanel.add(description);
        labelPanel.add(student1);
        labelPanel.add(student2);
        
        add(labelPanel, BorderLayout.NORTH);

    }
}