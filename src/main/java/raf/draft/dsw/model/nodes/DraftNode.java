package raf.draft.dsw.model.nodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DraftNode {
    private String ime;
    private DraftNode parent;

    public DraftNode(String ime, DraftNode parent) {
        this.ime = ime;
        this.parent = parent;
    }
}
