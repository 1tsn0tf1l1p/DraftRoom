package raf.draft.dsw.model.tree;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.factory.TreeFactory;
import raf.draft.dsw.model.messagegenerator.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.observer.ISubscriber;
import raf.draft.dsw.model.room.RoomElement;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.view.tree.CustomTreeCellRenderer;
import raf.draft.dsw.view.tree.CustomTreeUI;
import raf.draft.dsw.view.tree.TreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
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
        populateTree(root, projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);

        treeView.setUI(new CustomTreeUI());
        configureUIManager();

        treeView.setBackground(Color.LIGHT_GRAY);
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
    public void addChild(TreeItem parent, boolean isRoom, RoomElement roomElement) {
        if (!isRoom) {
            if (parent != null && parent.getNode() instanceof ProjectExplorer) {
                TreeItem item = new TreeItem(factory.createProject((DraftNodeComposite) parent.getNode()));
                parent.add(item);
                ((ProjectExplorer) parent.getNode()).addChild(item.getNode());
            } else if (parent != null && parent.getNode() instanceof Project) {
                TreeItem item = new TreeItem(factory.createBuilding((DraftNodeComposite) parent.getNode()));
                parent.add(item);
                ((Project) parent.getNode()).addChild(item.getNode());
            } else if (parent != null && parent.getNode() instanceof Building) {
                Room room = factory.createRoom((DraftNodeComposite) parent.getNode());
                TreeItem item = new TreeItem(room);
                parent.add(item);
                ((Building) parent.getNode()).addChild(item.getNode());
            } else if (parent != null && parent.getNode() instanceof Room && roomElement != null) {
                TreeItem item = new TreeItem(roomElement);
                parent.add(item);
            }
        } else {
            if (parent != null && (parent.getNode() instanceof Project || parent.getNode() instanceof Building)) {
                Room room = factory.createRoom((DraftNodeComposite) parent.getNode());
                TreeItem item = new TreeItem(room);
                parent.add(item);
                ((DraftNodeComposite) parent.getNode()).addChild(item.getNode());
            }
        }
        setProjectChanged(parent.getNode());
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void setProjectChanged(DraftNode node) {
        Project project = null;
        if (node.getParent() == null) {
            return;
        }
        if (node instanceof Project) {
            project = (Project) node;
        } else if (node.getParent() instanceof Project) {
            project = (Project) node.getParent();
        } else if (node.getParent().getParent() instanceof Project) {
            project = (Project) node.getParent().getParent();
        }
        project.setChanged(true);
    }

    public TreeItem returnTreeItemForRoom(DraftNode node) {
        if (node == null || treeModel == null) {
            return null;
        }

        TreeItem root = (TreeItem) treeModel.getRoot();
        return findNodeInTree(root, node);
    }

    private TreeItem findNodeInTree(TreeItem current, DraftNode node) {
        if (current == null) {
            return null;
        }

        if (current.getNode().equals(node)) {
            return current;
        }

        Enumeration<TreeNode> children = current.children();
        while (children.hasMoreElements()) {
            TreeItem child = (TreeItem) children.nextElement();
            TreeItem found = findNodeInTree(child, node);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public void loadProject(ProjectExplorer projectExplorer, Project project) {
        TreeItem projectItem = new TreeItem(project);

        projectExplorer.addChild(project);

        populateTree(projectItem, project);

        TreeItem root = (TreeItem) treeModel.getRoot();
        root.add(projectItem);

        SwingUtilities.updateComponentTreeUI(treeView);
    }


    @Override
    public void removeChild(TreeItem node) {
        TreeItem parent = (TreeItem) node.getParent();
        if (parent == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(MessageType.ERROR, "No node selected.");
            return;
        }
        parent.remove(node);
        node.getNode().getParent().removeChild(node.getNode());
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    private void populateTree(TreeItem parent, DraftNodeComposite node) {
        for (DraftNode child : node.getChildren()) {
            TreeItem childItem = new TreeItem(child);
            parent.add(childItem);

            if (child instanceof DraftNodeComposite) {
                populateTree(childItem, (DraftNodeComposite) child);
            }
        }
    }

    @Override
    public TreeItem getSelectedItem() {
        return (TreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void setSelectedItem(DraftNode node) {
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
