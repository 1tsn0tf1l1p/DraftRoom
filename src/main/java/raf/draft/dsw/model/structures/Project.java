package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class Project extends DraftNodeComposite {
    @JsonProperty("author")
    private String author;

    @JsonProperty("path")
    private String path;

    @JsonProperty("color")
    private Color color;
    private boolean changed;

    public Project(String ime, DraftNodeComposite parent, String author, String path) {
        super(ime, parent);
        this.color = Color.WHITE;
        this.author = author;
        this.path = path;
        this.changed = true;
    }

    // Default constructor for Jackson
    public Project() {
        this.color = Color.WHITE;
    }

    @Override
    public void addChild(DraftNode child) {
        if (child instanceof Building || child instanceof Room) {
            super.addChild(child);
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