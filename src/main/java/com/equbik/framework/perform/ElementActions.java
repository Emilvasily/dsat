package com.equbik.framework.perform;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class ElementActions {

    /*
     * ElementActions class provides action for each action type. Add here whatever you need your framework to do with element(and not only)
     */

    protected final WebDriverWait wait;

    public ElementActions(WebDriverWait wait) {
        this.wait = wait;
    }

    public void clickElement(WebDriver driver, Integer id, String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            clickJS(driver, element);
        } catch (Exception e) {
            System.out.println("FAIL: Can't click: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't click: " + id + " " + xpath + " " + e.getMessage());
        }
    }

    public void scrollToAndClickElement(WebDriver driver, Integer id, String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            scrollTo(driver, element);
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            System.out.println("FAIL: Can't click: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't click: " + id + " " + xpath + " " + e.getMessage());
        }
    }

    public void clickEnter(Integer id, String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element = wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("FAIL: Can't click Enter: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't click Enter: " + id + " " + xpath + " " + e.getMessage());
        }
    }

    public void clickTab(WebDriver driver){
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
        } catch (Exception e) {
            System.out.println("FAIL: Can't press Tab: " + e.getMessage());
            throw new RuntimeException("FAIL: Can't press Tab: " + e.getMessage());
        }
    }

    public void clickJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollTo(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void sendKeysElement(String xpath, Integer id, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("FAIL: Can't locate: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't locate: " + id + " " + xpath + " " + e.getMessage());
        }
    }

    public String getElementText(Integer id, String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (Exception e) {
            System.out.println("FAIL: Can't locate: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't locate: " + id + " " + xpath + " " + e.getMessage());
        }
    }

    public void threadSleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginByPIN(WebDriver driver) {
        try {
            org.openqa.selenium.interactions.Actions actions = new Actions(driver);

            int attempts = 0;
            while (attempts < 2) {
                actions.sendKeys(Keys.ENTER).perform();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                attempts++;
            }
        } catch (Exception e) {
            System.out.println("FAIL: Can't click Enter: " + e.getMessage());
            throw new RuntimeException("FAIL: Can't click enter " + e.getMessage());
        }
    }

    public void iframeSwitch(WebDriver driver, Integer id, String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            driver.switchTo().frame(element);
        } catch (Exception e) {
            System.out.println("FAIL: Can't switch to frame: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't switch to frame: " + id + " " + xpath + " " + e.getMessage());
        }
    }

    public void defaultFrame(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("FAIL: Can't switch to default frame: " + e.getMessage());
            throw new RuntimeException("FAIL: Can't switch to default frame: " + e.getMessage());
        }
    }

    public void uploadFile(WebDriver driver, Integer id, String filePath, String xpath, String relatedXpath){
        File doc = new File(filePath);
        try {
            WebElement uploaded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            uploaded.sendKeys(doc.getAbsolutePath());

            WebElement toUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(relatedXpath)));
            
            Actions action = new Actions(driver);
            action.dragAndDrop(toUpload, uploaded);
        }catch (Exception e) {
            System.out.println("FAIL: Can't locate: " + id + " " + xpath + " " + e.getMessage());
            throw new RuntimeException("FAIL: Can't locate: " + id + " " + xpath + " " + e.getMessage());
        }
    }

}
