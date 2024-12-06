package raf.draft.dsw.view.room;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.state.EditRoomState;
import raf.draft.dsw.controller.state.SelectState;
import raf.draft.dsw.controller.state.ZoomState;
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
    private double zoomFactor = 1.0;
    private Room room;
    private List<Painter> painters;
    private RoomElementFactory factory;
    private RoomRectangle roomRectangle;
    private RoomState currentState;
    private Dimension originalSize;
    private Rectangle selectionBox;

    private JComponent selectionBoxLayer;

    public RoomView(Room room) {
        this.room = room;
        this.painters = new CopyOnWriteArrayList<>();
        this.originalSize = new Dimension(800, 600);
        factory = new RoomElementFactory(room, originalSize);

        currentState = new EditRoomState(this);

        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        add(layeredPane, BorderLayout.CENTER);

        roomRectangle = new RoomRectangle(room, this, painters);
        roomRectangle.setBounds(0, 0, originalSize.width, originalSize.height);
        layeredPane.add(roomRectangle, JLayeredPane.DEFAULT_LAYER);

        selectionBoxLayer = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                if (selectionBox != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    int x = (int) (selectionBox.x * zoomFactor);
                    int y = (int) (selectionBox.y * zoomFactor);
                    int width = (int) (selectionBox.width * zoomFactor);
                    int height = (int) (selectionBox.height * zoomFactor);

                    g2d.setColor(new Color(0, 0, 255, 50));
                    g2d.fillRect(x, y, width, height);
                    g2d.setColor(Color.BLUE);
                    g2d.drawRect(x, y, width, height);
                }
            }
        };
        selectionBoxLayer.setBounds(0, 0, originalSize.width, originalSize.height);
        layeredPane.add(selectionBoxLayer, JLayeredPane.PALETTE_LAYER);

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

        this.addMouseWheelListener(e -> {
            if (currentState instanceof ZoomState) {
                ((ZoomState) currentState).handleMouseWheelMoved(e);
            }
        });

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                adjustSizes(layeredPane);
                repaint();
            }
        });

        addChildren();
    }

    private void adjustSizes(JLayeredPane layeredPane) {
        Dimension newSize = getSize();
        originalSize = newSize;

        roomRectangle.setBounds(0, 0, newSize.width, newSize.height);
        selectionBoxLayer.setBounds(0, 0, newSize.width, newSize.height);
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
        if (currentState instanceof SelectState) {
            if (selectionBox == null) {
                Point startPoint = new Point((int) (e.getX() / zoomFactor), (int) (e.getY() / zoomFactor));
                selectionBox = new Rectangle(startPoint);
                System.out.println("Initialized selectionBox at: " + selectionBox);
            }

            Point currentPoint = new Point((int) (e.getX() / zoomFactor), (int) (e.getY() / zoomFactor));

            int x = Math.min(selectionBox.x, currentPoint.x);
            int y = Math.min(selectionBox.y, currentPoint.y);
            int width = Math.abs(selectionBox.x - currentPoint.x);
            int height = Math.abs(selectionBox.y - currentPoint.y);

            selectionBox.setBounds(x, y, width, height);
            System.out.println("Updated selectionBox to: " + selectionBox);

            repaint();
        }

        currentState.handleMouseDrag(e);
    }


    private void handleMouseRelease(MouseEvent e) {
        repaint();
        currentState.handleMouseRelease(e);
    }
}
