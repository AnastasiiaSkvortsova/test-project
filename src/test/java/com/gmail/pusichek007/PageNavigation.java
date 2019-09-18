package com.gmail.pusichek007;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageNavigation {

    private WebDriverWrapper driver;

    public PageNavigation(WebDriverWrapper webDriver){
        driver = webDriver;
    }

    public void navigateToRegistrationPage() {
        driver.openUrl(AccountConstants.homePageUrl);
        driver.waitPageUntilPresenseOfElementFoundByClassNamw(AccountConstants.LoginPageButtonClass);
        driver.pressButtonByClassName(AccountConstants.LoginPageButtonClass);
        driver.waitUntilFrameToBeAvailiableAndSwitchToIt(AccountConstants.LoginFrameId);
        driver.waitPageUntilPresenseOfElementFoundByCSS(AccountConstants.RegistrationButtonCSS);
        driver.pressButtonByCSSSelector(AccountConstants.RegistrationButtonCSS);
        driver.waitPageUntilPresenseOfElementFoundByID(AccountConstants.FirstNameFieldID);
    }
}
