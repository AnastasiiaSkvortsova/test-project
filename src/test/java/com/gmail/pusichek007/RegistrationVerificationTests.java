package com.gmail.pusichek007;

import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationVerificationTests {

    @BeforeSuite(alwaysRun = true)
    //navigate to registration page
    public void navigateToRegistrationPage(){
        WebDriverWrapper driver = WebDriverWrapper.getDriverInstance();
        driver.openUrl(AccountConstants.homePageUrl);
        driver.waitPageUntilPresenseOfElementFoundByClassNamw(AccountConstants.LoginPageButtonClass);
        driver.pressButtonByClassName(AccountConstants.LoginPageButtonClass);
        driver.waitUntilFrameToBeAvailiableAndSwitchToIt(AccountConstants.LoginFrameId);
        driver.waitPageUntilPresenseOfElementFoundByLinkText(AccountConstants.RegistrationButtonLinkText);
        driver.pressButtonByLinkText(AccountConstants.RegistrationButtonLinkText);
        driver.waitPageUntilPresenseOfElementFoundByID(AccountConstants.FirstNameFieldID);
    }

    @Test (groups = {"RegistrationVerificationTests", "EmptyFields"})
    public void createAccount_EmptyFields_GetError(){
        createAccount_FailedWithEmptyOnInvalidFieldsError(AccountConstants.EmptyField, AccountConstants.EmptyField, AccountConstants.EmptyField, AccountConstants.EmptyField, AccountConstants.ErrorMessageNoFirstName);
    }

    @Test (groups = {"RegistrationVerificationTests", "EmptyFields"})
    public void createAccount_EmptyFirstName_GetError(){
        createAccount_FailedWithEmptyOnInvalidFieldsError(AccountConstants.EmptyField, AccountConstants.ValidLastName, AccountConstants.ValidEmail, AccountConstants.ValidPassword, AccountConstants.ErrorMessageNoFirstName);
    }

    //LastName is not a required field

    @Test (groups = {"RegistrationVerificationTests", "EmptyFields"})
    public void createAccount_EmptyEmail_GetError(){
        createAccount_FailedWithEmptyOnInvalidFieldsError(AccountConstants.ValidFirstName, AccountConstants.ValidLastName, AccountConstants.EmptyField, AccountConstants.ValidPassword, AccountConstants.ErrorMessageNoEmail);
    }

    @Test (groups = { "RegistrationVerificationTests", "PasswordVerificationTests", "EmptyFields"})
    public void createAccount_EmptyPassword_GetError(){
        createAccount_FailedWithEmptyOnInvalidFieldsError(AccountConstants.ValidFirstName, AccountConstants.ValidLastName, AccountConstants.ValidEmail, AccountConstants.EmptyField, AccountConstants.ErrorMessageNoPassword);
    }

    @DataProvider(name = "EmailVerificationTestsCredentials")
    public static Object[][] setEmailVerificationTestsCredentials() {
        return new Object[][]{{AccountConstants.ValidLastName, AccountConstants.ValidLastName, AccountConstants.InvalidEmail1, AccountConstants.ValidPassword, AccountConstants.ErrorMessageInvalidEmail},
                {AccountConstants.ValidLastName, AccountConstants.ValidLastName, AccountConstants.InvalidEmail2, AccountConstants.ValidPassword, AccountConstants.ErrorMessageInvalidEmail},
                {AccountConstants.ValidLastName, AccountConstants.ValidLastName, AccountConstants.InvalidEmail3, AccountConstants.ValidPassword, AccountConstants.ErrorMessageInvalidEmail},
                {AccountConstants.ValidLastName, AccountConstants.ValidLastName, AccountConstants.InvalidEmail4, AccountConstants.ValidPassword, AccountConstants.ErrorMessageInvalidEmail},
                {AccountConstants.ValidLastName, AccountConstants.ValidLastName, AccountConstants.InvalidEmail5, AccountConstants.ValidPassword, AccountConstants.ErrorMessageInvalidEmail}};
    }

    @Test (dataProvider = "EmailVerificationTestsCredentials", groups = {"RegistrationVerificationTests", "InvalidFields"})
    public void createAccount_EmailValidationFailed_GetError(String fName, String lName, String email, String password, String erroMes){
        createAccount_FailedWithEmptyOnInvalidFieldsError(fName, lName, email, password, erroMes);
    }

    @DataProvider(name = "PasswordVerificationTestCredentials")
    public static Object[][] setPasswordVerificationTestsCredentials(){
        return new Object[][]{{AccountConstants.ValidFirstName, AccountConstants.ValidLastName, AccountConstants.ValidEmail, AccountConstants.ShortPassword, AccountConstants.ErrorMessageInvalidPassword},
                {AccountConstants.ValidFirstName, AccountConstants.ValidLastName, AccountConstants.ValidEmail, AccountConstants.PasswordWithoutLowerCase, AccountConstants.ErrorMessageInvalidPassword},
                {AccountConstants.ValidFirstName, AccountConstants.ValidLastName, AccountConstants.ValidEmail, AccountConstants.PasswordWithoutUpperCase, AccountConstants.ErrorMessageInvalidPassword}};
    }

    @Test(dataProvider = "PasswordVerificationTestCredentials", groups = {"RegistrationVerificationTests","PasswordVerificationTests", "InvalidFields"})
    public void createAccount_InvalidPassword_GetError(String fName, String lName, String email, String password, String errorMes){
        createAccount_FailedWithEmptyOnInvalidFieldsError(fName, lName, email, password, errorMes);
    }

    public void createAccount_FailedWithEmptyOnInvalidFieldsError (String fName, String lName, String email, String password, String errorMes){

        //act
        WebDriverWrapper driver = WebDriverWrapper.getDriverInstance();
        AccountCreator accountCreator = new AccountCreator(driver);
        AccountCreationResults result = accountCreator.createAccount(fName,lName,email,password);

        //assert
        Assert.assertFalse(result.accountIsCreated());
        Assert.assertTrue(result.getError().contains(errorMes));
    }

    @AfterMethod(alwaysRun = true)
    //refresh the page for next test execution
    public void clearFields(){
        WebDriverWrapper driver = WebDriverWrapper.getDriverInstance();
        driver.clearFieldById(AccountConstants.FirstNameFieldID);
        driver.clearFieldById(AccountConstants.LastNameFieldID);
        driver.clearFieldById(AccountConstants.EmailFieldID);
        driver.clearFieldById(AccountConstants.PasswordFieldID);
    }

}
