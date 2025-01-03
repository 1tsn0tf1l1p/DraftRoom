package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.serialization.Serializer;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class LoadAction extends AbstractRoomAction {
    private final Serializer serializer;

    public LoadAction() {
        putValue(NAME, "Load Project");
        putValue(SMALL_ICON, loadIcon("/images/load.png"));
        serializer = new Serializer();
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Project");
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Project loadedProject = serializer.load(file, Project.class);

                ProjectExplorer projectExplorer =
                        ApplicationFramework.getInstance().getProjectExplorer();

                loadedProject.setParent(projectExplorer);
                DraftTreeImplementation draftTree = ApplicationFramework.getInstance().getTree();

                draftTree.loadProject(projectExplorer,loadedProject);

                System.out.println("Loaded project: " + loadedProject.getIme());
                JOptionPane.showMessageDialog(null,
                        "Project loaded successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                loadedProject.setChanged(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error loading project: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}