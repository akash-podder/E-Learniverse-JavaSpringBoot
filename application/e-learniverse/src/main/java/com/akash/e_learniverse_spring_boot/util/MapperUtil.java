package com.akash.e_learniverse_spring_boot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
public final class MapperUtil {

    public static final ModelMapper MODEL_MAPPER;
    private static ObjectMapper mapper;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration().setAmbiguityIgnored(true);
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private MapperUtil() {

    }

    private static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> String toJson(T obj) throws JsonProcessingException {
        return getMapper().writeValueAsString(obj);
    }

    public static <T> void writeValue(PrintWriter writer, T obj) throws IOException {
        getMapper().writeValue(writer, obj);
    }

    public static <T> T toObject(String json, Class<T> templateClass) throws IOException {
        return getMapper().readValue(json, templateClass);
    }

    public static Map<String, Object> getStringObjectMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
        };
        return mapper.readValue(json, typeRef);
    }

    public static Optional<JsonNode> readTree(String json) {
        JsonNode resp = null;
        try {
            resp = getMapper().readTree(json);
        } catch (Exception ex) {
            log.error("Json parsing error: {} {}", ex.getMessage(), ex);
        }
        return Optional.ofNullable(resp);
    }

    public static String safeParseNode(JsonNode node) {
        if (node == null) {
            return null;
        }
        try {
            return MapperUtil.toJson(node);
        } catch (JsonProcessingException e) {
            return node.toString();
        }
    }

    public static <T> Optional<String> toJsonString(T obj) {
        String resp;
        try {
            resp = getMapper().writeValueAsString(obj);
        } catch (Exception ex) {
            resp = null;
        }
        return Optional.ofNullable(resp);
    }

    public static <T> Optional<T> fromJson(String json, Class<T> templateClass) {
        T response;

        try {
            response = getMapper().readValue(json, templateClass);
        } catch (Exception ex) {
            response = null;
        }
        return Optional.ofNullable(response);
    }

    public static <T> Optional<List<T>> listFromJson(String json, Class<T> templateClass) {
        List<T> response;
        try {
            response = getMapper().readValue(
                json,
                mapper.getTypeFactory().constructCollectionType(List.class, templateClass)
            );
        } catch (Exception ex) {
            response = null;
        }
        return Optional.ofNullable(response);
    }

    public static Optional<JsonNode> tree(String json) {
        JsonNode resp;
        try {
            resp = getMapper().readTree(json);
        } catch (Exception ex) {
            resp = null;
        }
        return Optional.ofNullable(resp);
    }

    public static <T, E> Optional<E> map(T obj, Class<E> referenceClass) {
        E e;
        try {
            e = MODEL_MAPPER.map(obj, referenceClass);
        } catch (Exception ex) {
            e = null;
        }
        return Optional.ofNullable(e);
    }
}
