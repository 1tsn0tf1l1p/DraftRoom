package raf.draft.dsw.controller.actions.global;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;
import raf.draft.dsw.model.serialization.Serializer;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.tree.DraftTreeImplementation;
import raf.draft.dsw.view.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class LoadAction extends AbstractRoomAction {
    private final Serializer serializer;

    public LoadAction() {
        putValue(NAME, "Load Project");
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

                projectExplorer.addChild(loadedProject);
                loadedProject.setParent(projectExplorer);

                DraftTreeImplementation draftTree =
                        (DraftTreeImplementation) ApplicationFramework.getInstance().getTree();
                JTree newTree = draftTree.generateTree(projectExplorer);

                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.getPanel().remove(mainFrame.getTree());
                mainFrame.getPanel().add(newTree, BorderLayout.WEST);
                mainFrame.setTree(newTree);
                mainFrame.revalidate();
                mainFrame.repaint();

                System.out.println("Loaded project: " + loadedProject.getIme());
                JOptionPane.showMessageDialog(null,
                        "Project loaded successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error loading project: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}