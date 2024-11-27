package raf.draft.dsw.view.frames;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.bars.TabContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRoomFrame  extends JFrame {
    private JSpinner widthField;
    private JSpinner heightField;
    private JButton confirmButton;
    private DraftNode draftNode;

    public CreateRoomFrame(DraftNode draftNode) {
        this.draftNode = draftNode;
        initialize();
        actions();
    }

    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Room Dimensions");
        setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel width = new JLabel("Width:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(width, gbc);

        JLabel height = new JLabel("Height:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(height, gbc);

        widthField = new JSpinner(new SpinnerNumberModel(500, 100, Integer.MAX_VALUE, 5));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(widthField, gbc);

        heightField = new JSpinner(new SpinnerNumberModel(500, 100, Integer.MAX_VALUE, 5));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(heightField, gbc);

        confirmButton = new JButton("Confirm");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        panel.add(confirmButton, gbc);

        add(panel);
    }

    private void actions() {
        confirmButton.addActionListener(_ -> {
            try {
                Room room = (Room) draftNode;
                room.setSize(Integer.parseInt(widthField.getValue().toString()), Integer.parseInt(heightField.getValue().toString()));
                System.out.println(room.getWidth());
                System.out.println(room.getHeight());
                dispose();
            } catch(NumberFormatException exception) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Invalid width or height input.");
            }
        });
    }
}
