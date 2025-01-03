package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.factory.RoomElementFactory;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.patterns.state.RoomState;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.view.commands.concrete_commands.AddCommand;
import raf.draft.dsw.view.frames.CreateRoomFrame;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;

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

        if (!placeElementWithoutIntersection(newElement)) {
            return;
        }

        AddCommand addCommand = new AddCommand(roomView, newElement);

        CreateRoomFrame createRoomFrame = new CreateRoomFrame(newElement);
        createRoomFrame.setVisible(true);

        createRoomFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent wEvent) {
                if (createRoomFrame.isConfirmed()) {
                    if (checkIntersection(newElement, 10) || snapToEdge(newElement)) {
                        ApplicationFramework.getInstance().getMessageGenerator()
                                .createMessage(MessageType.WARNING, "Invalid element position!");
                        return;
                    }
                    roomView.getCommandManager().addCommand(addCommand);
                    roomView.repaint();
                } else {
                    roomView.getCommandManager().addCommand(addCommand);
                    roomView.getCommandManager().undoCommand();
                }
            }
        });
    }

    private boolean placeElementWithoutIntersection(RoomElement newElement) {
        int roomWidth = roomView.getRoom().getWidth();
        int roomHeight = roomView.getRoom().getHeight();

        if (roomWidth <= 0 || roomHeight <= 0) {
            return true;
        }

        int padding = 0;
        if (!isFreeSpot(newElement, padding)) {
            int maxAttempts = 500;
            int step = 10;
            int originalX = newElement.getX();
            int originalY = newElement.getY();

            boolean foundSpot = false;
            outerLoop:
            for (int dx = 0; dx <= maxAttempts; dx += step) {
                for (int dy = 0; dy <= maxAttempts; dy += step) {
                    int[] offsetsX = {dx, -dx, dx, -dx};
                    int[] offsetsY = {dy, dy, -dy, -dy};

                    for (int i = 0; i < offsetsX.length; i++) {
                        int newX = originalX + offsetsX[i];
                        int newY = originalY + offsetsY[i];

                        if (newX < 0 || newY < 0
                                || newX + newElement.getWidth() > roomWidth
                                || newY + newElement.getHeight() > roomHeight) {
                            continue;
                        }

                        newElement.setX(newX);
                        newElement.setY(newY);

                        if (isFreeSpot(newElement, padding)) {
                            foundSpot = true;
                            break outerLoop;
                        }
                    }
                }
            }

            if (!foundSpot) {
                newElement.setX(originalX);
                newElement.setY(originalY);
                return false;
            }
        }

        return true;
    }

    private boolean isFreeSpot(RoomElement element, int padding) {
        return !checkIntersection(element, padding);
    }

    private boolean snapToEdge(RoomElement element) {
        int roomWidth = roomView.getRoom().getWidth();
        int roomHeight = roomView.getRoom().getHeight();
        int padding = 10;
        boolean snapped = false;

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(element.getRotateRatio()),
                element.getX() + element.getWidth() / 2.0,
                element.getY() + element.getHeight() / 2.0);

        Point2D[] points = {
                transform.transform(new Point2D.Double(element.getX(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX(), element.getY() + element.getHeight()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY() + element.getHeight()), null)
        };

        double minX = Arrays.stream(points).mapToDouble(Point2D::getX).min().orElse(0);
        double maxX = Arrays.stream(points).mapToDouble(Point2D::getX).max().orElse(0);
        double minY = Arrays.stream(points).mapToDouble(Point2D::getY).min().orElse(0);
        double maxY = Arrays.stream(points).mapToDouble(Point2D::getY).max().orElse(0);

        int rotatedWidth = (int) (maxX - minX);
        int rotatedHeight = (int) (maxY - minY);

        if (minX < padding) {
            element.setX(padding);
            snapped = true;
        }
        if (minY < padding) {
            element.setY(padding);
            snapped = true;
        }
        if (maxX > roomWidth - padding) {
            element.setX(roomWidth - rotatedWidth);
            snapped = true;
        }
        if (maxY > roomHeight - padding) {
            element.setY(roomHeight - rotatedHeight);
            snapped = true;
        }

        return snapped;
    }

    private boolean checkIntersection(RoomElement element, int padding) {
        Rectangle rotatedBounds = getRotatedBounds(element);

        for (Painter otherPainter : roomView.getPainters()) {
            RoomElement otherElement = otherPainter.getElement();
            if (otherElement == null || element == otherElement) continue;

            Rectangle otherRotatedBounds = getRotatedBounds(otherElement);

            Rectangle paddedBounds = new Rectangle(
                    otherRotatedBounds.x - padding,
                    otherRotatedBounds.y - padding,
                    otherRotatedBounds.width + 2 * padding,
                    otherRotatedBounds.height + 2 * padding
            );

            if (rotatedBounds.intersects(paddedBounds)) {
                return true;
            }
        }

        return false;
    }

    private Rectangle getRotatedBounds(RoomElement element) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(
                Math.toRadians(element.getRotateRatio()),
                element.getX() + element.getWidth() / 2.0,
                element.getY() + element.getHeight() / 2.0
        );

        Point2D[] points = {
                transform.transform(new Point2D.Double(element.getX(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY()), null),
                transform.transform(new Point2D.Double(element.getX(), element.getY() + element.getHeight()), null),
                transform.transform(new Point2D.Double(element.getX() + element.getWidth(), element.getY() + element.getHeight()), null)
        };

        double minX = Arrays.stream(points).mapToDouble(Point2D::getX).min().orElse(0);
        double maxX = Arrays.stream(points).mapToDouble(Point2D::getX).max().orElse(0);
        double minY = Arrays.stream(points).mapToDouble(Point2D::getY).min().orElse(0);
        double maxY = Arrays.stream(points).mapToDouble(Point2D::getY).max().orElse(0);

        return new Rectangle((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
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
