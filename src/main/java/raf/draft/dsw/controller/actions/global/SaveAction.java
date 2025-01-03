package raf.draft.dsw.controller.actions.global;

import com.sun.tools.javac.Main;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.serialization.Serializer;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.model.tree.TreeItem;
import raf.draft.dsw.view.frames.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SaveAction extends AbstractRoomAction {
    private Serializer serializer;
    public SaveAction() {
        putValue(NAME, "Save");
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
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
        if (!selectedProject.isChanged()) return;

        String filePath = selectedProject.getPath();
        File file = new File(filePath);
        if (!file.exists()) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("DraftRoom files (*.dr)", "dr");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(selectedProject.getIme() + ".dr"));
            fileChooser.setSelectedFile(new File(selectedProject.getIme() + ".dr"));
            fileChooser.setDialogTitle("Save Project As");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File newFile = fileChooser.getSelectedFile();
                filePath = newFile.getAbsolutePath();
                selectedProject.setPath(filePath);
            } else {
                return;
            }
            selectedProject.setChanged(false);
        }
        try {
            serializer.save(selectedProject, new File(filePath));
            JOptionPane.showMessageDialog(null, "Project saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
