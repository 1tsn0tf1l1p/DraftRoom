package raf.draft.dsw.controller.tree;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.controller.tree.mvc.CustomTreeCellRenderer;
import raf.draft.dsw.controller.tree.mvc.CustomTreeUI;
import raf.draft.dsw.controller.tree.mvc.TreeItem;
import raf.draft.dsw.controller.tree.mvc.TreeView;
import raf.draft.dsw.model.factory.TreeFactory;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DraftTreeImplementation implements DraftTree {

    private TreeView treeView;
    private DefaultTreeModel treeModel;
    private List<ISubscriber> treeSubscribers;

    private TreeFactory factory = new TreeFactory();

    @Override
    public JTree generateTree(ProjectExplorer projectExplorer) {
        TreeItem root = new TreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);

        treeView.setUI(new CustomTreeUI());
        configureUIManager();

        treeView.setBackground(Color.GRAY);
        treeView.setShowsRootHandles(true);
        treeView.setRootVisible(true);
        treeSubscribers = new ArrayList<>();
        treeView.setCellRenderer(new CustomTreeCellRenderer());
        return treeView;
    }

    private void configureUIManager() {
        UIManager.put("Tree.line", Color.BLACK);
        UIManager.put("Tree.hash", Color.BLACK);

        UIManager.put("Tree.selectionBackground", Color.DARK_GRAY);
        UIManager.put("Tree.textBackground", Color.GRAY);

        UIManager.put("Tree.expandedIcon", UIManager.getIcon("Tree.expandedIcon"));
        UIManager.put("Tree.collapsedIcon", UIManager.getIcon("Tree.collapsedIcon"));
    }

    @Override
    public void addChild(TreeItem parent, boolean isRoom) {
        if(!isRoom) {
            if(parent != null && parent.getNode() instanceof ProjectExplorer) {
                TreeItem item = new TreeItem(factory.createProject("New Project", parent.getNode(), "", "~/Documents"));
                parent.add(item);
                ((ProjectExplorer) parent.getNode()).addChild(item.getNode());
            }

            else if(parent != null && parent.getNode() instanceof Project) {
                TreeItem item = new TreeItem(factory.createBuilding("New Building", parent.getNode()));
                parent.add(item);
                ((Project) parent.getNode()).addChild(item.getNode());
            }

            else if(parent != null && parent.getNode() instanceof Building) {
                TreeItem item = new TreeItem(factory.createRoom("New Room", parent.getNode()));
                parent.add(item);
                ((Building) parent.getNode()).addChild(item.getNode());
            }
        }
        else {
            if(parent != null && (parent.getNode() instanceof Project || parent.getNode() instanceof Building)) {
                TreeItem item = new TreeItem(factory.createRoom("New Room", parent.getNode()));
                parent.add(item);
                ((DraftNodeComposite) parent.getNode()).addChild(item.getNode());
            }
        }

        notifySubscribers(null);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(TreeItem node) {
        TreeItem parent = (TreeItem) node.getParent();
        parent.remove(node);
        ((DraftNodeComposite) node.getNode().getParent()).removeChild(node.getNode());
        notifySubscribers("");
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void setSelectedItem(DraftNode node) {
    }

    @Override
    public TreeItem getSelectedItem() {
        return (TreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        treeSubscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        treeSubscribers.remove(subscriber);
    }

    @Override
    public <T> void notifySubscribers(T t) {
        treeSubscribers.forEach(e -> e.update(""));
    }
}
