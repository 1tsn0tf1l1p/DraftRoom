package raf.draft.dsw.view.frames;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.factory.RoomElementFactory;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.room.RoomView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomOrganizerFrame extends JFrame {
    private RoomView roomView;
    private Room room;
    private DefaultListModel<Element> listModel;
    private JButton addButton, finishButton;
    private JSpinner widthInput, heightInput;
    private JComboBox<String> roomElementComboBox;
    private JPanel listPanel;
    private List<Element> elementList;

    public RoomOrganizerFrame(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        initialize();
        actions();
    }

    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Room organizer");
        setSize(800, 400);

        elementList = new ArrayList<>();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel column1 = new JPanel();
        column1.setLayout(new BoxLayout(column1, BoxLayout.Y_AXIS));
        column1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        column1.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel widthLabel = new JLabel("Width:");
        column1.add(widthLabel);

        widthInput = new JSpinner(new SpinnerNumberModel(100, 50, room.getWidth(), 1));
        widthInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, widthInput.getPreferredSize().height));
        column1.add(widthInput);

        mainPanel.add(column1);

        JPanel column2 = new JPanel();
        column2.setLayout(new BoxLayout(column2, BoxLayout.Y_AXIS));
        column2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        column2.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel heightLabel = new JLabel("Height:");
        column2.add(heightLabel);

        heightInput = new JSpinner(new SpinnerNumberModel(100, 50, room.getHeight(), 1));
        heightInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, heightInput.getPreferredSize().height));
        column2.add(heightInput);

        mainPanel.add(column2);

        JPanel column3 = new JPanel();
        column3.setLayout(new BoxLayout(column3, BoxLayout.Y_AXIS));
        column3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        column3.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel roomElementLabel = new JLabel("Room element:");
        column3.add(roomElementLabel);

        roomElementComboBox = new JComboBox<>();
        roomElementComboBox.addItem("Bed");
        roomElementComboBox.addItem("Bathtub");
        roomElementComboBox.addItem("Boiler");
        roomElementComboBox.addItem("Doors");
        roomElementComboBox.addItem("Sink");
        roomElementComboBox.addItem("Table");
        roomElementComboBox.addItem("Toiletbowl");
        roomElementComboBox.addItem("Wardrobe");
        roomElementComboBox.addItem("Washingmachine");
        roomElementComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, roomElementComboBox.getPreferredSize().height));
        column3.add(roomElementComboBox);

        mainPanel.add(column3);

        JPanel column4 = new JPanel();
        column4.setLayout(new BoxLayout(column4, BoxLayout.Y_AXIS));
        column4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        column4.setAlignmentY(Component.TOP_ALIGNMENT);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JScrollPane listScrollPane = new JScrollPane(listPanel);
        listScrollPane.setPreferredSize(new Dimension(200, 150));
        column4.add(listScrollPane);

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton = new JButton("Add");
        buttonRow.add(Box.createRigidArea(new Dimension(10, 0))); // Spacer
        buttonRow.add(addButton);

        finishButton = new JButton("Finish");
        buttonRow.add(Box.createRigidArea(new Dimension(10, 0))); // Spacer
        buttonRow.add(finishButton);

        column4.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        column4.add(buttonRow);

        mainPanel.add(column4);

        add(mainPanel);
    }

    private void actions() {
        addButton.addActionListener(_ -> {
            Element element = new Element(
                    roomElementComboBox.getSelectedItem().toString(),
                    (Integer) widthInput.getValue(),
                    (int) heightInput.getValue()
            );
            elementList.add(element);
            addElementToList(element);
        });
        finishButton.addActionListener(_ -> {
            int maxHeight = 0, maxWidth = 0;
            for (Element element : elementList) {
                if (element.height * element.width > maxHeight * maxWidth) {
                    maxHeight = element.height;
                    maxWidth = element.width;
                }
            }
            int heightRatio = room.getHeight() / maxHeight;
            int widthRatio = room.getWidth() / maxWidth;
            if (heightRatio * widthRatio < elementList.size()) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "Cannot organize group with this elements");
                return;
            }
            createElements(heightRatio, widthRatio);
            this.dispose();
        });
    }

    private void addElementToList(Element element) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        JLabel itemLabel = new JLabel(element.toString());
        JButton deleteButton = new JButton("X");
        itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        deleteButton.addActionListener(_ -> {
            elementList.remove(element);
            listPanel.remove(itemPanel);
            listPanel.repaint();
        });
        itemPanel.add(itemLabel);
        itemPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        itemPanel.add(deleteButton);

        itemPanel.setPreferredSize(new Dimension(listPanel.getWidth(), itemLabel.getPreferredSize().height + deleteButton.getPreferredSize().height));

        listPanel.add(itemPanel);
        listPanel.revalidate();
        listPanel.repaint();
    }

    private void createElements(int rows, int columns) {
        int left = 0, top = 0, right = columns - 1, bottom = rows - 1;
        int scaledWidth = roomView.getRoom().getWidth() / columns;
        int scaledHeight = roomView.getRoom().getHeight() / rows;
        int counter = 0;
        Random random = new Random();
        while (counter < columns * rows && !elementList.isEmpty()) {
            for (int i = left; i <= right; i++) {
                if (elementList.isEmpty()) break;
                int randomInt = random.nextInt(0, elementList.size());
                Element element = elementList.get(randomInt);
                int xPos = i * scaledWidth;
                int yPos = top * scaledHeight;
                if (top > 0) {
                    yPos += (scaledHeight - element.height) / 2;
                }
                if (i < right && i > left || (i == left && i == right)) {
                    xPos += (scaledWidth - element.width) / 2;
                }
                if (i == right && i != left) {
                    xPos = room.getWidth() - element.width - top * scaledWidth;
                }
                createElementInRoom(element.type, xPos, yPos, element.width, element.height);
                elementList.remove(element);
                counter++;
            }
            if (elementList.isEmpty()) break;
            top++;
            for (int i = top; i <= bottom; i++) {
                if (elementList.isEmpty()) break;
                int randomInt = random.nextInt(0, elementList.size());
                Element element = elementList.get(randomInt);
                int xPos = room.getWidth() - element.width - left * scaledWidth;
                int yPos = i * scaledHeight;
                if (i == bottom) {
                    yPos = room.getHeight() - element.height - left * scaledHeight;
                }
                if (i >= top && i < bottom) {
                    yPos += (scaledHeight - element.height) / 2;
                }
                createElementInRoom(element.type, xPos, yPos, element.width, element.height);

                elementList.remove(element);
                counter++;
            }
            if (elementList.isEmpty()) break;
            right--;
            for (int i = right; i >= left; i--) {
                if (elementList.isEmpty()) break;
                int randomInt = random.nextInt(0, elementList.size());
                Element element = elementList.get(randomInt);
                int xPos = i * scaledWidth;
                int yPos = room.getHeight() - element.height - left * scaledHeight;
                if (i <= right && i > left) {
                    xPos += (scaledWidth - element.width) / 2;
                }
                createElementInRoom(element.type, xPos, yPos, element.width, element.height);

                elementList.remove(element);
                counter++;
            }
            if (elementList.isEmpty()) break;
            bottom--;
            for (int i = bottom; i >= top; i--) {
                if (elementList.isEmpty()) break;
                int randomInt = random.nextInt(0, elementList.size());
                Element element = elementList.get(randomInt);
                int xPos = left * scaledWidth;
                int yPos = i * scaledHeight + (scaledHeight - element.height) / 2;
                ;
                createElementInRoom(element.type, xPos, yPos, element.width, element.height);
                elementList.remove(element);
                counter++;
            }
            if (elementList.isEmpty()) break;
            left++;
        }
        ApplicationFramework.getInstance().getTree().addRoomElementsToTree(room);
    }

    private void createElementInRoom(String elementType, int xPosition, int yPosition, int elementWidth, int elementHeight) {
        RoomElementFactory factory = new RoomElementFactory(room);
        RoomElement element = factory.create(elementType, null);

        room.addChild(element);
        element.setParent(room);
        element.setX(xPosition);
        element.setY(yPosition);
        element.setWidth(elementWidth);
        element.setHeight(elementHeight);


        roomView.addChildren();
        roomView.repaint();
    }


    private static class Element {
        String type;
        int width;
        int height;

        public Element(String type, int width, int height) {
            this.type = type;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return type + " (" + width + " x " + height + ")";
        }
    }
}
