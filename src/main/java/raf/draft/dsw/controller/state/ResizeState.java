package raf.draft.dsw.controller.state;

import raf.draft.dsw.model.patterns.state.RoomState;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.view.room.Painter;
import raf.draft.dsw.view.room.RoomView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;

public class ResizeState implements RoomState {
    private final int RESIZE_THRESHOLD = 10;
    private RoomView roomView;
    private RoomElement selectedElement;
    private boolean isResizing;
    private int initialX;
    private int initialY;
    private int initialWidth;
    private int initialHeight;

    public ResizeState(RoomView roomView) {
        this.roomView = roomView;
        this.isResizing = false;
    }

    @Override
    public void handleMouseClick(MouseEvent e) {
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        Point scaledPoint = unscalePoint(e.getPoint());

        for (Painter painter : roomView.getPainters()) {
            RoomElement element = painter.getElement();
            if (element != null && isWithinResizeArea(element, scaledPoint)) {
                selectedElement = element;
                isResizing = true;

                initialWidth = element.getWidth();
                initialHeight = element.getHeight();

                initialX = scaledPoint.x;
                initialY = scaledPoint.y;
                break;
            }
        }
    }

    @Override
    public void handleMouseDrag(MouseEvent e) {
        if (isResizing && selectedElement != null) {
            Point scaledPoint = unscalePoint(e.getPoint());

            int deltaXGlobal = scaledPoint.x - initialX;
            int deltaYGlobal = scaledPoint.y - initialY;

            Point2D localDelta = convertDeltaToLocal(deltaXGlobal, deltaYGlobal, selectedElement);

            int newWidth, newHeight;
            int angle = selectedElement.getRotateRatio() % 360;

            if (angle < 0) {
                angle += 360;
            }

            if (angle == 270) {
                newHeight = initialWidth - (int) localDelta.getX();
                newWidth = initialHeight + (int) localDelta.getY();
            } else if (angle == 90) {
                newWidth = initialWidth - (int) localDelta.getY();
                newHeight = initialHeight + (int) localDelta.getX();
            } else if (angle == 180) {
                newWidth = initialWidth - (int) localDelta.getX();
                newHeight = initialHeight - (int) localDelta.getY();
            } else {
                newWidth = initialWidth + (int) localDelta.getX();
                newHeight = initialHeight + (int) localDelta.getY();
            }

            newWidth = Math.max(newWidth, 10);
            newHeight = Math.max(newHeight, 10);

            int prevWidth = selectedElement.getWidth();
            int prevHeight = selectedElement.getHeight();
            int prevX = selectedElement.getX();
            int prevY = selectedElement.getY();
            selectedElement.setSize(newWidth, newHeight);

            boolean checkInt = checkIntersection(selectedElement, 10);
            boolean snap = snapToEdge(selectedElement);

            if (checkInt || snap) {
                selectedElement.setSize(prevWidth, prevHeight);
                selectedElement.setX(prevX);
                selectedElement.setY(prevY);
            }

            roomView.repaint();
        }
    }


    @Override
    public void handleKeyPress(KeyEvent e) {
    }

    @Override
    public void handleMouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e) {
        isResizing = false;
        selectedElement = null;
    }

    @Override
    public void enterState() {
        isResizing = false;
        selectedElement = null;
    }

    @Override
    public void exitState() {
        isResizing = false;
        selectedElement = null;
    }

    private boolean isWithinResizeArea(RoomElement element, Point point) {
        double angle = Math.toRadians(element.getRotateRatio());
        double w = element.getScaledWidth();
        double h = element.getScaledHeight();

        Point2D.Double topLeft = new Point2D.Double(0, 0);
        Point2D.Double topRight = new Point2D.Double(w, 0);
        Point2D.Double bottomLeft = new Point2D.Double(0, h);
        Point2D.Double bottomRight = new Point2D.Double(w, h);

        AffineTransform transform = new AffineTransform();
        transform.translate(element.getScaledX() + w / 2.0, element.getScaledY() + h / 2.0);
        transform.rotate(angle);
        transform.translate(-w / 2.0, -h / 2.0);

        Point2D topLeftGlobal = transform.transform(topLeft, null);
        Point2D topRightGlobal = transform.transform(topRight, null);
        Point2D bottomLeftGlobal = transform.transform(bottomLeft, null);
        Point2D bottomRightGlobal = transform.transform(bottomRight, null);

        Point2D visualBottomRight = bottomRightGlobal;
        for (Point2D corner : Arrays.asList(topLeftGlobal, topRightGlobal, bottomLeftGlobal, bottomRightGlobal)) {
            if (corner.getY() > visualBottomRight.getY() ||
                    (corner.getY() == visualBottomRight.getY() && corner.getX() > visualBottomRight.getX())) {
                visualBottomRight = corner;
            }
        }

        double dx = point.getX() - visualBottomRight.getX();
        double dy = point.getY() - visualBottomRight.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance <= RESIZE_THRESHOLD;
    }

    private Point unscalePoint(Point point) {
        double zoomFactor = roomView.getZoomFactor();
        return new Point((int) (point.x / zoomFactor), (int) (point.y / zoomFactor));
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
        Rectangle elementBounds = element.getBounds();

        for (Painter otherPainter : roomView.getPainters()) {
            RoomElement otherElement = otherPainter.getElement();

            if (element == otherElement) {
                continue;
            }

            Rectangle otherBounds = otherElement.getBounds();

            int dx = Math.max(0, Math.max(otherBounds.x - (elementBounds.x + elementBounds.width),
                    elementBounds.x - (otherBounds.x + otherBounds.width)));
            int dy = Math.max(0, Math.max(otherBounds.y - (elementBounds.y + elementBounds.height),
                    elementBounds.y - (otherBounds.y + otherBounds.height)));

            if (Math.sqrt(dx * dx + dy * dy) <= padding) {
                return true;
            }
        }
        return false;
    }

    private Point2D convertDeltaToLocal(int deltaXGlobal, int deltaYGlobal, RoomElement element) {
        double angle = Math.toRadians(element.getRotateRatio());

        double cosA = Math.cos(-angle);
        double sinA = Math.sin(-angle);

        double localDX = deltaXGlobal * cosA - deltaYGlobal * sinA;
        double localDY = deltaXGlobal * sinA + deltaYGlobal * cosA;

        return new Point2D.Double(localDX, localDY);
    }
}
