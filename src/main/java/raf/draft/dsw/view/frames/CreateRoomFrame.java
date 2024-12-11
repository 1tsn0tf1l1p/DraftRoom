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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter
@Setter
public class CreateRoomFrame extends JFrame {
    private JSpinner widthField;
    private JSpinner heightField;
    private JTextField nameField;
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
        setSize(400, 250);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int widthNum;
        int heightNum;

        if (draftNode instanceof Room) {
            widthNum = ((Room) draftNode).getWidth();
            heightNum = ((Room) draftNode).getHeight();
        } else {
            widthNum = ((RoomElement) draftNode).getWidth();
            heightNum = ((RoomElement) draftNode).getHeight();
        }

        if (draftNode instanceof RoomElement) {
            JLabel nameLabel = new JLabel("Name:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(nameLabel, gbc);

            nameField = new JTextField(((RoomElement) draftNode).getIme(), 15);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(nameField, gbc);
        }

        JLabel width = new JLabel("Width:");
        gbc.gridx = 0;
        gbc.gridy = (draftNode instanceof RoomElement) ? 1 : 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(width, gbc);

        widthField = new JSpinner(new SpinnerNumberModel(widthNum == 0 ? 500 : widthNum, 50, Integer.MAX_VALUE, 5));
        gbc.gridx = 1;
        gbc.gridy = (draftNode instanceof RoomElement) ? 1 : 0;
        panel.add(widthField, gbc);

        JLabel height = new JLabel("Height:");
        gbc.gridx = 0;
        gbc.gridy = (draftNode instanceof RoomElement) ? 2 : 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(height, gbc);

        heightField = new JSpinner(new SpinnerNumberModel(heightNum == 0 ? 500 : heightNum, 50, Integer.MAX_VALUE, 5));
        gbc.gridx = 1;
        gbc.gridy = (draftNode instanceof RoomElement) ? 2 : 1;
        panel.add(heightField, gbc);

        confirmButton = new JButton("Confirm");
        gbc.gridx = 1;
        gbc.gridy = (draftNode instanceof RoomElement) ? 3 : 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        panel.add(confirmButton, gbc);

        add(panel);
    }

    private void actions() {
        confirmButton.addActionListener(_ -> {
            createElement();
        });
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("test");
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    createElement();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
    private void createElement(){
        try {
            int width = Integer.parseInt(widthField.getValue().toString());
            int height = Integer.parseInt(heightField.getValue().toString());

            if (draftNode instanceof RoomElement) {
                RoomElement roomElement = (RoomElement) draftNode;
                roomElement.setSize(width, height);

                if (nameField != null && !nameField.getText().trim().isEmpty()) {
                    roomElement.setIme(nameField.getText().trim());
                }
            } else if (draftNode instanceof Room) {
                ((Room) draftNode).setSize(width, height);
            }
            dispose();
        } catch (NumberFormatException exception) {
            ApplicationFramework.getInstance().getMessageGenerator()
                    .createMessage(MessageType.ERROR, "Invalid width or height input.");
        }
    }

}
