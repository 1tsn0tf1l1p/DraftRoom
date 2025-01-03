package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.util.Objects;
import java.util.UUID;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DraftNodeComposite.class, name = "composite"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.Room.class, name = "room"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.Building.class, name = "building"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.RoomElement.class, name = "element"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Bathtub.class, name = "bathtub"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Bed.class, name = "bed"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Sink.class, name = "sink"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Doors.class, name = "doors"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Wardrobe.class, name = "wardrobe"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Boiler.class, name = "boiler"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.Table.class, name = "table"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.ToiletBowl.class, name = "toiletBowl"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.room.WashingMachine.class, name = "washingMachine")
})
public abstract class DraftNode {

    /**
     * -- SETTER --
     *  Sets the name of the node.
     *
     * @param ime the new name of the node
     */
    @Setter
    @JsonProperty("name")
    private String ime;

    @JsonIgnore
    private DraftNodeComposite parent;

    @JsonProperty("parentId")
    private UUID parentId;

    private UUID id = UUID.randomUUID();

    /**
     * Constructs a DraftNode with the specified name and parent.
     *
     * @param ime    the name of the node
     * @param parent the parent node
     */
    public DraftNode(String ime, DraftNodeComposite parent) {
        this.ime = ime;
        this.parent = parent;
        if (parent != null) {
            this.parentId = parent.getId();
        }
    }

    /**
     * Default constructor for Jackson (required for deserialization).
     */
    public DraftNode() {}

    /**
     * Sets the parent of the node.
     *
     * @param parent the new parent of the node
     */
    public void setParent(DraftNodeComposite parent) {
        this.parent = parent;
        if (parent != null) {
            this.parentId = parent.getId();
        }
    }

    @JsonProperty("parent")
    public String getParentName() {
        return parent != null ? parent.getIme() : null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DraftNode draftNode = (DraftNode) object;
        return Objects.equals(ime, draftNode.ime) && Objects.equals(parent, draftNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, parent);
    }
}