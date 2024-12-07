package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.view.frames.MainFrame;

import java.awt.event.ActionEvent;

public class AddNodeStateAction extends AbstractRoomAction {
    private String name;
    public AddNodeStateAction(String name,String path){
        this.name = name;
        putValue(SMALL_ICON, loadIcon(path));
        putValue(NAME, "Add " + name);
        putValue(SHORT_DESCRIPTION, "Add node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPanel().getRoomView().setSelectedItem(name);
    }
}
