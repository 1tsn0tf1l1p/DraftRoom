package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

@Getter
public class ProjectExplorer extends DraftNodeComposite {
    public ProjectExplorer(String ime, DraftNodeComposite parent) {
        super(ime, parent);
    }

    @Override
    public void addChild(DraftNode child) {
        if(child instanceof Project) {
            if (!this.getChildren().contains(child)) {
                this.getChildren().add(child);
            }
        }
    }


}
