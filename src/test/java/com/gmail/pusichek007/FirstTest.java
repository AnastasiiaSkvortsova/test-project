package com.gmail.pusichek007;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    @Test
    public void getTheCells() {

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        driver.get("https://www.toolsqa.com/automation-practice-table/");

        //create table body wrapper
        WebElement tbody  = driver.findElement(By.xpath("//div[@id='content']//tbody"));

        //get a list of rows
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));

        //loop rows
        for (int r = 0; r<rows.size(); r++) {
            //get a list of columns for each row
            List<WebElement> columns = rows.get(r).findElements(By.tagName("td"));

            //loop columns
            for (int c = 0; c <columns.size(); c++) {
            //get a value of each cell
               String cell = columns.get(c).getText();
            //println values of all the cells
               System.out.println(cell);
            }
        }
        driver.close();
    }


}
