package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.factory.RoomElementFactory;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.state.RoomState;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.frames.CreateRoomFrame;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AddState implements RoomState {
    private Dimension originalSize;
    private RoomView roomView;
    private RoomElementFactory factory;
    private DraftTreeImplementation tree;

    public AddState(RoomView roomView) {
        this.roomView = roomView;
        this.originalSize = roomView.getOriginalSize();
        this.factory = roomView.getFactory();
        tree = ApplicationFramework.getInstance().getTree();
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
        Point unscaledPoint = unscalePoint(e.getPoint());

        MouseEvent unscaledEvent = new MouseEvent(
                e.getComponent(),
                e.getID(),
                e.getWhen(),
                e.getModifiersEx(),
                (int) unscaledPoint.getX(),
                (int) unscaledPoint.getY(),
                e.getClickCount(),
                e.isPopupTrigger(),
                e.getButton()
        );

        RoomElement newElement = factory.create(roomView.getSelectedItem(), unscaledEvent);

        Painter painter = factory.createPainter(newElement);
        roomView.getPainters().add(painter);
        roomView.getRoom().addChild(newElement);
        CreateRoomFrame createRoomFrame = new CreateRoomFrame(newElement);
        createRoomFrame.setVisible(true);

        createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                roomView.repaint();
                TreeItem treeItem = tree.returnTreeItemForRoom(roomView.getRoom());
                tree.addChild(treeItem, false, newElement);
            }
        });
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
    }

    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
    }

    @Override
    public void enterState() {
    }

    @Override
    public void exitState() {
    }

    private Point unscalePoint(Point point) {
        double zoomFactor = roomView.getZoomFactor();
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
    }
}
