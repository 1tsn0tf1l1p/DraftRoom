package raf.draft.dsw.view.room;

import lombok.Getter;
import raf.draft.dsw.model.factory.RoomElementFactory;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.frames.CreateRoomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomView extends JPanel {
    private Room room;
    private List<RoomElement> elements;
    private List<Painter> painters;
    private RoomElementFactory factory;
    private Dimension originalSize;

    public RoomView(Room room) {
        this.room = room;
        this.elements = new ArrayList<>();
        this.painters = new ArrayList<>();
        this.originalSize = new Dimension(800, 600);
        factory = new RoomElementFactory(room, originalSize);
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                originalSize = RoomView.this.getSize();
                factory = new RoomElementFactory(room, originalSize);
                repaint();
            }
        });

        addActions();
    }

    private void addActions() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Room width: " + room.getWidth());

                if (room.getWidth() == 0) {
                    CreateRoomFrame createRoomFrame = new CreateRoomFrame(room);
                    createRoomFrame.setVisible(true);
                    createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent e) {
                            repaint();
                        }
                    });
                } else {
                    RoomElement newBed = factory.create("bed", e);
                    CreateRoomFrame createRoomFrame = new CreateRoomFrame(newBed);
                    createRoomFrame.setVisible(true);

                    createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent e) {
                            Painter painter = factory.createPainter(newBed);
                            elements.add(newBed);
                            painters.add(painter);
                            repaint();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (room.getWidth() != 0) {
            Graphics2D g2d = (Graphics2D) g;

            int roomWidth = getWidth();
            int roomHeight = getHeight();

            float roomAspectRatio = (float) room.getWidth() / room.getHeight();

            int padding = 20;
            int availableWidth = roomWidth - 2 * padding;
            int availableHeight = roomHeight - 2 * padding;

            int rectWidth = availableWidth;
            int rectHeight = (int) (rectWidth / roomAspectRatio);

            if (rectHeight > availableHeight) {
                rectHeight = availableHeight;
                rectWidth = (int) (rectHeight * roomAspectRatio);
            }

            int x = (roomWidth - rectWidth) / 2;
            int y = (roomHeight - rectHeight) / 2;

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(x, y, rectWidth, rectHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, rectWidth, rectHeight);

            System.out.println("Scaled room at: " + x + ", " + y + " with size: " + rectWidth + "x" + rectHeight);

            for (Painter painter : painters) {
                if (painter.getElement() instanceof RoomElement) {
                    RoomElement element = (RoomElement) painter.getElement();

                    double scaleX = rectWidth / (double) room.getWidth();
                    double scaleY = rectHeight / (double) room.getHeight();

                    int scaledX = x + (int) (element.getX() * scaleX);
                    int scaledY = y + (int) (element.getY() * scaleY);
                    int scaledWidth = (int) (element.getWidth() * scaleX);
                    int scaledHeight = (int) (element.getHeight() * scaleY);

                    element.setScaledPosition(scaledX, scaledY);
                    element.setScaledSize(scaledWidth, scaledHeight);

                    painter.paint(g2d, element);
                }
            }
        }
    }
}