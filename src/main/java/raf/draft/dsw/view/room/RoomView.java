package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.state.EditRoomState;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.factory.RoomElementFactory;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class RoomView extends JPanel {
    private Room room;
    private List<Painter> painters;
    private RoomElementFactory factory;
    private RoomRectangle roomRectangle;
    private RoomState currentState;
    private Dimension originalSize;
    private Rectangle selectionBox;

    public RoomView(Room room) {
        this.room = room;
        this.painters = new CopyOnWriteArrayList<>();
        this.originalSize = new Dimension(800, 600);
        factory = new RoomElementFactory(room, originalSize);

        currentState = new EditRoomState(this);

        setLayout(new BorderLayout());
        roomRectangle = new RoomRectangle(room, this, painters);
        add(roomRectangle, BorderLayout.CENTER);

        roomRectangle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
        roomRectangle.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }
        });

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                originalSize = RoomView.this.getSize();
                factory = new RoomElementFactory(room, originalSize);
                repaint();
            }
        });

        addChildren();
    }

    public void changeState(RoomState state) {
        if (room.getWidth() != 0) {
            this.currentState = state;
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING, "You must initialize the room first!");
        }
    }

    private void addChildren() {
        for (DraftNode element : room.getChildren()) {
            Painter painter = factory.createPainter(element);
            painters.add(painter);
        }
        roomRectangle.repaint();
    }

    private void handleMouseClick(MouseEvent e) {
        currentState.handleMouseClick(e);
    }

    private void handleMousePress(MouseEvent e) {
        currentState.handleMousePressed(e);
    }



    private void handleMouseDrag(MouseEvent e) {
        if (selectionBox != null) {
            int x = Math.min(selectionBox.x, e.getX());
            int y = Math.min(selectionBox.y, e.getY());
            int width = Math.abs(selectionBox.x - e.getX());
            int height = Math.abs(selectionBox.y - e.getY());

            selectionBox.setBounds(x, y, width, height);
            repaint();
        }
        currentState.handleMouseDrag(e);
    }

    private void handleMouseRelease(MouseEvent e) {
        currentState.handleMouseRelease(e);
        selectionBox = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (selectionBox != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0, 0, 255, 50));
            g2.fill(selectionBox);
            g2.setColor(Color.BLUE);
            g2.draw(selectionBox);
        }
    }
}
