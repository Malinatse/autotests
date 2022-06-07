package org.selenide.examples.tests.login;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.testdriver.pages.TESTLOGINPAGE;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Login page")
public class TESTLOGIN {
    @Rule
    public TextReport report = new TextReport();

    public static TESTLOGINPAGE loginPage;

    @BeforeClass
    public static void setup()
    {
        // Terminate the web driver, if it was not completed correctly
        closeWebDriver();
        // Set the home page
        Configuration.baseUrl = ConfProperties.getProperty("loginpage");
    }

    @Before
    public void login()
    {
        loginPage = open("/", TESTLOGINPAGE.class);
    }

    @Test
    @DisplayName("Login test")
    public void testLoginTest(){
        loginPage.enterLogin("proho912@gmail.com");
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
