package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.serialization.Serializer;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.view.frames.MainFrame;
import raf.draft.dsw.view.tree.TreeView;

import javax.swing.*;
import java.awt.desktop.AppForegroundListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class LoadAction extends AbstractRoomAction {
    private final Serializer serializer;
    private TreeView treeView;
    public LoadAction() {
        putValue(NAME, "Load Project");
        serializer = new Serializer();
        treeView = ApplicationFramework.getInstance().getTree().getTreeView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Project");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Project loadedProject = serializer.load(file, Project.class);
                System.out.println("Loaded Project: " + loadedProject.getIme());
                System.out.println("Children count: " + loadedProject.getChildren().size());

                ApplicationFramework.getInstance().getProjectExplorer().addChild(loadedProject);
                treeView.expandPath(treeView.getSelectionPath());
                SwingUtilities.updateComponentTreeUI(treeView);

                JOptionPane.showMessageDialog(null, "Project loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
