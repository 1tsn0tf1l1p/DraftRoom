package raf.draft.dsw.model.nodes;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class DraftNodeComposite extends DraftNode {

    private List<DraftNode> children;

    public DraftNodeComposite(String ime, DraftNodeComposite parent) {
        super(ime,parent);
        this.children = new ArrayList<>();
    }

    public void addChild(DraftNode child) {
        children.add(child);
    }
    public void removeChild(DraftNode child) {
        children.remove(child);
    }



}
