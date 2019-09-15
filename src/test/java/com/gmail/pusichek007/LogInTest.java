package com.gmail.pusichek007;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogInTest {

    String url;
    WebDriver driver;

    public void DoBefore() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver  = new ChromeDriver();
        url = "https://livejournal.com/";
    }

    @Test
    public void RegistrationTest () {

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(url);
        System.out.println("url is opened");

        driver.findElement(By.xpath("//html[@id='js']//header[@role='banner']//nav[@role='presentation']/ul[@class='s-do']//a[@href='https://www.livejournal.com/login.bml']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("find el1");

        driver.findElement(By.id("user")).sendKeys("PusPusPus");
        System.out.println("findel2");
        driver.findElement(By.id("lj_loginwidget_password")).sendKeys("007Pus007");

        driver.findElement(By.name("action:login")).submit();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.close();

    }

}


