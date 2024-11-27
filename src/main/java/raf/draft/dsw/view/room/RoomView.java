package raf.draft.dsw.view.room;

import lombok.Getter;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.frames.CreateRoomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
public class RoomView extends JPanel {
    private Room room;

    public RoomView(Room room) {
        this.room = room;
        addActions();
    }
    private void addActions(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (room.getHeight()==0){
                    CreateRoomFrame createRoomFrame = new CreateRoomFrame(room);
                    createRoomFrame.setVisible(true);
                };
            }
        });
    }

}
