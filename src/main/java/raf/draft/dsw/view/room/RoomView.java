package raf.draft.dsw.view.room;

import lombok.Getter;
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
import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomView extends JPanel {
    private Room room;
    private List<Painter> painters;
    private RoomElementFactory factory;
    private RoomRectangle roomRectangle;
    private RoomState currentState;
    private Dimension originalSize;

    public RoomView(Room room) {
        this.room = room;
        this.painters = new ArrayList<>();
        this.originalSize = new Dimension(800, 600);
        factory = new RoomElementFactory(room, originalSize);

        currentState = new EditRoomState(this);

        setLayout(new BorderLayout());
        roomRectangle = new RoomRectangle(room, painters);
        add(roomRectangle, BorderLayout.CENTER);

        roomRectangle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
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
        if (room.getWidth()!=0) {
            this.currentState = state;
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.WARNING,"You must initialize the room first!");
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
        System.out.println("Room width: " + room.getWidth());
        currentState.handleMouseClick(e);
    }
}
