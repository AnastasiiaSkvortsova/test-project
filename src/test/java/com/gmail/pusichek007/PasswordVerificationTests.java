package com.gmail.pusichek007;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordVerificationTests {

    WebDriverWrapper driver = WebDriverWrapper.getDriverInstance();

    @Test
    public void createAccount_ShortPassword_GetError () {

        String fName  = AccountConstants.ValidFirstName;
        String lName  = AccountConstants.ValidLastName;
        String email  = AccountConstants.ValidEmail;
        String password = AccountConstants.ShortPassword;

       AccountCreator accountCreator = new AccountCreator(driver);
       AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

       Assert.assertFalse(result.accountIsCreated());
       Assert.assertTrue(result.getError().contains(AccountConstants.ErrorMessageInvalidPassword));
    }
    @Test
    public void createAccount_EmptyPassword_GetError () {

        String fName  = AccountConstants.ValidFirstName;
        String lName  = AccountConstants.ValidLastName;
        String email  = AccountConstants.ValidEmail;
        String password = AccountConstants.EmptyField;

        AccountCreator accountCreator = new AccountCreator(driver);
        AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

        Assert.assertFalse(result.accountIsCreated());
        Assert.assertTrue(result.getError().contains(AccountConstants.ErrorMessageInvalidPassword));

    }
    @Test
    public void createAccount_PasswordWithoutUpperCase_GetError () {

        String fName  = AccountConstants.ValidFirstName;
        String lName  = AccountConstants.ValidLastName;
        String email  = AccountConstants.ValidEmail;
        String password = AccountConstants.PasswordWithoutUpperCase;

        AccountCreator accountCreator = new AccountCreator(driver);
        AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

        Assert.assertFalse(result.accountIsCreated());
        Assert.assertTrue(result.getError().contains(AccountConstants.ErrorMessageInvalidPassword));

    }
    @Test
    public void CreateAccount_PasswordWithoutLowerCase_GetError () {

        String fName  = AccountConstants.ValidFirstName;
        String lName  = AccountConstants.ValidLastName;
        String email  = AccountConstants.ValidEmail;
        String password = AccountConstants.PasswordWithoutLowerCase;

        AccountCreator accountCreator = new AccountCreator(driver);
        AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

        Assert.assertFalse(result.accountIsCreated());
        Assert.assertTrue(result.getError().contains(AccountConstants.ErrorMessageInvalidPassword));

    }


}
