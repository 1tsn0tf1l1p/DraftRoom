package raf.draft.dsw.view.frames;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class CreateRoomFrame extends JFrame {
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
        setTitle(draftNode.getIme());
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

        int widthNum;
        int heightNum;
        if (draftNode instanceof Room) {
            widthNum = ((Room) draftNode).getWidth();
            heightNum = ((Room) draftNode).getHeight();
        }
        else {
            widthNum = ((RoomElement)draftNode).getWidth();
            heightNum = ((RoomElement)draftNode).getHeight();
        }

        if (widthNum == 0) {
            widthField = new JSpinner(new SpinnerNumberModel(500, 50, Integer.MAX_VALUE, 5));
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(widthField, gbc);
            heightField = new JSpinner(new SpinnerNumberModel(500, 50, Integer.MAX_VALUE, 5));
            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(heightField, gbc);
        }
        else {
            widthField = new JSpinner(new SpinnerNumberModel(widthNum, 50, Integer.MAX_VALUE, 5));
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(widthField, gbc);
            heightField = new JSpinner(new SpinnerNumberModel(heightNum, 50, Integer.MAX_VALUE, 5));
            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(heightField, gbc);
        }


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
                int width = Integer.parseInt(widthField.getValue().toString());
                int height = Integer.parseInt(heightField.getValue().toString());
                if (draftNode instanceof RoomElement) {
                    ((RoomElement) draftNode).setSize(width, height);
                } else if (draftNode instanceof Room) {
                    ((Room) draftNode).setSize(width, height);

                }
                dispose();
            } catch (NumberFormatException exception) {
                ApplicationFramework.getInstance().getMessageGenerator()
                        .createMessage(MessageType.ERROR, "Invalid width or height input.");
            }
        });
    }
}
