package com.equbik.framework.compose;

import com.equbik.framework.json_model.Scenario;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class JSONToScenario {

    /*
     * JSONToScenario class. Hope you don't need any other words to describe what it's used for
     */

    public Scenario jsonToScenario(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Scenario scenario;
        try {
            Path filePath = Path.of(json);
            String content = Files.readString(filePath);
            scenario = objectMapper.readValue(content, Scenario.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scenario;
    }

}
