package raf.draft.dsw.controller.tree.mvc;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class TreeItem extends DefaultMutableTreeNode {

    private DraftNode node;

    public TreeItem(DraftNode node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return node.getIme();
    }

    public void setName(String name) {
        node.setIme(name);
    }


}
