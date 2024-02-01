package com.equbik.framework.perform;

import com.equbik.framework.compose.StepsMap;
import com.equbik.framework.compose.JSONToScenario;
import com.equbik.framework.csv_model.MapCSVToElement;
import com.equbik.framework.json_model.Scenario;
import com.equbik.framework.json_model.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class StepsService {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Scenario scenario;
    private final StepsMap elementsPerStep;

    /*
     * StepService class initializes each scenario step and provides variables
     */

    public StepsService(WebDriver driver, WebDriverWait wait, String scenarioPath, String path) {
        JSONToScenario jsonToScenario = new JSONToScenario();
        scenario = jsonToScenario.jsonToScenario(scenarioPath);
        MapCSVToElement csv = new MapCSVToElement(path);
        elementsPerStep = new StepsMap(scenario, csv);
        this.driver = driver;
        this.wait = wait;
    }

    public void startScenario() {
        List<StepActionPerform> list = new ArrayList<>();
        for (Step step : scenario.getSteps()) {
            Map<String, String> variables = new HashMap<>();
            try {
                variables.putAll(step.getVariables());
                System.out.println("INFO: " + step.getStepName() + " step has " + step.getVariables().keySet() + " variables");
            } catch (NullPointerException e) {
                System.out.println("INFO: Skipping variables for " + step.getStepName());
            }
            list.add(new StepActionPerform(driver, wait, elementsPerStep.mapCSVElementsPerStep().get(step.getStepName()), variables));
        }
        for (StepActionPerform service : list) {
            service.process();
        }
    }

}
