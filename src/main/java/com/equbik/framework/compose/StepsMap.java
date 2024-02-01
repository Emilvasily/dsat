package com.equbik.framework.compose;

import com.equbik.framework.csv_model.MapCSVToElement;
import com.equbik.framework.json_model.Scenario;
import com.equbik.framework.json_model.Step;
import com.equbik.framework.model.Elements;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class StepsMap {

    /*
     * StepsMap class forms LinkedHashMap where Key is the Step class and Value is the List of elements used in this step
     */

    private final Scenario scenario;
    private final ElementsPerStep elementsPerStep;

    public StepsMap(Scenario scenario, MapCSVToElement csvToElementsList) {
        this.scenario = scenario;
        this.elementsPerStep = new ElementsPerStep(csvToElementsList);
    }

    public Map<String, List<Elements>> mapCSVElementsPerStep() {
        Map<String, List<Elements>> stepElements = new LinkedHashMap<>();
        for (Step step : scenario.getSteps()) {
            stepElements.put(step.getStepName(), elementsPerStep.getStepElements(step));
        }
        return stepElements;
    }

}
