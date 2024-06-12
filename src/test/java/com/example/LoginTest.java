package com.example;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private SelfHealingDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gunathilakek\\work\\healenium\\selenium\\chromedriver-win64\\chromedriver.exe"); // Update with the path to your chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver delegate = new ChromeDriver(options);
        driver = SelfHealingDriver.create(delegate);
        driver.manage().window().maximize();
        driver.get("https://jesrellf.github.io/SelfHealingSample/"); // Update with the correct path to your HTML file
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.enterUsername("user");
        loginPage.enterPassword("password");
        loginPage.clickLoginButton();

        // Wait for navigation to complete or check URL or new page elements
        try {
            Thread.sleep(2000); // Temporary solution, replace with better wait condition if possible
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Validate if the navigation was successful
        Assert.assertTrue(driver.getCurrentUrl().contains("page1.html"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.clickLoginButton();

        // Check for the login error message
        Assert.assertTrue(loginPage.isLoginMessageDisplayed());
        Assert.assertEquals(loginPage.getLoginMessage(), "Invalid username or password. Please try again.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
