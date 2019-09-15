package com.gmail.pusichek007;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.Date;


public class RegistrationTest {

    private WebDriver driver;

    public RegistrationTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void loginTest (String name, String email, String password) {

        driver.findElement(By.id("id_reg-full_name")).sendKeys(name);
        driver.findElement(By.id("id_reg-email")).sendKeys(email);
        driver.findElement(By.id("id_reg-password")).sendKeys(password);
    }

    private String generateUniqueEmail() {
        return "pusichek007+"+getTimeStamp()+"@gmail.com";
    }

    private void assertMessageSuccessReg() {

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Assert.assertTrue(driver.findElements(By.xpath("/html/body/div[5]")).size()>0);
            WebElement moveToClose = driver.findElement(By.cssSelector("#show-user-menu"));
            Actions close = new Actions(driver);
            close.moveToElement(moveToClose).moveByOffset(1,1).click().build().perform();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       }
        catch(Exception notElementFound) {
            Assert.fail();
        }
    }

    public void checkNameValidEmailValidPassword(String name, String email) {
        String password = "password111";
        loginTest(name, email, password);
        driver.findElement(By.xpath("/html//form[@id='registration-form']/table//input[@value='Зарегистрироваться']")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    private long getTimeStamp (){
        return new Date().getTime();
    }

    public void doBeforeEachTest () {
        String url = "https://dadata.ru/";
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.findElement(By.xpath("/html//header[@id='header']//a[@href='#authorization_popup']")).click();
        driver.findElement(By.xpath("/html//div[@id='authorization_popup']//a[@href='#registration_popup']")).click();
    }

    @Test
    public void checkNameLettersNumbers() {
        checkNameValidEmailValidPassword("Arnold1", generateUniqueEmail());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertMessageSuccessReg();
    }

    @Test
    public void checkRegistrateSamePerson() {
        String email = generateUniqueEmail();
        checkNameValidEmailValidPassword("Arnold1", email);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertMessageSuccessReg();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("/html/body/div[6]"))));
        signOut();

        driver.findElement(By.xpath("/html//header[@id='header']//a[@href='#authorization_popup']")).click();
        driver.findElement(By.xpath("/html//div[@id='authorization_popup']//a[@href='#registration_popup']")).click();
        checkNameValidEmailValidPassword("Arnold", email);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html//form[@id='registration-form']/table//div[.='Уже зарегистрирован']"))));
        Assert.assertTrue(driver.findElements(By.xpath("/html//form[@id='registration-form']/table//div[.='Уже зарегистрирован']")).size()>0);

    }

    private void signOut() {
        driver.findElement(By.xpath("//div[@id='user-menu']")).click();
        driver.findElement(By.xpath("//div[@id='user-menu']/ul//a[@href='/profile/logout/?next=/']")).click();
    }

    public void doAfterEachTest() {
        driver.close();
    }

}
