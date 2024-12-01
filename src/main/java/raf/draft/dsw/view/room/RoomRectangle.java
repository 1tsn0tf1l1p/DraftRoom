package raf.draft.dsw.view.room;

import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoomRectangle extends JComponent {
    private Room room;
    private List<Painter> painters;

    public RoomRectangle(Room room, List<Painter> painters) {
        this.room = room;
        this.painters = painters;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Check if the room has a valid width
        if (room.getWidth() != 0) {
            Graphics2D g2d = (Graphics2D) g;

            // Get the dimensions of the panel
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Calculate the aspect ratio of the room
            float roomAspectRatio = (float) room.getWidth() / room.getHeight();

            // Set padding and calculate available dimensions
            int padding = 20;
            int availableWidth = panelWidth - 2 * padding;
            int availableHeight = panelHeight - 2 * padding;

            // Determine rectangle dimensions while maintaining aspect ratio
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
                    RoomElement element = (RoomElement) painter.getElement();

                    double scaleX = rectWidth / (double) room.getWidth();
                    double scaleY = rectHeight / (double) room.getHeight();

                    int scaledX = padding + (int) (element.getX() * scaleX);
                    int scaledY = padding + (int) (element.getY() * scaleY);
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
