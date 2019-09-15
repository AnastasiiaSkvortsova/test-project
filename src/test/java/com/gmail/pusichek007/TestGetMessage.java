package com.gmail.pusichek007;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.Message;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestGetMessage {

    private WebDriver driver;

    public TestGetMessage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    private static EmailUtils emailUtils;

    private long getTimeStamp (){
        return new Date().getTime();
    }
    private String generateUniqueEmail() {
        return "pusichek007+"+getTimeStamp()+"@gmail.com";
    }



    public void connectToEmail() {
        try {
            //emailUtils = new EmailUtils();
           emailUtils = new EmailUtils("aaaskvortsova@gmail.com", "008Pus008", "smtp.gmail.com", EmailUtils.EmailFolder.INBOX);
        } catch (Exception e) {
        e.printStackTrace();
        Assert.fail(e.getMessage());
        }
    }

    @Test
    public void Test(){
        String html = "sdfsdf sdf  sdf <b>sdffsd</b> sdfsd <a href='temp-href'>text</a>sdfsdfms sd f sdf  sdf s";
        Matcher matcher = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1").matcher(html);

        Assert.assertTrue(matcher.find());
    }

    @Test
    public void testGettingAuthorizationCode() {
        try {

            WebDriverWait wait = new WebDriverWait(driver,10);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://pravoved.ru/registration/");

            driver.findElement(By.id("name")).sendKeys("anaana");
            driver.findElement(By.id("popupRegEmail")).sendKeys(generateUniqueEmail());
            driver.findElement(By.cssSelector("[for='agree-terms-conditions-checkbox']")).click();
            driver.findElement(By.cssSelector(".register")).submit();
            driver.findElement(By.cssSelector(".ui-button-icon-primary")).click();
            //LocalDateTime now = new LocalDateTime(L);

            Message email = emailUtils.getLatestMessage();
            String html = emailUtils.getMessageContent(email);
            String link = emailUtils.getAllUrlsFromMessage(html).get(1);
            System.out.println(link);

            driver.get(link);

            driver.close();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testGetUrls() throws Exception {
        String html = "sdfsdf sdf  sdf <b>sdffsd</b> sdfsd <a style=\"color:#244f8a\" href=\"https://pravoved.ru/?astr=776b3499786a29999753ed974456e088&amp;email=aaaskvortsova%2B124%40gmail.com\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://pravoved.ru/?astr%3D776b3499786a29999753ed974456e088%26email%3Daaaskvortsova%252B124%2540gmail.com&amp;source=gmail&amp;ust=1547583424010000&amp;usg=AFQjCNGxw0PRdxQOkH8SI-xX7yQOL7ArXQ\">https://pravoved.ru/?astr=<wbr>776b3499786a29999753ed974456e0<wbr>88&amp;email=aaaskvortsova%2B124%<wbr>40gmail.com</a> sdfsdfms sd f sdf  sdf s";
        String link = emailUtils.getAllUrlsFromMessage(html).get(0);
        System.out.println("url is"+link);
        driver.get(link);
    }
}
