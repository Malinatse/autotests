package org.selenide.examples.tests.moderate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.HTTPApiHelper;
import com.codeborne.selenide.WebDriverRunner;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.PWA.pages.FirstVisitPagePWA;
import org.selenide.examples.PWA.pages.LiveStudyPagePWA;
import org.selenide.examples.containers.moderate.ParticipantsContainer;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.LoginTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;
import org.selenide.examples.testdriver.pages.ModeratorPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Moderator translate tests")
public class ModeratorTranslateTest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPagePWA controlPanelPWA;
    public static LiveStudyPagePWA liveStudyPage;
    public static ControlPanelPage controlPanel;
    public static StudyEditor studyEditor;
    public SectionGroupsParticipants gp;
    public SectionAssignModerator am;
    public SectionTopicsAndQuestions tq;
    private static ParticipantsContainer pc;
    public static CreateEditQuestionModal questionModal;
    public static CreateEditTopicModal topicModal;
    public static FirstVisitPagePWA firstVisitPage;
    public static ModeratorPage moderatorPage;
    public String invLnk;
    String email;
    String studyId;
    HTTPApiHelper APIHelper = new HTTPApiHelper();
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
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
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
        // Add questions
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        // Activate study
        $x("//body").scrollTo();
        studyEditor.studyDetails.studyActivateBtn.clickButton();
        $x("//button[contains(@class, \"vue-dialog-button--confirm\")]").click();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
        // Open authorization page
        loginPage = open(invLnk, LoginPageNew.class);
        loginPage.selectEng();
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
        // Going into the first Live Study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Check the name, date
        liveStudyPage.studyName.checkPropertyHeader("TestStudy");
         // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicName");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("1 Question");
        // Check the different types of questions in the questions and answer them
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Тестовый текст",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Тестовый текст");
        controlPanelPWA.userLogout();
        // Open authorization page
        loginPage = open("/", LoginPageNew.class);
        // Insert login
        loginPage.setLogin(ConfProperties.getProperty("login"));
        // Click Login button
        loginPage.loginBtn.clickButton();
        // Insert password
        loginPage.setPassword(ConfProperties.getProperty("password"));
        // Click Login button
        controlPanel = loginPage.loginBtn2.clickButton(ControlPanelPage.class);
        // Obtain study list
        $x("//a[contains(text(), \"TestStudy\")]").shouldBe(Condition.visible, Duration.ofSeconds(30));
        controlPanel.liveStudyCards.get(0).cardHeader.checkPropertyHeader("TestStudy");
        moderatorPage =controlPanel.liveStudyCards.get(0).cardModerateBtn.clickButton(ModeratorPage.class);
        // Check study info
        moderatorPage.studyName.checkPropertyHeader("TestStudy");
        moderatorPage.adminName.checkPropertyHeader(ConfProperties.getProperty("adminLogin"));
        moderatorPage.studyDate.checkPropertyHeader(ConfProperties.getProperty("ModerateStartDate"));
        // Check question body
        pc = moderatorPage.partView;
        pc.participantsConversationStream.get(0).questionBody.checkPropertyHeader("Question1");
        // Check answer
        pc.participantsConversationStream.get(0).partAnswer.checkPropertyHeader("Тестовый текст");
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("1. Turn on auto-translate and see translated text to English, the positive case")
    public void test1TurnOnAutoTranslateAndSeeTranslatedTextToEnglish(){
        moderatorPage.translateSwitch.clickButton();
        $x("//div[contains(text(), \"Test text\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("2. Edit translated text, the positive case")
    public void test2EditTranslatedAnswer(){
        // Включить автоматический перевод
        moderatorPage.translateSwitch.clickButton();
        // Проверить перевод
        $x("//div[contains(text(), \"Test text\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Отредактировать ответ участника
        pc.participantsConversationStream.get(0).EditAnswerPart("Новый тестовый текст");
        // Проверить отредактированный ответ
        pc.participantsConversationStream.get(0).partAnswer.checkPropertyHeader("Новый тестовый текст");
        controlPanel=moderatorPage.crumbsLine.clickHome();
        moderatorPage =controlPanel.liveStudyCards.get(0).cardModerateBtn.clickButton(ModeratorPage.class);
        moderatorPage.translateSwitch.clickButton();
        moderatorPage.translateSwitch.clickButton();
        // Проверить перевод
        $x("//*[contains(text(), \"New test text\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    @After
    public void logout() throws Exception {
        // Logout
        controlPanel.userLogout();
        APIHelper.deleteProspectByMail(email);
        // Delete study
        APIHelper.deleteStudyById(studyId);
        // Finishing the web driver
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        //Finishing the web driver
        closeWebDriver();
    }
}
