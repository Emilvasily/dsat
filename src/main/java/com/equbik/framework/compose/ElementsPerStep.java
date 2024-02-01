package com.equbik.framework.compose;

import com.equbik.framework.csv_model.MapCSVToElement;
import com.equbik.framework.json_model.Step;
import com.equbik.framework.model.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class ElementsPerStep {

    /*
     * ElementsPerStep class forms List of elements, which will be used further in StepsMap class
     */

    private final List<Elements> elementsList;

    public ElementsPerStep(MapCSVToElement toElement) {
        this.elementsList = toElement.csvToElementsList();
    }

    public List<Elements> getStepElements(Step step) {
        List<Elements> elementsPerStep = new ArrayList<>();
        List<String> elementsInStep;
        elementsInStep = step.getElements();
        for (String element : elementsInStep) {
            Optional<Elements> matchingElement = elementsList.stream()
                    .filter(e -> e.getName().equals(element))
                    .findFirst();
            matchingElement.ifPresent(elementsPerStep::add);
        }
        return elementsPerStep;
    }

}
