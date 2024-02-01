package com.equbik;

import com.equbik.framework.perform.StepsService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class Main {

    /*
     * Main class. Replace chrome(provided for example) if needed or improve with driver selection method
     */

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String scenarioPath;
    private final String path;

    public Main(String chromedriver, String chromium, String url, String scenarioPath, String path) {
        System.setProperty("webdriver.chrome.driver", chromedriver);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromium);
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        driver.manage().window().maximize();

        this.scenarioPath = scenarioPath;
        this.path = path;
    }

    public void performScenario() {
        StepsService service = new StepsService(driver, wait, scenarioPath, path);
        service.startScenario();
    }

}
