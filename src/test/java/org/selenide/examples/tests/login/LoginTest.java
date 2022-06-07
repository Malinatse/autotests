package org.selenide.examples.tests.login;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.testdriver.groupinterfaces.LoginTests;
import org.selenide.examples.testdriver.groupinterfaces.NegativeTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Login page tests")
public class LoginTest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPage controlPanel;

    @BeforeClass
    public static void setup()
    {
        // Terminate the web driver, if it was not completed correctly
        closeWebDriver();
        // Set the home page
        Configuration.baseUrl = ConfProperties.getProperty("loginpage");
        Configuration.headless = ConfProperties.getPropertyBool("headless");
    }

    @Before
    public void login()
    {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        // Open the login page
        loginPage = open("/", LoginPageNew.class);
        loginPage.selectEng();
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("1. Login and password verification, the positive case")
    public void test1ValidLoginValidPassword(){
        //Enter login
        loginPage.setLogin(ConfProperties.getProperty("login"));
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Enter password
        loginPage.setPassword(ConfProperties.getProperty("password"));
        //Press the "Login" button
        controlPanel=loginPage.loginBtn2.clickButton(ControlPanelPage.class);
    }

    @Test
    @Category({LoginTests.class, NegativeTests.class})
    @DisplayName("2. Login check (blank field), negative case")
    public void test2InvalidLoginBlankField(){
        //Click on the login field
        loginPage.loginField.clickInput();
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Checking the presence of an invalid login message
        loginPage.checkErrorInvalidMailText("Please enter a valid email.");
    }

    @Test
    @Category({LoginTests.class, NegativeTests.class})
    @DisplayName("3. Login verification (filled field without validation), negative case")
    public void test3InvalidLogin(){
        //Entering an invalid login
        loginPage.loginField.setClearInput("admin", false);
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Checking the presence of an invalid login message
        loginPage.checkErrorInvalidMailText("Please enter a valid email.");
    }

    @Test
    @Category({LoginTests.class, NegativeTests.class})
    @DisplayName("4. Login verification (filled field without validation without domain), negative case")
    public void test4InvalidLoginWithoutDomain(){
        //Entering an invalid login
        loginPage.loginField.setClearInput("admin@admin", false);
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Checking the presence of an invalid login message
        loginPage.checkErrorInvalidMailText("We couldn't find that account. Please enter your registered email or contact us for assistance.");
    }

    @Test
    @Category({LoginTests.class, NegativeTests.class})
    @DisplayName("5. Checking unregistered login, negative case")
    public void test5NotFoundLogin(){
        //Entering an unregistered login
        loginPage.loginField.setClearInput("admin@admin.admin", false);
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Checking the presence of an invalid login message
        loginPage.checkErrorInvalidMailText("We couldn't find that account. Please enter your registered email or contact us for assistance.");
    }

    @Test
    @Category({LoginTests.class, NegativeTests.class})
    @DisplayName("6. Checking valid login and blank password field, negative case")
    public void test6BlankFieldPassword(){
        //Enter a registered login
        loginPage.setLogin(ConfProperties.getProperty("login"));
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Click on the password entry field
        loginPage.passInput.clickInput();
        //Press the "Login" button
        loginPage.loginBtn2.clickButton();
        //Checking for an invalid password message
        loginPage.checkErrorInvalidMailText("Your password is incorrect. Please try again or use the reset password option.");
    }

    @Test
    @Category({LoginTests.class, NegativeTests.class})
    @DisplayName("7. Checking valid login and invalid password, negative case")
    public void test7IncorrectPassword(){
        //Enter a registered login
        loginPage.setLogin(ConfProperties.getProperty("login"));
        //Press the "Next" button
        loginPage.loginBtn.clickButton();
        //Entering an invalid password
        loginPage.passInput.setClearInput("123",false);
        //Press the "Login" button
        loginPage.loginBtn2.clickButton();
        //Checking for an invalid password message
        loginPage.checkErrorInvalidMailText("Your password is incorrect. Please try again or use the reset password option.");
    }

    @After
    public void logout()
    {
        //Finishing the web driver
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        //Finishing the web driver
        closeWebDriver();
    }
}
