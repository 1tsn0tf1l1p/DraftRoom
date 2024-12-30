package raf.draft.dsw.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.draft.dsw.model.serialization.deserializers.ColorDeserializer;
import raf.draft.dsw.model.serialization.serializers.ColorSerializer;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        return objectMapper.readValue(file, clazz);
    }

    private File ensureCustomExtension(File file) {
        if (!file.getName().endsWith(customExtension)) {
            return new File(file.getAbsolutePath() + customExtension);
        }
        return file;
    }
}