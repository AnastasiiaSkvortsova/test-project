package com.gmail.pusichek007;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordVerificationTests2Version {

    @Test (groups = { "passwordVerificationTests", "EmptyFields" })
    public void createAccount_EmptyPassword_GetError(){
        createAccount_FailedWithPasswordError(AccountConstants.EmptyField);
    }

    @Test (groups = { "passwordVerificationTests", "InvalidFields"})
    public void createAccount_ShortPassword_GetError(){
        createAccount_FailedWithPasswordError(AccountConstants.ShortPassword);
    }

    @Test (groups = { "passwordVerificationTests", "InvalidFields"})
    public void createAccount_PasswordWithoutUpperCase_GetError(){
        createAccount_FailedWithPasswordError(AccountConstants.PasswordWithoutUpperCase);
    }

    @Test (groups = { "passwordVerificationTests", "InvalidFields"})
    public void createAccount_PasswordWithoutLowerCase_GetError(){
        createAccount_FailedWithPasswordError(AccountConstants.PasswordWithoutLowerCase);
    }

    public void createAccount_FailedWithPasswordError (String password) {

        WebDriverWrapper driver = new WebDriverWrapper();

        //arrange
        String fName  = AccountConstants.ValidFirstName;
        String lName  = AccountConstants.ValidLastName;
        String email  = AccountConstants.ValidEmail;

        //act
        AccountCreator accountCreator = new AccountCreator(driver);
        AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

        //assert
        Assert.assertFalse(result.accountIsCreated());
        Assert.assertTrue(result.getError().contains(AccountConstants.ErrorMessageInvalidPassword));

        //refresh the page for next test execution
        accountCreator.refreshRegistrationPage();
    }
}
