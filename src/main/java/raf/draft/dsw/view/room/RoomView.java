package raf.draft.dsw.view.room;

import lombok.Getter;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;

@Getter
public class RoomView extends JPanel {
    private Room room;

    public RoomView(Room room) {
        this.room = room;
    }




}
