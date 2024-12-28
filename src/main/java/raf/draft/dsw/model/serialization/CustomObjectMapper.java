package raf.draft.dsw.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import raf.draft.dsw.model.serialization.deserializers.ColorDeserializer;
import raf.draft.dsw.model.serialization.serializers.ColorSerializer;

import java.awt.*;

public class CustomObjectMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());
        objectMapper.registerModule(module);
    }

    public static ObjectMapper getMapper() {
        return objectMapper;
    }
}