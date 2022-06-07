package org.selenide.examples.tests.draftstudy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.containers.studyeditor.StudyDetails;
import org.selenide.examples.HTTPApiHelper;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.DraftStudy;
import org.selenide.examples.testdriver.groupinterfaces.DraftStudyTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Draft study tests")
public class DraftStudyTest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPage controlPanel;
    private static StudyEditor studyEditor;
    private static StudyDetails sd;
    HTTPApiHelper APIHelper = new HTTPApiHelper();
    String studyId;
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());


    @BeforeClass
    public static void setup()
    {
        // Closing driver
        closeWebDriver();
        // Set start page
        Configuration.baseUrl = ConfProperties.getProperty("loginpage");
        Configuration.headless = ConfProperties.getPropertyBool("headless");
        Configuration.browser = "chrome";
        // Configuration.browserVersion = "89.0";
        Configuration.pageLoadTimeout = 40000;
        Configuration.browserSize = "1440x900";
        // Configuration.browserVersion = true;
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
        // Obtain study list
        controlPanel.createStudyBtn.clickButton();
        // Create new study
        studyEditor = controlPanel.standardStudyBtn.clickButton(StudyEditor.class);
        // Accept cookies
        controlPanel.acceptCookies();
        // Refresh the data of the study details
        sd = studyEditor.studyDetails;
    }


    @Test
    @Category({DraftStudyTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("1. Checking default team")
    public void test1DefaultTeam(){
        // Check default team
        sd.studyTeamInput.checkSelectSpan(ConfProperties.getProperty("defaultTeam"));
    }

    @Test
    @Category({DraftStudyTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("2. Checking default name")
    public void test2DefaultName(){
        // Check default name
        sd.studyNameInput.checkInputValue("");
    }

    @Test
    @Category({DraftStudyTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("3.Checking default start date")
    public void test3DefaultStartDate(){
        // Check default StartDate
        sd.studyStartDatePicker.chekInput("");
    }

    @Test
    @Category({DraftStudyTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("4.Checking default objective")
    public void test4DefaultObjectives(){
        // Check default Objectives
        sd.studyObjectivesInput.checkMultilinedText("");
    }

    @Test
    @Category({DraftStudyTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("5.Checking all fields saving")
    public void test5SaveData() {
        // Set study name
        sd.studyNameInput.setClearInput(ConfProperties.getProperty("studyTestName"), false);
        // Set start date
        sd.studyStartDatePicker.setClearInput(ConfProperties.getProperty("studyTestStartDate"), false);
        // Set objective
        sd.studyObjectivesInput.setClearMultilinedText(ConfProperties.getProperty("studyTestObjectives"), false);
        // Click Create button
        sd.studyCreateBtn.clickButton();


        // Go to main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Go to Draft study list
        controlPanel.clickStudiesDraftTab();
        // Click target study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Refresh the date for study details
        sd = studyEditor.studyDetails;


        // Check name
        sd.studyNameInput.checkInputValue(ConfProperties.getProperty("studyTestName"));
        // Check start date
        sd.studyStartDatePicker.chekInput(ConfProperties.getProperty("studyTestStartDate"));
        // Check objective
        sd.studyObjectivesInput.checkMultilinedText(ConfProperties.getProperty("studyTestObjectives"));
    }

    @After
    public void logout() throws Exception {
        // Delete study
        APIHelper.deleteStudyById(studyId);
        // Logout
        controlPanel.userLogout();
        // Finishing the web driver
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        closeWebDriver();
    }
}
