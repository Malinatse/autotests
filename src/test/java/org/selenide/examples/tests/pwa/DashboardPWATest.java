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
import org.selenide.examples.PWA.pages.StudyDetailsPagePWA;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

@DisplayName("PWA Home page tests")
public class DashboardPWATest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPagePWA controlPanelPWA;
    public static ControlPanelPage controlPanel;
    public static StudyDetailsPagePWA detailsPage;
    public static StudyEditor studyEditor;
    public SectionGroupsParticipants gp;
    public SectionAssignModerator am;
    public SectionTopicsAndQuestions tq;
    public static CreateEditQuestionModal questionModal;
    public static CreateEditTopicModal topicModal;
    public static FirstVisitPagePWA firstVisitPage;
    public   String invLnk;
    String email;
    HTTPApiHelper APIHelper = new HTTPApiHelper();
    List<String> studyIds = new ArrayList<>();
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

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
        reviewdb.setApprovalStatus("Approved", 0);
        String id = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        studyIds.add(id);
        invLnk = APIHelper.getStudyInviteById(id);
        // Assign moderator
        am = studyEditor.studySections.moderatorData.clickHeader();
        am.addModeratorBtn.clickButton();
        am.setModeratorName(0, ConfProperties.getProperty("adminLogin"));
        am.setModeratorGroup(0, "Unassigned");
        am.addModeratorBtn.clickButton();
        // Add questions
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("testName");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName");
        // Add questions of all type
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Body1");
        // Activate study
        $x("//body").scrollTo();
        studyEditor.studyDetails.studyActivateBtn.clickButton();
        $x("//button[contains(@class, \"vue-dialog-button--confirm\")]").click();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Create new study
        controlPanel.createStudyBtn.clickButton();
        studyEditor = controlPanel.standardStudyBtn.clickButton(StudyEditor.class);
        studyEditor.createStudy("InviteApprovedDraftStudy");
        // Unwrap main wrapper
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionProspectsDatabase.groupHeader.clickPropertyHeader();
        // Add participant by email
        gp.sectionProspectsDatabase.prospectsEmailHeader.clickButton();
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
        reviewdb.setApprovalStatus("Approved", 0);
        id = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        studyIds.add(id);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Create new study
        controlPanel.createStudyBtn.clickButton();
        studyEditor = controlPanel.standardStudyBtn.clickButton(StudyEditor.class);
        studyEditor.createStudy("InvitedPendingDraftStudy");
        // Unwrap main wrapper
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionProspectsDatabase.groupHeader.clickPropertyHeader();
        // Get prospects table
        // Add participant by email
        gp.sectionProspectsDatabase.prospectsEmailHeader.clickButton();
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
        //reviewdb.setApprovalStatus("Pending", 0);
        id = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        studyIds.add(id);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Create new study
        controlPanel.createStudyBtn.clickButton();
        studyEditor = controlPanel.standardStudyBtn.clickButton(StudyEditor.class);
        studyEditor.createStudy("InviteDraftStudy");
        // Unwrap main wrapper
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionProspectsDatabase.groupHeader.clickPropertyHeader();
        // Get prospects table
        // Add participant by email
        gp.sectionProspectsDatabase.prospectsEmailHeader.clickButton();
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
        id = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        studyIds.add(id);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Create new study
        controlPanel.createStudyBtn.clickButton();
        studyEditor = controlPanel.standardStudyBtn.clickButton(StudyEditor.class);
        studyEditor.createStudy("InviteDraftStudy");
        // Unwrap main wrapper
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        $x("//body").scrollTo();
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionProspectsDatabase.groupHeader.clickPropertyHeader();
        // Get prospects table
        // Add participant by email
        gp.sectionProspectsDatabase.prospectsEmailHeader.clickButton();
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
        id = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        studyIds.add(id);
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
    @Category({DashboardPWATest.class, PositiveTests.class})
    @DisplayName("1.Checking a user's profile and study of each type on the dashboard, the positive case")
    public void test1CheckingUsersProfileAndStudyOfEachTypeOnDashboard(){
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
        // Check the presence of all possible types of stadiums and their content
        // Checking the Live study
        controlPanelPWA.thisIsLiveStudy(0);
        controlPanelPWA.liveStudyCardsPWA.get(0).studyName.checkPropertyHeader("TestStudy");
        controlPanelPWA.liveStudyCardsPWA.get(0).answeredPercents.checkPropertyHeader("0%");
        controlPanelPWA.liveStudyCardsPWA.get(0).completedPercents.checkPropertyHeader("0%");
        controlPanelPWA.liveStudyCardsPWA.get(0).notStartedPercents.checkPropertyHeader("100%");
        // Checking the Waiting study
        controlPanelPWA.thisIsWaitingStudy(0);
        controlPanelPWA.waitingStudyCardsPWA.get(0).studyName.checkPropertyHeader("InviteApprovedDraftStudy");
        controlPanelPWA.invitedStudyCardsPWA.get(2).acceptInvitedStudyBtn.clickButton();
        // Checking the Pending study
        controlPanelPWA.thisIsPendingStudy(0);
        controlPanelPWA.pendingStudyCardsPWA.get(0).studyName.checkPropertyHeader("InvitedPendingDraftStudy");
        // Checking the Invited study
        controlPanelPWA.thisIsInvitedStudy(0);
        controlPanelPWA.invitedStudyCardsPWA.get(0).studyName.checkPropertyHeader("InviteDraftStudy");
        controlPanelPWA.thisIsInvitedStudy(1);
        controlPanelPWA.invitedStudyCardsPWA.get(0).studyName.checkPropertyHeader("InviteDraftStudy");
        controlPanelPWA.checkPrivateAndTerms();
    }

    @Test
    @Category({DashboardPWATest.class, PositiveTests.class})
    @DisplayName("2.Checking waiting and pending study, the positive case")
    public void test2CheckingWaitingAndPendingStudy() {
        // Enter the necessary data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Check the data you entered when registering
        controlPanelPWA.userProfileDashboard.userFirstNameWelcomeBack.checkPropertyHeader("Welcome back, Test!");
        controlPanelPWA.userProfileDashboard.userFirstName.checkPropertyHeader("Test Name");
        controlPanelPWA.userProfileDashboard.userEmailDashboard.checkPropertyHeader("Email: " + email);
        controlPanelPWA.userProfileDashboard.userPhoneDashboard.checkPropertyHeader("Phone: +17777777777");
        // Checking for waiting and pending studies
        controlPanelPWA.thisIsWaitingStudy(0);
        controlPanelPWA.waitingStudyCardsPWA.get(0).studyName.checkPropertyHeader("InviteApprovedDraftStudy");
        controlPanelPWA.invitedStudyCardsPWA.get(2).acceptInvitedStudyBtn.clickButton();
        controlPanelPWA.thisIsPendingStudy(0);
        controlPanelPWA.pendingStudyCardsPWA.get(0).studyName.checkPropertyHeader("InvitedPendingDraftStudy");
        // Go to the waiting study page
        detailsPage = controlPanelPWA.waitingStudyCardsPWA.get(0).studyNameBtn.clickButton(StudyDetailsPagePWA.class);
        detailsPage.studyName.checkPropertyHeader("InviteApprovedDraftStudy");
        detailsPage.thisIsWaitingStudyPage();
        // Go to dashboard
        detailsPage.crumbsLine.clickHome();
        // Go to the pending study page
        detailsPage = controlPanelPWA.pendingStudyCardsPWA.get(0).studyNameBtn.clickButton(StudyDetailsPagePWA.class);
        detailsPage.studyName.checkPropertyHeader("InvitedPendingDraftStudy");
        detailsPage.thisIsPendingStudyPage();
        // Go to dashboard
        detailsPage.crumbsLine.clickHome();
    }

    @Test
    @Category({DashboardPWATest.class, PositiveTests.class})
    @DisplayName("3.Checking invited study, the positive case")
    public void test3CheckingInvitedStudy() {
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Check the data entered during registration
        controlPanelPWA.userProfileDashboard.userFirstNameWelcomeBack.checkPropertyHeader("Welcome back, Test!");
        controlPanelPWA.userProfileDashboard.userFirstName.checkPropertyHeader("Test Name");
        controlPanelPWA.userProfileDashboard.userEmailDashboard.checkPropertyHeader("Email: " + email);
        controlPanelPWA.userProfileDashboard.userPhoneDashboard.checkPropertyHeader("Phone: +17777777777");
        // Checking the availability of the two studies
        controlPanelPWA.thisIsInvitedStudy(0);
        controlPanelPWA.invitedStudyCardsPWA.get(0).studyName.checkPropertyHeader("InviteDraftStudy");
        controlPanelPWA.thisIsInvitedStudy(1);
        controlPanelPWA.invitedStudyCardsPWA.get(1).studyName.checkPropertyHeader("InviteDraftStudy");
        // We accept the invitation in the first
        controlPanelPWA.invitedStudyCardsPWA.get(0).acceptInvitedStudyBtn.clickButton();
        // Check that Study has moved to the pending type
        controlPanelPWA.pendingStudyCardsPWA.get(0).studyName.checkPropertyHeader("InviteDraftStudy");
        controlPanelPWA.thisIsPendingStudy(0);
        detailsPage = controlPanelPWA.pendingStudyCardsPWA.get(0).studyNameBtn.clickButton(StudyDetailsPagePWA.class);
        detailsPage.studyName.checkPropertyHeader("InviteDraftStudy");
        detailsPage.thisIsPendingStudyPage();
        detailsPage.crumbsLine.clickHome();
        // Declining the invitation to the second
        controlPanelPWA.invitedStudyCardsPWA.get(0).rejectInvitedStudyBtn.clickButton();
    }

    @After
    public void logout() throws Exception {
        // Delete prospect
        APIHelper.deleteProspectByMail(email);
        // Delete all study
        for (String studyId : studyIds) {
            APIHelper.deleteStudyById(studyId);
        }
        // Logout
        controlPanelPWA.userLogout();
        // Finishing the web driver
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        // Finishing the web driver
        closeWebDriver();
    }
}
