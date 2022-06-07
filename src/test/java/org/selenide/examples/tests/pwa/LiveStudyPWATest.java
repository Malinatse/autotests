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
import org.selenide.examples.PWA.pages.LiveStudyPagePWA;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

@DisplayName("PWA Live study tests")
public class LiveStudyPWATest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPagePWA controlPanelPWA;
    public static LiveStudyPagePWA liveStudyPage;
    public static ControlPanelPage controlPanel;
    private static StudyEditor studyEditor;
    public SectionGroupsParticipants gp;
    public SectionAssignModerator am;
    private SectionTopicsAndQuestions tq;
    public static CreateEditQuestionModal questionModal;
    public static CreateEditTopicModal topicModal;
    public static FirstVisitPagePWA firstVisitPage;
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
        // Add 2 group
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        gp.sectionCreateGroups.addGroupBtn.clickButton();
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
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("1.Live study test with all types of questions, the positive case")
    public void test1LiveStudyTestWithAllTypesOfQuestions(){
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicNameWithAllTypeOfQuestions");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        // Add questions of all type
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createYesNoQuestion("Name2","Question2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createMultipleChoiceQuestion("Name3","Question3","answer1","answer2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSliderQuestion("Name4","Question4","0","2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createStarsQuestion("Name5","Question5",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSingleChoiceQuestion("Name6","Question6","answer1","answer2",false);
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
        // Going into the first Live Study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Check the name, date
        liveStudyPage.studyName.checkPropertyHeader("TestStudy");
        liveStudyPage.studyDates.checkPropertyHeader("Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate")));
        // Check the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("6 Questions");
        // Check the different types of questions in the questions and answer them
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        liveStudyPage.clickQuestion(0);
        liveStudyPage.questions.get(1).titleQuestion.checkPropertyHeader("Name2");
        liveStudyPage.questions.get(1).questionValue.checkPropertyHeader("Question2");
        liveStudyPage.questions.get(1).questionRadio.get(0).clickRadio();
        liveStudyPage.questions.get(1).sendBtn.clickButton();
        liveStudyPage.questions.get(1).questionRadio.get(0).checkRadio();
        liveStudyPage.clickQuestion(1);
        liveStudyPage.questions.get(2).titleQuestion.checkPropertyHeader("Name3");
        liveStudyPage.questions.get(2).questionValue.checkPropertyHeader("Question3");
        liveStudyPage.questions.get(2).questionCheckbox.get(0).clickCheckBoxQuestion();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).clickCheckBoxQuestion();
        liveStudyPage.questions.get(2).sendBtn.clickButton();
        liveStudyPage.questions.get(2).questionCheckbox.get(0).checkCheckBox();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).checkCheckBox();
        liveStudyPage.clickQuestion(2);
        liveStudyPage.questions.get(3).titleQuestion.checkPropertyHeader("Name4");
        liveStudyPage.questions.get(3).questionValue.checkPropertyHeader("Question4");
        liveStudyPage.questions.get(3).setQuestionIncrementBtn(1);
        liveStudyPage.questions.get(3).sendBtn.clickButton();
        liveStudyPage.questions.get(3).questionSliderValue.checkPropertyHeader("1");
        liveStudyPage.clickQuestion(3);
        liveStudyPage.questions.get(4).titleQuestion.checkPropertyHeader("Name5");
        liveStudyPage.questions.get(4).questionValue.checkPropertyHeader("Question5");
        liveStudyPage.questions.get(4).questionStarBlockBtn.get(3).clickButton();
        liveStudyPage.questions.get(4).sendBtn.clickButton();
        liveStudyPage.questions.get(4).checkStarsCount(4);
        liveStudyPage.questions.get(4).questionStarBlockBtn.get(4).clickButton();
        liveStudyPage.questions.get(4).checkStarsCount(4);
        liveStudyPage.clickQuestion(4);
        liveStudyPage.questions.get(5).titleQuestion.checkPropertyHeader("Name6");
        liveStudyPage.questions.get(5).questionValue.checkPropertyHeader("Question6");
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(1).clickRadio();
        liveStudyPage.questions.get(5).sendBtn.clickButton();
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(1).checkRadio();
        liveStudyPage.clickQuestion(5);
        // Check the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("100%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("0%");
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("2.Go to dashboard after each answer to a question, the positive case")
    public void test2GoToDashboardAfterEachAnswerToQuestion(){
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicNameWithAllTypeOfQuestions");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        // Add questions of all type
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createYesNoQuestion("Name2","Question2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createMultipleChoiceQuestion("Name3","Question3","answer1","answer2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSliderQuestion("Name4","Question4","0","2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createStarsQuestion("Name5","Question5",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSingleChoiceQuestion("Name6","Question6","answer1","answer2",false);
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
        liveStudyPage.studyDates.checkPropertyHeader("Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate")));
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("6 Questions");
        // Check the different types of questions and answer them by going to the Dashboard after each one
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        // Answering the question
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        // Go to live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("17%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("83%");
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        liveStudyPage.questions.get(1).titleQuestion.checkPropertyHeader("Name2");
        liveStudyPage.questions.get(1).questionValue.checkPropertyHeader("Question2");
        // Answering the question
        liveStudyPage.questions.get(1).questionRadio.get(0).clickRadio();
        liveStudyPage.questions.get(1).sendBtn.clickButton();
        liveStudyPage.questions.get(1).questionRadio.get(0).checkRadio();
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        // Go to live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("34%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("66%");
        liveStudyPage.questions.get(1).questionRadio.get(0).checkRadio();
        liveStudyPage.questions.get(2).titleQuestion.checkPropertyHeader("Name3");
        liveStudyPage.questions.get(2).questionValue.checkPropertyHeader("Question3");
        // Answering the question
        liveStudyPage.questions.get(2).questionCheckbox.get(0).clickCheckBoxQuestion();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).clickCheckBoxQuestion();
        liveStudyPage.questions.get(2).sendBtn.clickButton();
        liveStudyPage.questions.get(2).questionCheckbox.get(0).checkCheckBox();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).checkCheckBox();
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        // Go to live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("50%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("50%");
        liveStudyPage.questions.get(2).questionCheckbox.get(0).checkCheckBox();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).checkCheckBox();
        liveStudyPage.questions.get(3).titleQuestion.checkPropertyHeader("Name4");
        liveStudyPage.questions.get(3).questionValue.checkPropertyHeader("Question4");
        // Answering the question
        liveStudyPage.questions.get(3).setQuestionIncrementBtn(1);
        liveStudyPage.questions.get(3).sendBtn.clickButton();
        liveStudyPage.questions.get(3).questionSliderValue.checkPropertyHeader("1");
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        // Go to live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("67%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("33%");
        liveStudyPage.questions.get(3).questionSliderValue.checkPropertyHeader("1");
        liveStudyPage.questions.get(4).titleQuestion.checkPropertyHeader("Name5");
        liveStudyPage.questions.get(4).questionValue.checkPropertyHeader("Question5");
        // Answering the question
        liveStudyPage.questions.get(4).questionStarBlockBtn.get(3).clickButton();
        liveStudyPage.questions.get(4).sendBtn.clickButton();
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        // Go to live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("84%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("16%");
        liveStudyPage.questions.get(5).titleQuestion.checkPropertyHeader("Name6");
        liveStudyPage.questions.get(5).questionValue.checkPropertyHeader("Question6");
        // Answering the question
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(1).clickRadio();
        liveStudyPage.questions.get(5).sendBtn.clickButton();
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(1).checkRadio();
        liveStudyPage.clickQuestion(5);
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        // Go to live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("100%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("0%");
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("3.Go to dashboard after each answer to a question without clicking submit, the positive case")
    public void test3GoToDashboardAfterEachAnswerToQuestionWithoutClickingSubmit(){
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicNameWithAllTypeOfQuestions");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        // Add questions of all type
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createYesNoQuestion("Name2","Question2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createMultipleChoiceQuestion("Name3","Question3","answer1","answer2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSliderQuestion("Name4","Question4","0","2",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createStarsQuestion("Name5","Question5",false);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSingleChoiceQuestion("Name6","Question6","answer1","answer2",false);
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
        liveStudyPage.studyDates.checkPropertyHeader("Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate")));
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("6 Questions");
        // Check questions of different types in the topic and answer them without pressing "send"
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(1).titleQuestion.checkPropertyHeader("Name2");
        liveStudyPage.questions.get(1).questionValue.checkPropertyHeader("Question2");
        liveStudyPage.questions.get(1).questionRadio.get(0).clickRadio();
        liveStudyPage.questions.get(2).titleQuestion.checkPropertyHeader("Name3");
        liveStudyPage.questions.get(2).questionValue.checkPropertyHeader("Question3");
        liveStudyPage.questions.get(2).questionCheckbox.get(0).clickCheckBoxQuestion();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).clickCheckBoxQuestion();
        liveStudyPage.questions.get(3).titleQuestion.checkPropertyHeader("Name4");
        liveStudyPage.questions.get(3).questionValue.checkPropertyHeader("Question4");
        liveStudyPage.questions.get(3).setQuestionIncrementBtn(1);
        liveStudyPage.questions.get(4).titleQuestion.checkPropertyHeader("Name5");
        liveStudyPage.questions.get(4).questionValue.checkPropertyHeader("Question5");
        liveStudyPage.questions.get(4).questionStarBlockBtn.get(3).clickButton();
        liveStudyPage.questions.get(5).titleQuestion.checkPropertyHeader("Name6");
        liveStudyPage.questions.get(5).questionValue.checkPropertyHeader("Question6");
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(1).clickRadio();
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Go to dashboard
        liveStudyPage.crumbsLine.clickHome();
        $x("//button[contains(@class, \"LLButtonNew_small\")][2]").click();
        // Go back to the Live study and press the "send" button
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        liveStudyPage.questions.get(1).sendBtn.clickButton();
        liveStudyPage.questions.get(1).questionRadio.get(0).checkRadio();
        liveStudyPage.questions.get(2).sendBtn.clickButton();
        liveStudyPage.questions.get(2).questionCheckbox.get(0).checkCheckBox();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).checkCheckBox();
        liveStudyPage.questions.get(3).sendBtn.clickButton();
        liveStudyPage.questions.get(3).questionSliderValue.checkPropertyHeader("1");
        liveStudyPage.questions.get(4).sendBtn.clickButton();
        liveStudyPage.questions.get(4).checkStarsCount(4);
        liveStudyPage.questions.get(5).sendBtn.clickButton();
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(1).checkRadio();
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("100%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("0%");
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("4. Live study with all types of questions and additional answer input, the positive case")
    public void test4LiveStudyWithAllTypesOfQuestionsAndAdditionalAnswerInput(){
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicNameWithAllTypeOfQuestions");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        // Add questions of all type with text input
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createTextQuestion("Name1","Question1");
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createYesNoQuestion("Name2","Question2",true);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createMultipleChoiceQuestion("Name3","Question3","answer1","answer2",true);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSliderQuestion("Name4","Question4","0","2",true);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createStarsQuestion("Name5","Question5",true);
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.createSingleChoiceQuestion("Name6","Question6","answer1","answer2",true);
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
        // Going into the first Live Study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the name
        liveStudyPage.studyName.checkPropertyHeader("TestStudy");
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("6 Questions");
        // Check the different types of questions in the questions and answer them
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        liveStudyPage.questions.get(1).titleQuestion.checkPropertyHeader("Name2");
        liveStudyPage.questions.get(1).questionValue.checkPropertyHeader("Question2");
        liveStudyPage.questions.get(1).questionRadio.get(1).clickRadio();
        liveStudyPage.questions.get(1).questionInput.setClearInput("Answer2",false);
        liveStudyPage.questions.get(1).sendBtn.clickButton();
        liveStudyPage.questions.get(1).questionRadio.get(1).checkRadio();
        liveStudyPage.questions.get(1).questionTextLocked.get(0).checkPropertyHeader("Answer2");
        liveStudyPage.questions.get(2).titleQuestion.checkPropertyHeader("Name3");
        liveStudyPage.questions.get(2).questionValue.checkPropertyHeader("Question3");
        liveStudyPage.questions.get(2).questionCheckbox.get(1).clickCheckBoxQuestion();
        liveStudyPage.questions.get(2).questionInput.setClearInput("Answer3",false);
        liveStudyPage.questions.get(2).sendBtn.clickButton();
        liveStudyPage.questions.get(2).questionCheckbox.get(1).checkCheckBox();
        liveStudyPage.questions.get(2).questionTextLocked.get(0).checkPropertyHeader("Answer3");
        liveStudyPage.questions.get(3).titleQuestion.checkPropertyHeader("Name4");
        liveStudyPage.questions.get(3).questionValue.checkPropertyHeader("Question4");
        liveStudyPage.questions.get(3).setQuestionIncrementBtn(1);
        liveStudyPage.questions.get(3).questionInput.setClearInput("Answer4",false);
        liveStudyPage.questions.get(3).sendBtn.clickButton();
        liveStudyPage.questions.get(3).questionSliderValue.checkPropertyHeader("1");
        liveStudyPage.questions.get(3).questionTextLocked.get(0).checkPropertyHeader("Answer4");
        liveStudyPage.questions.get(4).titleQuestion.checkPropertyHeader("Name5");
        liveStudyPage.questions.get(4).questionValue.checkPropertyHeader("Question5");
        liveStudyPage.questions.get(4).questionStarBlockBtn.get(3).clickButton();
        liveStudyPage.questions.get(4).questionInput.setClearInput("Answer5",false);
        liveStudyPage.questions.get(4).sendBtn.clickButton();
        liveStudyPage.questions.get(4).questionTextLocked.get(0).checkPropertyHeader("Answer5");
        liveStudyPage.questions.get(4).checkStarsCount(4);
        liveStudyPage.questions.get(5).titleQuestion.checkPropertyHeader("Name6");
        liveStudyPage.questions.get(5).questionValue.checkPropertyHeader("Question6");
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(0).clickRadio();
        liveStudyPage.questions.get(5).questionInput.setClearInput("Answer6",false);
        liveStudyPage.questions.get(5).sendBtn.clickButton();
        liveStudyPage.questions.get(5).questionSingleChoiceRadio.get(0).checkRadio();
        liveStudyPage.questions.get(5).questionTextLocked.get(0).checkPropertyHeader("Answer6");
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("100%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("0%");
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("5. Live study with division of questions into groups, the positive case")
    public void test5LiveStudyWithDivisionOfQuestionsIntoGroups(){
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicName1");
        // Add questions
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.questionTitle.setClearInput("Name1",false);
        questionModal.questionType.setClearInput("Text Field", false);
        questionModal.questionBody.setClearMultilinedText("Question1",false);
        questionModal.radioGroup.clickRadioSpan();
        $x(".//*[@data-e2e='automaticallyResolveModalCheckbox']").scrollIntoView(true);
        questionModal.selectedGroupBtn.get(0).clickButton();
        questionModal.saveQuestion.clickButton();
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.questionTitle.setClearInput("Name2",false);
        questionModal.questionType.setClearInput("Text Field", false);
        questionModal.questionBody.setClearMultilinedText("Question2",false);
        questionModal.radioGroup.clickRadioSpan();
        $x(".//*[@data-e2e='automaticallyResolveModalCheckbox']").scrollIntoView(true);
        questionModal.selectedGroupBtn.get(1).clickButton();
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicName2");
        // Add questions
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        questionModal.questionTitle.setClearInput("Name1",false);
        questionModal.questionType.setClearInput("Text Field", false);
        questionModal.questionBody.setClearMultilinedText("Question1",false);
        questionModal.radioGroup.clickRadioSpan();
        $x(".//*[@data-e2e='automaticallyResolveModalCheckbox']").scrollIntoView(true);
        questionModal.selectedGroupBtn.get(0).clickButton();
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicName3");
        // Add questions
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        questionModal.questionTitle.setClearInput("Name2",false);
        questionModal.questionType.setClearInput("Text Field", false);
        questionModal.questionBody.setClearMultilinedText("Question2",false);
        questionModal.radioGroup.clickRadioSpan();
        $x(".//*[@data-e2e='automaticallyResolveModalCheckbox']").scrollIntoView(true);
        questionModal.selectedGroupBtn.get(1).clickButton();
        questionModal.saveQuestion.clickButton();
        // Activate study
        $x("//body").scrollTo();
        studyEditor.studyDetails.studyActivateBtn.clickButton();
        $x("//button[contains(@class, \"vue-dialog-button--confirm\")]").click();
        $x("//span[contains(text(), \"Live\")]").shouldBe(Condition.exist, Duration.ofSeconds(10));
        //Open authorization page
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
        // Going into the first Live Study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Checking the name
        liveStudyPage.studyName.checkPropertyHeader("TestStudy");
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicName1");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("1 Question");
        liveStudyPage.topics.get(1).topicNumber.checkPropertyHeader("Topic 2");
        liveStudyPage.topics.get(1).topicName.checkPropertyHeader("TopicName2");
        liveStudyPage.topics.get(1).questionNum.checkPropertyHeader("1 Question");
        // Check the questions in the questions and answer them
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        liveStudyPage.topics.get(1).topicName.clickPropertyHeader();
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        // Checking the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("100%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("0%");
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("6.Live study test with full date, the positive case")
    public void test6LiveStudyTestFullDate(){
        // Add end date
        studyEditor.studyDetails.studyEndDatePicker.setClearInput(ConfProperties.getProperty("studyTestEndDate"),false);
        studyEditor.studyDetails.studySaveBtn.clickButton();
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
        // Insert login
        loginPage.setLogin(email);
        // Click Login button
        loginPage.loginBtn.clickButton();
        // Insert password
        loginPage.setRegisterPassword(ConfProperties.getProperty("passwordNew"));
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
        // Check date
        controlPanelPWA.liveStudyCardsPWA.get(0).studyDate.checkPropertyHeader("Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"))+" - "+(ConfProperties.getProperty("studyTestEndDate")));
        // Go to Live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        liveStudyPage.studyDates.checkPropertyHeader("Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"))+" - "+(ConfProperties.getProperty("studyTestEndDate")));
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("7.Live study test with auto probe, the positive case")
    public void test7LiveStudyTestWithAutoProbe(){
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        topicModal.createTopic("TopicName");
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicName");
        // Add questions
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        questionModal.modalQuestionAddProbeBtn.clickButton();
        questionModal.modalQuestionProbeRadio.clickRadio();
        questionModal.modalQuestionProbeText.setClearMultilinedText("AutoProbe1",false);
        $x(".//*[@data-e2e='radioAllParticipant']").scrollIntoView(true);
        questionModal.modalQuestionProbeBtn.clickButton();
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
        // Go to Live study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Answering the question
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        // Check answer and auto probe
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        liveStudyPage.questions.get(0).questionTextLocked.get(1).checkPropertyHeader("AutoProbe1");
    }

    @Test
    @Category({LiveStudyPWATest.class, PositiveTests.class})
    @DisplayName("8.Live study test with image, the positive case")
    public void test8LiveStudyTestWithImage(){
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
        // Going into the first Live Study
        liveStudyPage = controlPanelPWA.goToLiveStudyId(0);
        // Check the name, date
        liveStudyPage.studyName.checkPropertyHeader("TestStudy");
        liveStudyPage.studyDates.checkPropertyHeader("Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate")));
        // Check the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("0%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("100%");
        // Check the Topics title and the number of questions
        liveStudyPage.topics.get(0).topicNumber.checkPropertyHeader("Topic 1");
        liveStudyPage.topics.get(0).topicName.checkPropertyHeader("TopicName");
        liveStudyPage.topics.get(0).questionNum.checkPropertyHeader("1 Question");
        // Check the different types of questions in the questions and answer them
        liveStudyPage.questions.get(0).titleQuestion.checkPropertyHeader("Name1");
        liveStudyPage.questions.get(0).questionValue.checkPropertyHeader("Question1");
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        // Add image
        File file = new File("src/test/java/../resources/image.png");
        $x(".//*[@data-e2e=\"imageUpload\"]/label/input").uploadFile(file);
        $x(".//*[@data-e2e=\"iconImage\"]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        $x(".//*[@data-e2e=\"iconImage\"]").shouldBe(Condition.visible, Duration.ofSeconds(10));
        $x(".//span[contains(text(), \"Add More\")]").shouldBe(Condition.visible, Duration.ofSeconds(100));
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
        // Check the percentages
        liveStudyPage.answeredPercents.checkPropertyHeader("100%");
        liveStudyPage.completedPercents.checkPropertyHeader("0%");
        liveStudyPage.notStartedPercents.checkPropertyHeader("0%");
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
