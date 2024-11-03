package raf.draft.dsw.gui.swing.tab;
import com.formdev.flatlaf.FlatLightLaf;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class TabView extends JPanel implements ISubscriber {

    private Room room;

    public TabView(Room room) {
        this.room = room;
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        room.addSubscriber(this);
    }

    @Override
    public <T> void update(T t) {
        this.repaint();
    }
}
