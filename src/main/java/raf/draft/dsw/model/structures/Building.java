package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

@Getter
public class Building extends DraftNodeComposite {
    @JsonProperty("color")
    private Color color;

    public Building(String ime, DraftNodeComposite parent) {
        super(ime, parent);
        this.color = new Color(new Random().nextInt(255),
                new Random().nextInt(85, 170),
                new Random().nextInt(85, 170));
    }

    @Override
    public void addChild(DraftNode child) {
        if (child instanceof Room) {
            super.addChild(child);
        }
    }

    // Default constructor for Jackson
    public Building() {}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Building building = (Building) object;
        return Objects.equals(super.getIme(), building.getIme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getIme());
    }
}