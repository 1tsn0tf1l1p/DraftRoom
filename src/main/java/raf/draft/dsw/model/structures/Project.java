package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

@Getter
public class Project extends DraftNodeComposite{
    private String author;
    private String path;

    public Project(String ime, DraftNode parent, String author, String path) {
        super(ime, parent);
        this.author = author;
        this.path = path;
    }
}
