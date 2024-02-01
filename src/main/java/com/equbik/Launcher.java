package com.equbik;

/**
 * Emil Vasilyev
 * emilvasily@gmail.com
 * https://www.linkedin.com/in/emilvas/
 **/

public class Launcher {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        /*
         * Launcher class to start the app using CMD. Just provide path to your driver, page url, scenario and csv library
         */

        String chromedriver = args[0];
        String chromium = args[1];
        String url = args[2];
        String scenarioPath = args[3];
        String csvPath = args[4];
        Main app = new Main(chromedriver, chromium, url, scenarioPath, csvPath);
        app.performScenario();
    }

}
