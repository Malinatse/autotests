package org.selenide.examples.tests.pwa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.HTTPApiHelper;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.PWA.pages.FirstVisitPagePWA;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.testdriver.groupinterfaces.NegativeTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("PWA First Visit User tests")
public class FirstVisitUserPWATest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPagePWA controlPanelPWA;
    public static ControlPanelPage controlPanel;
    public static StudyEditor studyEditor;
    public SectionGroupsParticipants gp;
    public static FirstVisitPagePWA firstVisitPage;
    public   String invLnk;
    HTTPApiHelper APIHelper = new HTTPApiHelper();
    String email;
    String studyId;

    @BeforeClass
    public static void setup()
    {
        // Closing driver
        closeWebDriver();
        // Set start page
        Configuration.baseUrl = ConfProperties.getProperty("loginpage");
        Configuration.browser = "chrome";
        // Configuration.browserVersion = "89.0";
        Configuration.pageLoadTimeout = 40000;
        Configuration.browserSize = "1440x900";
        // Configuration.browserVersion = true;
        Configuration.headless = ConfProperties.getPropertyBool("headless");
    }

    @Before
    public void login() throws Exception {
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
        studyEditor.createStudy("TestStudy");
        // Unwrap main wrapper
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionProspectsDatabase.groupHeader.clickPropertyHeader();
        // Get prospects table
        LLReviewProspectsTable reviewdb;
        // Add participant by email
        gp.sectionProspectsDatabase.prospectsEmailHeader.clickButton();
        long cDate = System.currentTimeMillis();
        email = "autotest" + cDate + "@test.test";
        gp.sectionProspectsDatabase.prospectsEmailInput.setClearInput(email, true);
        gp.sectionProspectsDatabase.addProspectsBtn.clickButton();
        // Search new participant
        gp.sectionReviewParticipants.groupHeader.clickPropertyHeader();
        gp = gp.sectionReviewParticipants.performSearch(email);
        // Invite participant
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Open authorization page
        loginPage = open(invLnk, LoginPageNew.class);
        // Insert login
        loginPage.setLogin(email);
        // Click Login button
        loginPage.loginBtn.clickButton();
        // Insert password
        loginPage.setRegisterPassword(ConfProperties.getProperty("passwordNew"));
        loginPage.acceptCookies();
        // Click Login button
        firstVisitPage = loginPage.registerLoginBtn2.clickButton(FirstVisitPagePWA.class);
    }

    @Test
    @Category({FirstVisitUserPWATest.class, PositiveTests.class})
    @DisplayName("1.First Visit User, the positive case")
    public void test1FirstVisitUser() {
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Check the data you entered when registering
        controlPanelPWA.userProfileDashboard.userFirstNameWelcomeBack.checkPropertyHeader("Welcome back, Test!");
        controlPanelPWA.userProfileDashboard.userFirstName.checkPropertyHeader("Test Name");
        controlPanelPWA.userProfileDashboard.userEmailDashboard.checkPropertyHeader("Email: " + email);
        controlPanelPWA.userProfileDashboard.userPhoneDashboard.checkPropertyHeader("Phone: +17777777777");
    }

    @Test
    @Category({FirstVisitUserPWATest.class, NegativeTests.class})
    @DisplayName("2.First Visit User with blank inputs, the negative case")
    public void test2FirstVisitUserWithBlankInput() {
        // Accepting cookies
        firstVisitPage.acceptCookies();
        // Checking messages about empty fields
        firstVisitPage.checkErrorMassageFirstVisit();
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
    }

    @After
    public void logout() throws Exception {
        // Logout
        controlPanelPWA.userLogout();
        // Delete prospect
        APIHelper.deleteProspectByMail(email);
        // Delete study
        APIHelper.deleteStudyById(studyId);
        // Finishing the web driver
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        // Finishing the web driver
        closeWebDriver();
    }
}
