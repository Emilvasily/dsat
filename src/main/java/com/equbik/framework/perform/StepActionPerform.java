package com.equbik.framework.perform;

import com.equbik.framework.model.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class StepActionPerform {

    /*
     * StepActionPerform class configures each element(if needed) before execution. It's a magic place, be careful
     */

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final List<ElementActionPerform> listOfElementsToPerform = new ArrayList<>();
    private final List<Elements> elements;
    private final Map<String, String> variables;

    public StepActionPerform(WebDriver driver, WebDriverWait wait, List<Elements> elements, Map<String, String> variables) {
        this.driver = driver;
        this.wait = wait;
        this.elements = elements;
        this.variables = variables;
    }

    private void preConfigElement(WebDriver driver, WebDriverWait wait, List<Elements> elements) {
        for (Elements element : elements) {
            int typeId = element.getTypeId();
            if ((typeId == 2 || typeId == 8 || typeId == 13) && variables.containsKey(element.getValue())) {
                /*
                 * Used for direct value injection. For example(type "wiki" on the google main page)
                 */
                element.setValue(variables.get(element.getValue()));
            } else if ((typeId == 7 || typeId == 12) && variables.containsKey(element.getValue())) {
                /*
                 * Badass action types used to replace pattern inside xpath with provided value before execution
                 */
                element.setXpath(element.getXpath().replace(element.getValue(), variables.get(element.getValue())));
            }
            listOfElementsToPerform.add(new ElementActionPerform(driver, wait, element));
        }
    }

    private void takeActions() {
        for (ElementActionPerform element : listOfElementsToPerform) {
            element.getAction();
        }
    }

    public void process() {
        preConfigElement(driver, wait, elements);
        takeActions();
    }

}
