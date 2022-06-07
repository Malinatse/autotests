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
import org.selenide.examples.PWA.pages.*;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.testdriver.elements.screeenings.ScreeningsLayout;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.modals.AddScreeningQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("PWA Screening question tests")
public class ScreeningQuestionPWATest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPagePWA controlPanelPWA;
    public static ControlPanelPage controlPanel;
    private static StudyEditor studyEditor;
    private SectionGroupsParticipants gp;
    private SectionAssignModerator am;
    private SectionTopicsAndQuestions tq;
    private ScreeningsLayout sq;
    public static CreateEditQuestionModal questionModal;
    public static CreateEditTopicModal topicModal;
    public static FirstVisitPagePWA firstVisitPage;
    public static AddScreeningQuestionModal addScreeningQuestionModal;
    public static StudyScreeningPagePWA sqPagePWA;
    private String invLnk;
    String email;
    String studyId;
    HTTPApiHelper APIHelper = new HTTPApiHelper();

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
        // Add participant by email
        gp.sectionProspectsDatabase.prospectsEmailHeader.clickButton();
        long cDate = System.currentTimeMillis();
        email = "autotest" + cDate + "@test.test";
        gp.sectionProspectsDatabase.prospectsEmailInput.setClearInput(email, true);
        gp.sectionProspectsDatabase.addProspectsBtn.clickButton();
        // Search new participant
        gp.sectionReviewParticipants.groupHeader.clickPropertyHeader();
        gp = gp.sectionReviewParticipants.performSearch(email);

    }

    @Test
    @Category({ScreeningQuestionPWATest.class, PositiveTests.class})
    @DisplayName("1.Invitation to a draft study with screening questions, the positive case")
    public void test1InvitationToDraftStudyWithScreeningQuestions () throws Exception {
        // Invite participant
        LLReviewProspectsTable reviewdb;
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Add Screening question with instruction
        gp.sectionCreateScreenings.headerText.clickPropertyHeader();
        sq= gp.sectionCreateScreenings;
        sq.addSQInstruction("SQInstruction");
        addScreeningQuestionModal = sq.screeningAddBtn.clickButton(AddScreeningQuestionModal.class);
        addScreeningQuestionModal.questionText.setClearMultilinedText("SQuestion1",false);
        addScreeningQuestionModal.SQSaveBtn.clickButton();
        controlPanel = studyEditor.crumbs.clickHome();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
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
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Accept the invitation to invite study
        sqPagePWA = controlPanelPWA.invitedStudyCardsPWA.get(0).acceptInvitedStudyBtnSQ.clickButton(StudyScreeningPagePWA.class);
        // Answer screening question
        $x("//*[contains(text(), \"SQInstruction\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        sqPagePWA.screeningQuestion.get(0).questionBody.checkPropertyHeader("SQuestion1");
        sqPagePWA.screeningQuestion.get(0).screeningAnswerText.setClearMultilinedTextSQ("SQAnswer1",false);
        controlPanelPWA = sqPagePWA.submitScreeningQuestionBtn.clickButton(ControlPanelPagePWA.class);
         // Check that study is pending
        controlPanelPWA.thisIsPendingStudy(0);
    }

    @Test
    @Category({ScreeningQuestionPWATest.class, PositiveTests.class})
    @DisplayName("2.Invitation to the study draft with screening instructions, but no questions, the positive case")
    public void test2InvitationToStudyDraftWithScreeningInstructionsButNoQuestions () throws Exception {
        // Invite participant
        LLReviewProspectsTable reviewdb;
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Add Screening instruction without question
        gp.sectionCreateScreenings.headerText.clickPropertyHeader();
        sq= gp.sectionCreateScreenings;
        sq.addSQInstruction("SQInstruction");
        controlPanel = studyEditor.crumbs.clickHome();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
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
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Accept the invitation to invite study
        controlPanelPWA.thisIsInvitedStudy(0);
        controlPanelPWA.invitedStudyCardsPWA.get(0).acceptInvitedStudyBtn.clickButton();
        // Check that study is pending
        controlPanelPWA.thisIsPendingStudy(0);
    }

    @Test
    @Category({ScreeningQuestionPWATest.class, PositiveTests.class})
    @DisplayName("3.Invitation to the study draft with screening questions, but no instructions, the positive case")
    public void test3InvitationToStudyDraftWithScreeningQuestionsButNoInstructions () throws Exception {
        // Invite participant
        LLReviewProspectsTable reviewdb;
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Add Screening questions without instruction
        gp.sectionCreateScreenings.headerText.clickPropertyHeader();
        sq= gp.sectionCreateScreenings;
        addScreeningQuestionModal = sq.screeningAddBtn.clickButton(AddScreeningQuestionModal.class);
        controlPanel = studyEditor.crumbs.clickHome();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
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
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Accept the invitation to invite study
        sqPagePWA = controlPanelPWA.invitedStudyCardsPWA.get(0).acceptInvitedStudyBtnSQ.clickButton(StudyScreeningPagePWA.class);
        // Answer screening question
        sqPagePWA.screeningQuestion.get(0).questionBody.checkPropertyHeader("SQuestion1");
        sqPagePWA.screeningQuestion.get(0).screeningAnswerText.setClearMultilinedTextSQ("SQAnswer1",false);
        sqPagePWA.submitScreeningQuestionBtn.clickButton(ControlPanelPagePWA.class);
        // Check that study is pending
        controlPanelPWA.thisIsPendingStudy(0);
    }

    @Test
    @Category({ScreeningQuestionPWATest.class, PositiveTests.class})
    @DisplayName("4.Invitation and approval in the draft study with screening questions, the positive case")
    public void test4InvitationAndApprovalInDraftStudyWithScreeningQuestions () throws Exception {
        // Invite and approved participant
        LLReviewProspectsTable reviewdb;
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        reviewdb.setApprovalStatus("Approved", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Add Screening question with instruction
        gp.sectionCreateScreenings.headerText.clickPropertyHeader();
        sq= gp.sectionCreateScreenings;
        sq.addSQInstruction("SQInstruction");
        addScreeningQuestionModal = sq.screeningAddBtn.clickButton(AddScreeningQuestionModal.class);
        addScreeningQuestionModal.questionText.setClearMultilinedText("SQuestion1",false);
        addScreeningQuestionModal.SQSaveBtn.clickButton();
        controlPanel = studyEditor.crumbs.clickHome();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
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
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Check approved study
        controlPanelPWA.thisIsWaitingStudy(0);
    }

    @Test
    @Category({ScreeningQuestionPWATest.class, PositiveTests.class})
    @DisplayName("5.Invitation and approved to a live study with screening questions, the positive case")
    public void test5InvitationAndApprovedToDraftStudyWithScreeningQuestions () throws Exception {
        // Invite participant
        LLReviewProspectsTable reviewdb;
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        reviewdb.setApprovalStatus("Approved", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Add Screening question with instruction
        gp.sectionCreateScreenings.headerText.clickPropertyHeader();
        sq= gp.sectionCreateScreenings;
        sq.addSQInstruction("SQInstruction");
        addScreeningQuestionModal = sq.screeningAddBtn.clickButton(AddScreeningQuestionModal.class);
        addScreeningQuestionModal.questionText.setClearMultilinedText("SQuestion1",false);
        addScreeningQuestionModal.SQSaveBtn.clickButton();
        sq.saveInstructionsBtn.clickButton();
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
        topicModal.createTopic("TopicName");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicName");
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        // Activate study
        $x("//body").scrollTo();
        studyEditor.studyDetails.studyActivateBtn.clickButton();
        $x("//button[contains(@class, \"vue-dialog-button--confirm\")]").click();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
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
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Check that study is live
        controlPanelPWA.thisIsLiveStudy(0);
    }

    @Test
    @Category({ScreeningQuestionPWATest.class, PositiveTests.class})
    @DisplayName("6.Invitation to a live study with screening questions, the positive case")
    public void test6InvitationToDraftStudyWithScreeningQuestions () throws Exception {
        // Invite participant
        LLReviewProspectsTable reviewdb;
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setInvitationState("\n" +
                "          Invited\n" +
                "        ", 0);
        reviewdb.setApprovalStatus("Approved", 0);
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        // Add Screening question with instruction
        gp.sectionCreateScreenings.headerText.clickPropertyHeader();
        sq= gp.sectionCreateScreenings;
        sq.addSQInstruction("SQInstruction");
        addScreeningQuestionModal = sq.screeningAddBtn.clickButton(AddScreeningQuestionModal.class);
        addScreeningQuestionModal.questionText.setClearMultilinedText("SQuestion1",false);
        addScreeningQuestionModal.SQSaveBtn.clickButton();
        sq.saveInstructionsBtn.clickButton();
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
        topicModal.createTopic("TopicName");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicName");
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        // Activate study
        $x("//body").scrollTo();
        studyEditor.studyDetails.studyActivateBtn.clickButton();
        $x("//button[contains(@class, \"vue-dialog-button--confirm\")]").click();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
        // Change the status to pending
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp.sectionProspectsDatabase.groupHeader.clickPropertyHeader();
        gp.sectionReviewParticipants.groupHeader.clickPropertyHeader();
        gp = gp.sectionReviewParticipants.performSearch(email);
        reviewdb = gp.sectionReviewParticipants.reviewProspectTable;
        reviewdb.setApprovalStatus("Pending", 0);
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
        // Enter data for registration
        firstVisitPage.enterDataForRegistration();
        firstVisitPage.acceptCookies();
        firstVisitPage.clickSubmitBtn();
        // Go to the photo download page
        $x("//*[contains(text(), \"Done! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Go to dashboard
        controlPanelPWA = firstVisitPage.logoBtn.clickButton(ControlPanelPagePWA.class);
        // Accept the invitation to invite study
        sqPagePWA = controlPanelPWA.invitedStudyCardsPWA.get(0).acceptInvitedStudyBtnSQ.clickButton(StudyScreeningPagePWA.class);
        // Answer screening question
        $x("//*[contains(text(), \"SQInstruction\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        sqPagePWA.screeningQuestion.get(0).questionBody.checkPropertyHeader("SQuestion1");
        sqPagePWA.screeningQuestion.get(0).screeningAnswerText.setClearMultilinedTextSQ("SQAnswer1",false);
        sqPagePWA.submitScreeningQuestionBtn.clickButton(ControlPanelPagePWA.class);
        // Check that study is pending
        controlPanelPWA.thisIsPendingStudy(0);
    }

    @After
    public void logout() throws Exception {
        // Logout
        controlPanelPWA.userLogout();
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
