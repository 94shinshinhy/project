package com.example.project.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    void 자바객체_JSON_변환() throws JsonProcessingException {

        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("치킨패티", "토마토", "양상추");
        Burger burger = new Burger("상하이", 5000, ingredients);

        // 수행
        String json = objectMapper.writeValueAsString(burger);

        // 예상
        String expected = "{\"name\":\"상하이\",\"price\":5000,\"ingredients\":[\"치킨패티\",\"토마토\",\"양상추\"]}";

        // 검증
        assertEquals(expected, json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());

    }

    @Test
    void JSON_자바객체_변환() throws JsonProcessingException {

        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.put("name", "상하이");
        objectNode.put("price", 5000);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("치킨패티");
        arrayNode.add("토마토");
        arrayNode.add("양상추");

        objectNode.set("ingredients", arrayNode);

        String json = objectNode.toString();

        // 수행
        Burger burger = objectMapper.readValue(json, Burger.class);

        // 예상
        List<String> ingredients = Arrays.asList("치킨패티", "토마토", "양상추");
        Burger expected = new Burger("상하이", 5000, ingredients);

        // 검증
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());

    }

}