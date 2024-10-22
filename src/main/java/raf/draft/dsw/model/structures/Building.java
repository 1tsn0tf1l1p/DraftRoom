package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

@Getter
public class Building extends DraftNodeComposite {
    public Building(String ime, DraftNode parent) {
        super(ime, parent);
    }
}
