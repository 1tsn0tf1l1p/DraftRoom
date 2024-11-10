package raf.draft.dsw.view.tab;

import lombok.Getter;
import raf.draft.dsw.model.observer.ISubscriber;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;

@Getter
public class TabView extends JPanel implements ISubscriber {

    private Room room;
    private Color color;

    public TabView(Room room) {
        this.room = room;
        if (room.getParent() instanceof Building) {
            color = ((Building) room.getParent()).getColor();
        } else {
            color = Color.WHITE;
        }

        this.revalidate();
        this.repaint();
        this.setBackground(color);

        this.setLayout(new BorderLayout());
        room.addSubscriber(this);
    }

    @Override
    public <T> void update(T t) {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
