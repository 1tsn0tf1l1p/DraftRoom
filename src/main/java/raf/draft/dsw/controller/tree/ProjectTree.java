package raf.draft.dsw.controller.tree;

import raf.draft.dsw.controller.observer.TreeSubscriber;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ProjectTree implements Tree {

    private TreeView treeView;
    private DefaultTreeModel treeModel;
    private List<TreeSubscriber> treeSubscribers;

    //private final AbstractFactory factory = new NodeFactory();

    @Override
    public JTree generateTree(ProjectExplorer projectExplorer) {
        TreeItem root = new TreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);// Need root
        treeView = new TreeView(treeModel);
        treeView.setUI(new CustomTreeUI());
        uimanager();
        treeView.repaint();
        treeSubscribers = new ArrayList<>();
        System.out.println(treeView);
        return treeView;
    }

    private void uimanager() {
        UIManager.put("Tree.line", Color.BLACK);
        UIManager.put("Tree.hash", Color.BLACK);
        UIManager.put("Tree.expandedIcon", new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
        UIManager.put("Tree.collapsedIcon", new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
    }

    @Override
    public void addChild(TreeItem parent) {
        if(parent != null && parent.getNode() instanceof Project) {
            TreeItem item = new TreeItem(factory.createCanvas("testCanvas", parent.getNode(), 400, 400));
            parent.add(item);
            ((Project) parent.getNode()).addChild(item.getNode());
            System.out.println(item.toString());
        }
        else if(parent != null && parent.getNode() instanceof ProjectExplorer) {
            TreeItem item = new TreeItem(factory.createProject("testProject", parent.getNode(), "pera", "~/Documents"));
            parent.add(item);
            ((ProjectExplorer) parent.getNode()).addChild(item.getNode());
            System.out.println(item.toString());
        }
        // For refresh
        notify("");
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(TreeItem node) {
        TreeItem parent = (TreeItem) node.getParent();
        System.out.println(node.getParent());
        parent.remove(node);
        ((Composite) node.getNode().getParent()).removeChild(node.getNode());
        notify("");
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void setSelectedItem(DraftNode node) {
    }

    @Override
    public TreeItem getSelectedItem(DraftNode node) {
        return (TreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void addSubscriber(TreeSubscriber subscriber) {
        treeSubscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(TreeSubscriber subscriber) {
        treeSubscribers.remove(subscriber);
    }

    @Override
    public <T> void notify(T t) {
        treeSubscribers.stream().forEach(e -> e.TreeUpdate(""));
    }
}
