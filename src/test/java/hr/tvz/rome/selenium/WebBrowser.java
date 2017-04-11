package hr.tvz.rome.selenium;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum WebBrowser {

    CHROME, FIREFOX, IE;


    static WebDriver setDriver(WebBrowser webBrowser, boolean inMemory) {
        WebDriver driver = null;
        switch (webBrowser) {

            case CHROME:
                if (inMemory) {
                    driver = new HtmlUnitDriver(BrowserVersion.CHROME);
                    driver.get("http://localhost:8080/Rome/");
                } else {
                    String rootChrome = System.getProperty("user.home").replaceAll("\\\\", "//");
                    String fileNameChrome = "chromedriver.exe";
                    String filePathChrome = rootChrome + "//Selenium//Drivers//" + fileNameChrome;
                    System.setProperty("webdriver.chrome.driver", filePathChrome);
                    driver = new ChromeDriver();
                    driver.get("http://localhost:8080/Rome/");
                }
                break;

            case IE:
                if (inMemory) {
                    driver = new HtmlUnitDriver();
                    driver.get("http://localhost:8080/Rome/");
                } else {
                    String rootIE = System.getProperty("user.home").replaceAll("\\\\", "//");
                    String fileNameIE = "IEDriverServer.exe";
                    String filePathIE = rootIE + "//Selenium//Drivers//" + fileNameIE;
                    System.setProperty("webdriver.ie.driver", filePathIE);
                    driver = new InternetExplorerDriver();
                    driver.get("http://localhost:8080/Rome/");
                }
                break;
        }
        return driver;
    }

}
