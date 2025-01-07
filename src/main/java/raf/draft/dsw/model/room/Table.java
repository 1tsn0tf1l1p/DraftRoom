package raf.draft.dsw.model.room;

import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.patterns.prototype.Prototype;

public class Table extends RoomElement {
    public Table(String ime, DraftNodeComposite parent, int x, int y) {
        super(ime, parent, x, y);
    }

    public Table(Table table) {
        super(table);
    }

    public Table() {
    }

    @Override
    public Prototype clone() {
        return new Table(this);
    }
}
