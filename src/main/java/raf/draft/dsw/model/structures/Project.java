package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

@Getter
public class Project extends DraftNodeComposite {
    private String author;
    private String path;

    public Project(String ime, DraftNode parent, String author, String path) {
        super(ime, parent);
        this.author = author;
        this.path = path;
    }

    @Override
    public void addChild(DraftNode child) {
        if(child instanceof Building || child instanceof Room) {
            if (!this.getChildren().contains(child)) {
                this.getChildren().add(child);
            }
        }
    }
}
