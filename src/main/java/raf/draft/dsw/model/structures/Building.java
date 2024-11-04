package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.Random;

@Getter
public class Building extends DraftNodeComposite {
    private Color color;
    public Building(String ime, DraftNode parent) {
        super(ime, parent);
        color = new Color(new Random().nextInt(255),
                new Random().nextInt(85, 170),
                new Random().nextInt(85, 170));
        System.out.println(color);
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
