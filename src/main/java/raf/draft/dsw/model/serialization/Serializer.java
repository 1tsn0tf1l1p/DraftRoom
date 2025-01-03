package raf.draft.dsw.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.serialization.deserializers.ColorDeserializer;
import raf.draft.dsw.model.serialization.serializers.ColorSerializer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Serializer {
    private final ObjectMapper objectMapper;
    private final String customExtension = ".dr";

    public Serializer() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());
        objectMapper.registerModule(module);
    }

    public void save(Object data, File file) throws IOException {
        File fileWithExtension = ensureCustomExtension(file);
        objectMapper.writeValue(fileWithExtension, data);
    }

    public <T> T load(File file, Class<T> clazz) throws IOException {
        // Deserialize the file into the specified class
        T data = objectMapper.readValue(file, clazz);

        // If the deserialized object is a DraftNodeComposite, link parent-child relationships
        if (data instanceof DraftNodeComposite) {
            linkParentChildRelationships((DraftNodeComposite) data);
        }

        return data;
    }

    private File ensureCustomExtension(File file) {
        if (!file.getName().endsWith(customExtension)) {
            return new File(file.getAbsolutePath() + customExtension);
        }
        return file;
    }

    /**
     * Links parent-child relationships for a DraftNodeComposite after deserialization.
     *
     * @param root The root node of the deserialized tree.
     */
    private void linkParentChildRelationships(DraftNodeComposite root) {
        Map<UUID, DraftNodeComposite> nodeMap = new HashMap<>();

        // Build a map of all nodes by their IDs
        buildNodeMap(root, nodeMap);

        // Set the parent references
        setParentReferences(root, nodeMap);
    }

    /**
     * Recursively builds a map of all nodes in the tree, indexed by their UUIDs.
     *
     * @param node    The current node.
     * @param nodeMap The map to populate.
     */
    private void buildNodeMap(DraftNodeComposite node, Map<UUID, DraftNodeComposite> nodeMap) {
        nodeMap.put(node.getId(), node);

        for (DraftNode child : node.getChildren()) {
            if (child instanceof DraftNodeComposite) {
                buildNodeMap((DraftNodeComposite) child, nodeMap);
            }
        }
    }

    /**
     * Recursively sets parent references for all nodes in the tree based on their parent UUIDs.
     *
     * @param node    The current node.
     * @param nodeMap The map of nodes indexed by UUIDs.
     */
    private void setParentReferences(DraftNodeComposite node, Map<UUID, DraftNodeComposite> nodeMap) {
        for (DraftNode child : node.getChildren()) {
            if (child.getParentId() != null) {
                DraftNodeComposite parent = nodeMap.get(child.getParentId());
                child.setParent(parent);
            }

            if (child instanceof DraftNodeComposite) {
                setParentReferences((DraftNodeComposite) child, nodeMap);
            }
        }
    }
}