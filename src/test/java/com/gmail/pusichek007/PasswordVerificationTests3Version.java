package com.gmail.pusichek007;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PasswordVerificationTests3Version {


    //@Parameters ({AccountConstants.ShortPassword}, {AccountConstants.EmptyPassword})
    //@Parameters ({AccountConstants.EmptyPassword})
   // @Parameters ({AccountConstants.PasswordWithoutUpperCase})
    //@Parameters ({AccountConstants.PasswordWithoutLowerCase})
    @Test
    public void createAccount_FailedWithPasswordError (String password) {

        WebDriverWrapper driver = WebDriverWrapper.getDriverInstance();

        String fName  = AccountConstants.ValidFirstName;
        String lName  = AccountConstants.ValidLastName;
        String email  = AccountConstants.ValidEmail;

        AccountCreator accountCreator = new AccountCreator(driver);
        AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

        Assert.assertFalse(result.accountIsCreated());
        Assert.assertTrue(result.getError().contains(AccountConstants.ErrorMessageInvalidPassword));
    }
}
