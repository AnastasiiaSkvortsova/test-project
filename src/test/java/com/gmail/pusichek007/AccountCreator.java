package com.gmail.pusichek007;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;


public class AccountCreator {

    WebDriverWrapper driver;

    public AccountCreator (WebDriverWrapper webdriver){
        driver = webdriver;
    }

    public AccountCreationResults createAccount(String fName, String lName, String email, String password) {

        //navigate to registration page
        PageNavigation pageNavigation = new PageNavigation(driver);
        pageNavigation.navigateToRegistrationPage();

        //get and input login
        driver.fillInputById(AccountConstants.FirstNameFieldID, fName);
        driver.fillInputById(AccountConstants.LastNameFieldID, lName);
        driver.fillInputById(AccountConstants.EmailFieldID, email);

        //get and input password
        driver.fillInputById(AccountConstants.PasswordFieldID, password);

        //press login button
        driver.pressButtonByXpath(AccountConstants.ReadyButtonXPath);

        //get account from db
        //if get_user = null, isCreated = false

        driver.waitPageUntilPresenseOfElementFoundByClassNamw(AccountConstants.ErrorMassageClass);
        String errorMessage = driver.findElementsByClassAndGetText(AccountConstants.ErrorMassageClass);
        boolean isCr = (errorMessage == null);

       return new AccountCreationResults(isCr, errorMessage);
    }

    public void refreshRegistrationPage() {

        driver.clearFieldById(AccountConstants.FirstNameFieldID);
        driver.clearFieldById(AccountConstants.LastNameFieldID);
        driver.clearFieldById(AccountConstants.EmailFieldID);
        driver.clearFieldById(AccountConstants.PasswordFieldID);
    }
}
