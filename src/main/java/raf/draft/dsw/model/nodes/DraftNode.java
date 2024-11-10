package raf.draft.dsw.model.nodes;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class DraftNode {
    private String ime;
    private DraftNodeComposite parent;

    public DraftNode(String ime, DraftNodeComposite parent) {
        this.ime = ime;
        this.parent = parent;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setParent(DraftNodeComposite parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DraftNode draftNode = (DraftNode) object;
        return Objects.equals(ime, draftNode.ime) && Objects.equals(parent, draftNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, parent);
    }
}
