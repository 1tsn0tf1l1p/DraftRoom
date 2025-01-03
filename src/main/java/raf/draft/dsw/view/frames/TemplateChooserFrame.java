package raf.draft.dsw.view.frames;

import raf.draft.dsw.controller.templates.LoadSelectedTemplateController;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TemplateChooserFrame extends JFrame {
    private JList<String> templateList;
    private JButton selectButton;
    private DefaultListModel<String> listModel;

    private static final String TEMPLATE_FOLDER = "src/main/resources/templates/";

    public TemplateChooserFrame() {
        init();
        actions();
    }

    private void actions() {
        selectButton.addActionListener(e -> {
            String selectedTemplate = templateList.getSelectedValue();
            if (selectedTemplate == null) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "Please, select a template first!");
            } else {
                new LoadSelectedTemplateController(selectedTemplate).actionPerformed(e);
                dispose();
            }
        });
    }

    private void init() {
        setTitle("Choose Template");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around all elements

        JLabel titleLabel = new JLabel("Choose a template:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        List<String> templates = loadTemplates();

        listModel = new DefaultListModel<>();
        templates.forEach(listModel::addElement);

        templateList = new JList<>(listModel);
        templateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        templateList.setCellRenderer(new CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(templateList);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Optional: Add a border around the list
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        selectButton = new JButton("Select");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button
        buttonPanel.add(selectButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    private List<String> loadTemplates() {
        List<String> templateNames = new ArrayList<>();
        File templateFolder = new File(TEMPLATE_FOLDER);

        if (templateFolder.exists() && templateFolder.isDirectory()) {
            File[] files = templateFolder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName().replace(".json", "");
                    templateNames.add(fileName);
                }
            }
        }

        return templateNames;
    }
    
    private static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            label.setHorizontalAlignment(SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.PLAIN, 14));
            if (isSelected) {
                label.setBackground(new Color(0, 120, 215));
                label.setForeground(Color.WHITE);
            } else {
                label.setBackground(Color.WHITE);
                label.setForeground(Color.BLACK);
            }

            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemplateChooserFrame frame = new TemplateChooserFrame();
            frame.setVisible(true);
        });
    }
}