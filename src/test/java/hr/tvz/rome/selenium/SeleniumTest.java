package hr.tvz.rome.selenium;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    private WebDriver driver;

    private WebDriverWait wait;


    public void login(WebBrowser webBrowser, boolean inMemory) {

        driver = WebBrowser.setDriver(webBrowser, inMemory);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        final WebElement username = driver.findElement(By.name("username"));
        final WebElement password = driver.findElement(By.name("password"));

        final WebElement submit = driver.findElement(By.name("loginButton"));

        username.sendKeys("itomic");
        password.sendKeys("password");

        submit.click();
    }


    @Test
    public void testLogin() {
        login(WebBrowser.CHROME, false);
    }

}
