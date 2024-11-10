package raf.draft.dsw.model.structures;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.Objects;

@Getter
public class Project extends DraftNodeComposite {
    @Setter
    private String author;
    @Setter
    private String path;
    private Color color;

    public Project(String ime, DraftNodeComposite parent, String author, String path) {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return Objects.equals(super.getIme(), project.getIme()) && Objects.equals(path, project.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getIme(), path);
    }
}
