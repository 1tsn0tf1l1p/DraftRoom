package raf.draft.dsw.view.frames;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {

    public AboutUsFrame() {
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("About us - DraftRoom");

        int screenHeight = 430;
        int screenWidth = 300;
        setSize(screenWidth, screenHeight);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("About us", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        JLabel description = new JLabel("U ovom projekatu su učestvovali:", SwingConstants.CENTER);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        mainPanel.add(description);

        mainPanel.add(createStudentProfile("/images/filip.jpg", "Filip Čobanin SI86/24"));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(createStudentProfile("/images/dimitrije.jpg", "Dimitrije Šovljanski SI90/24"));

        add(mainPanel);
    }

    private JPanel createStudentProfile(String imagePath, String name) {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = loadAndResizeImage(imagePath, 100, 100);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        profilePanel.add(imageLabel);
        profilePanel.add(nameLabel);

        return profilePanel;
    }

    private ImageIcon loadAndResizeImage(String path, int width, int height) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}