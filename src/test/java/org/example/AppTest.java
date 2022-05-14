package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
    private String websiteURL = "https://www.amazon.in/";
    private WebDriver driver = null;

    @BeforeTest
    public void runBeforeSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(websiteURL);

    }

    @Test
    public void amazonTest() throws InterruptedException {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("IPhone13");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

        // Selecting laptop
        driver.findElement(By.xpath("//span[contains(text(), 'Apple iPhone 13 (128GB) - Midnight')]")).click();

        // Switch Tabs
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        // adding to cart
        Thread.sleep(2000);
        driver.findElement(By.id("add-to-cart-button")).click();

        // Procees to checkout
        Thread.sleep(2000);
        driver.findElement(By.name("proceedToRetailCheckout")).click();

        // Assertion
        Thread.sleep(2000);
        String pageText = driver.findElement(By.xpath("//div/h1[normalize-space(text())='Sign-In']")).getText();
        Assert.assertEquals(pageText, "Sign-In");

        // Not proceeding further because of the sign-in flow

    }

    @AfterTest
    public void runAfterTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

}
