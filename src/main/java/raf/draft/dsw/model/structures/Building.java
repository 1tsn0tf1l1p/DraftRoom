package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

@Getter
public class Building extends DraftNodeComposite {
    public Building(String ime, DraftNode parent) {
        super(ime, parent);
    }

    @Override
    public void addChild(DraftNode child) {
        if(child instanceof Room) {
            if (!this.getChildren().contains(child)) {
                this.getChildren().add(child);
            }
        }
    }
}
