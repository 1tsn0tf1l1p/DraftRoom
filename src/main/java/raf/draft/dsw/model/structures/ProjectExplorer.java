package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

@Getter
public class ProjectExplorer extends DraftNodeComposite {
    public ProjectExplorer(String ime, DraftNode parent) {
        super(ime, parent);
    }
}
