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
    private boolean roomDrawn = false;

    public RoomView(Room room) {
        this.room = room;
        this.elements = new ArrayList<>();
        this.painters = new ArrayList<>();
        factory = new RoomElementFactory(room);
        addActions();
    }

    private void addActions() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!roomDrawn) {
                    CreateRoomFrame createRoomFrame = new CreateRoomFrame(room);
                    createRoomFrame.setVisible(true);
                    createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter(){
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent e) {
                            repaint();
                        }
                    });
                    roomDrawn=true;
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
        Graphics2D g2d = (Graphics2D) g;
        if (room.getWidth()>0) {
            int roomWidth = getWidth();
            int roomHeight = getHeight();

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(50, 50, roomWidth, roomHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(50, 50, roomWidth, roomHeight);
        }
        for (Painter painter : painters) {
            painter.paint(g2d, painter.getElement());
        }

    }
}
