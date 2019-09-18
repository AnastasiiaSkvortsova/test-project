package com.gmail.pusichek007;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WebDriverWrapper {

    private static WebDriverWrapper driverInstance;
    WebDriver driver;

    private WebDriverWrapper(){

        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
    }

    public static WebDriverWrapper getDriverInstance(){
        if (driverInstance == null){
            driverInstance = new WebDriverWrapper();}
        return driverInstance;
    }

    public void switchToFrameById (String id){
        driver.switchTo().frame(id);
    }

    public void moveToElementAndSendKeys (String input){
        Actions action = new Actions(driver);
        WebElement mouseHover = driver.findElement(By.name("FirstName"));
        action.moveToElement(mouseHover).perform();
        mouseHover.sendKeys(input);
    }

    public void moveToElementAndClick(String className){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.className(className))).click().perform();
    }

    public void waitPageUntilPresenseOfElementFoundByID(String id) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public void waitPageUntilPresenseOfElementFoundByCSS(String css) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
    }

    public void waitPageUntilPresenseOfElementFoundByClassNamw(String className) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
    }

    public void waitPageUntilPresenseOfElementFoundByXPath(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public void waitPageUntilPresenseOfElementFoundByLinkText(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
    }

    public void waitUntilElementToBeClickable(String className){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
    }

    public void waitUntilFrameToBeAvailiableAndSwitchToIt (String frameId){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
    }

    public void waitMentionedTime (int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    public void openUrl (String url){
        driver.get(url);
    }

    public void finfElementByClass (String className){
        driver.findElement(By.className(className));
    }

    public void findElementById (String id){
        driver.findElement(By.id(id));
    }

    public Boolean checkPresenseOfElementById (String id){
       Boolean isPresent = driver.findElement(By.id(id)).isDisplayed();
       return isPresent;
    }

    public void fillInputById (String id, String input){
        driver.findElement(By.id(id)).sendKeys(input);
    }

    public void fillInputByXPath (String xpath, String input){
        driver.findElement(By.xpath(xpath)).sendKeys(input);
    }

    public void fillInputByCSS (String css, String input){
        driver.findElement(By.cssSelector(css)).sendKeys(input);
    }

    public void fillInputByName (String name, String input){driver.findElement(By.name(name)).sendKeys(input);}

    public void pressButtonByXpath (String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }

    public void pressButtonByLinkText (String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    public void pressButtonByCSSSelector (String css){
        driver.findElement(By.cssSelector(css)).click();
    }

    public void pressButtonByClassName (String className){
        driver.findElement(By.className(className)).click();
    }

    public String findElementsByClassAndGetText (String className){
        String textMessage = driver.findElement(By.className(className)).getText();
        return  textMessage;
    }

    public void clearFieldById (String id) {
        driver.findElement(By.id(id)).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.BACK_SPACE));
    }

}
