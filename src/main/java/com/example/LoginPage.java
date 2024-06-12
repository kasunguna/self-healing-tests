package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private By usernameInput = By.xpath("/html/body/div/form/div/label[@for='username']/following-sibling::input");
    private By passwordInput = By.id("password1");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By loginMessage = By.id("loginMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Set implicit wait
    }

    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameInput);
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

    public String getLoginMessage() {
        WebElement loginMessageElement = driver.findElement(loginMessage);
        return loginMessageElement.getText();
    }

    public boolean isLoginMessageDisplayed() {
        WebElement loginMessageElement = driver.findElement(loginMessage);
        return loginMessageElement.isDisplayed();
    }

}
