package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.patterns.observer.IPublisher;
import raf.draft.dsw.model.patterns.observer.ISubscriber;
import raf.draft.dsw.model.serialization.Serializer;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.bars.Panel;
import raf.draft.dsw.view.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SaveAsAction extends AbstractRoomAction implements IPublisher {
    private Serializer serializer;
    private Panel panel;
    public SaveAsAction() {
        putValue(NAME, "Save");
        putValue(SMALL_ICON, loadIcon("/images/save-as.png"));
        this.serializer = new Serializer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem selectedItem = MainFrame.getInstance().getTree().getSelectedItem();

        Project selectedProject = null;
        if (MainFrame.getInstance().getPanel().getRoomView() !=null){
            Room focusedRoom = MainFrame.getInstance().getPanel().getRoomView().getRoom();
            if (focusedRoom.getParent() instanceof Project) selectedProject = (Project) focusedRoom.getParent();
            if (focusedRoom.getParent().getParent() instanceof Project) selectedProject = (Project) focusedRoom.getParent().getParent();
        }
        if (selectedItem.getNode() instanceof Project) {
            selectedProject = (Project) selectedItem.getNode();
        }

        if (selectedProject == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No project selected.");
            return;
        }
        String filePath = selectedProject.getPath();

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DraftRoom files (*.dr)", "dr");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File(selectedProject.getIme() + ".dr"));
        fileChooser.setSelectedFile(new File(selectedProject.getIme() + ".dr"));
        fileChooser.setDialogTitle("Save Project As");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePath = file.getAbsolutePath();
            if (!filePath.endsWith(serializer.getCustomExtension())) {
                filePath = filePath + serializer.getCustomExtension();
            }
            selectedProject.setPath(filePath);
            notifySubscribers(null);
        } else {
            return;
        }
        try {
            serializer.save(selectedProject, new File(filePath));
            JOptionPane.showMessageDialog(null, "Project saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }

    @Override
    public <T> void notifySubscribers(T t) {
        panel = MainFrame.getInstance().getPanel();
        panel.update(t);
    }
}
