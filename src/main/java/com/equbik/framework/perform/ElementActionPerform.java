package com.equbik.framework.perform;

import com.equbik.framework.model.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class ElementActionPerform extends ElementActions {

    /*
     * ElementActionPerform class tells the framework which action from ElementActions class will be used for each action type
     */

    private final WebDriver driver;
    private final String xpath;
    private final Integer type;
    private final String value;
    private final int id;
    private final String relatedElement;

    public ElementActionPerform(WebDriver driver, WebDriverWait wait, Elements element) {
        super(wait);
        this.driver = driver;
        this.id = element.getId();
        this.xpath = element.getXpath();
        this.type = element.getTypeId();
        this.value = element.getValue();
        this.relatedElement = element.getRelatedElement();
    }

    public void getAction() {
        switch (type) {
            case 1 -> clickElement(driver, id, xpath);
            case 2 -> sendKeysElement(xpath, id, value);
            case 3, 7 -> System.out.println("SUCCESS: Point id: " + id + " element text: " + getElementText(id, xpath));
            case 4 -> clickEnter(id, xpath);
            case 5, 12 -> scrollToAndClickElement(driver, id, xpath);
            case 6 -> loginByPIN(driver);
            case 8 -> threadSleep(Integer.parseInt(value));
            case 9 -> iframeSwitch(driver, id, xpath);
            case 10 -> defaultFrame(driver);
            case 11 -> clickTab(driver);
            case 13 -> uploadFile(driver,id, value, xpath, relatedElement);
            default -> System.out.println("FAIL: Undefined element type");
        }
    }

}
