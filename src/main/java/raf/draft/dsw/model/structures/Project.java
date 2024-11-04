package raf.draft.dsw.model.structures;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;

@Getter
public class Project extends DraftNodeComposite {
    @Setter
    private String author;
    @Setter
    private String path;
    private Color color;

    public Project(String ime, DraftNode parent, String author, String path) {
        super(ime, parent);
        color = Color.WHITE;
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
