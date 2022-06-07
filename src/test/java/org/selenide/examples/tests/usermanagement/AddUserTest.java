package org.selenide.examples.tests.usermanagement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.testdriver.groupinterfaces.NegativeTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.groupinterfaces.UserCreationTests;
import org.selenide.examples.testdriver.modals.AddUserModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;
import org.selenide.examples.testdriver.pages.UserManagementPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Add user tests")
public class AddUserTest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPage controlPanel;
    public static UserManagementPage userManagement;
    public static AddUserModal userModal;
    String email;

    @BeforeClass
    public static void setup()
    {
        // Завершаем работу с веб драйвером, если она не была завершена корректно
        closeWebDriver();
        // Устанавливаем стартовую страницу
        Configuration.baseUrl = ConfProperties.getProperty("loginpage");
        Configuration.headless = ConfProperties.getPropertyBool("headless");

    }

    @Before
    public void login()
    {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        // Open authorization page
        loginPage = open("/", LoginPageNew.class);
        loginPage.selectEng();
        // Insert login
        loginPage.setLogin(ConfProperties.getProperty("login"));
        // Click Login button
        loginPage.loginBtn.clickButton();
        // Insert password
        loginPage.setPassword(ConfProperties.getProperty("password"));
        // Click Login button
        controlPanel = loginPage.loginBtn2.clickButton(ControlPanelPage.class);
        // Go to the User Management page
        userManagement = controlPanel.clickUserManagement();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Test
    @Category({UserCreationTests.class, PositiveTests.class})
    @DisplayName("1. Checking the creation of a new admin")
    public void test1CreateNewAdmin(){
        long cDate = System.currentTimeMillis();
        email=cDate+"admintest@test.test";
        userManagement.acceptCookies();
        // Open the modal window to add a new user
        $x("//*[contains(text(), \"\n" +
                "    Add User\n" +
                "  \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        userModal = userManagement.addUsrBtn.clickButton(AddUserModal.class);
        // Set a valid email value
        userModal.mailInputs.setClearInput(email, false);
        // We invite a new member
        userManagement = userModal.invite.clickButton(UserManagementPage.class);
        controlPanel = controlPanel.crumbsLine.clickHome();
    }

    @Test
    @Category({UserCreationTests.class, NegativeTests.class})
    @DisplayName("2.Checking that an error message appears when an invalid email is entered")
    public void test2CheckMailError(){
        email="admin";
        userManagement.acceptCookies();
        // Open the modal window to add a new user
        $x("//*[contains(text(), \"\n" +
                "    Add User\n" +
                "  \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        userModal = userManagement.addUsrBtn.clickButton(AddUserModal.class);
        // Set a invalid email
        userModal.mailInputs.setClearInput(email, false);
        userModal.checkMailErrorText(0, "The field must be a valid email");
        userManagement = userModal.cancel.clickButton(UserManagementPage.class);
        controlPanel = controlPanel.crumbsLine.clickHome();
    }

    @After
    public void logout()
    {
        // Logout
        controlPanel.userLogout();
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        // Finishing the web driver
        closeWebDriver();
    }
}
