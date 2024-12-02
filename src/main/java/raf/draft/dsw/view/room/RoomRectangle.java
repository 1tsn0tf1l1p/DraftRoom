package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomRectangle extends JComponent {
    private Room room;
    private RoomView roomView;
    private List<Painter> painters;

    public RoomRectangle(Room room, RoomView roomView, List<Painter> painters) {
        this.room = room;
        this.painters = painters;
        this.roomView = roomView;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (room.getWidth() != 0) {
            Graphics2D g2d = (Graphics2D) g;
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            float roomAspectRatio = (float) room.getWidth() / room.getHeight();
            int padding = 20;
            int availableWidth = panelWidth - 2 * padding;
            int availableHeight = panelHeight - 2 * padding;
            int rectWidth = availableWidth;
            int rectHeight = (int) (rectWidth / roomAspectRatio);

            if (rectHeight > availableHeight) {
                rectHeight = availableHeight;
                rectWidth = (int) (rectHeight * roomAspectRatio);
            }
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(padding, padding, rectWidth, rectHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(padding, padding, rectWidth, rectHeight);

            for (Painter painter : painters) {
                if (painter.getElement() != null) {
                    RoomElement element = painter.getElement();
                    double scaleX = roomView.getWidth() / (double) room.getWidth();
                    double scaleY = roomView.getHeight() / (double) room.getHeight();
                    double uniformScale = Math.min(scaleX, scaleY);
                    int scaledX = (int) (element.getX() * uniformScale);
                    int scaledY = (int) (element.getY() * uniformScale);
                    int scaledWidth = (int) (element.getWidth() * uniformScale);
                    int scaledHeight = (int) (element.getHeight() * uniformScale);
                    element.setScaledPosition(scaledX, scaledY);
                    element.setScaledSize(scaledWidth, scaledHeight);
                    painter.paint(g2d, element);
                }
            }

        }
    }
}
