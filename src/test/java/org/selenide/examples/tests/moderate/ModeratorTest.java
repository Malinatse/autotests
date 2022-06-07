package org.selenide.examples.tests.moderate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.HTTPApiHelper;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.PWA.pages.FirstVisitPagePWA;
import org.selenide.examples.PWA.pages.LiveStudyPagePWA;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.moderate.ParticipantsContainer;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.LoginTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.modals.FileModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;
import org.selenide.examples.testdriver.pages.ModeratorPage;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@DisplayName("Moderator tests")
public class ModeratorTest {
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
    public static FileModal fileModal;
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
        liveStudyPage.questions.get(0).questionInput.setClearInput("Answer1",false);
        liveStudyPage.questions.get(0).sendBtn.clickButton();
        liveStudyPage.questions.get(0).questionTextLocked.get(0).checkPropertyHeader("Answer1");
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
        pc.participantsConversationStream.get(0).partAnswer.checkPropertyHeader("Answer1");
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("1. Respond to a question by sending text, the positive case")
    public void test1RespondToQuestionBySendingText(){
        // Ответить на ответ участника текстом
        pc.participantsConversationStream.get(0).moderateAnswerInput.setClearInput("ModerateAnswer1",false);
        // Нажать на кнопку "send"
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленный ответ
        pc.participantsConversationStream.get(0).nextAnswer.get(0).nextAnswerText.checkPropertyHeader("ModerateAnswer1");
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("2. Respond to a question by sending an image, the positive case")
    public void test2RespondToQuestionBySendingImage(){
        // Добавить изображение для ответа
        File file = new File("src/test/java/../resources/image.png");
        $x(".//*[@data-e2e=\"filesUploader\"]//input").uploadFile(file);
        $x("//span[contains(text(), \"Uploading Media...\")]").shouldBe(Condition.visible, Duration.ofSeconds(1));
        $x("//*[@data-e2e='iconImage']/img").shouldBe(Condition.visible, Duration.ofSeconds(15));
        // Нажать на кнопку "send"
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленную картинку
        $x(".//*[@data-e2e=\"iconImage\"]").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("3. Respond to a question by sending video, the positive case")
    public void test3RespondToQuestionBySendingVideo(){
        // Добавить видео для ответа
        File file = new File("src/test/java/../resources/video.mp4");
        $x(".//*[@data-e2e=\"filesUploader\"]//input").uploadFile(file);
        $x("//span[contains(text(), \"Uploading Media...\")]").shouldBe(Condition.visible, Duration.ofSeconds(5));
        // Нажать на кнопку "send"
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@data-e2e='moderateAnswerInputMod']//button")));
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленное видео
        $x(".//*[@data-e2e=\"videoIcon\"]").shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("4. Expand the image to a larger view, the positive case")
    public void test4ExpandImageToLargerView(){
        // Добавить изображение для ответа
        File file = new File("src/test/java/../resources/image.png");
        $x(".//*[@data-e2e=\"filesUploader\"]//input").uploadFile(file);
        $x("//span[contains(text(), \"Uploading Media...\")]").shouldBe(Condition.visible, Duration.ofSeconds(1));
        $x("//*[@data-e2e='iconImage']/img").shouldBe(Condition.visible, Duration.ofSeconds(15));
        // Нажать на кнопку "send"
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленную картинку
        pc.participantsConversationStream.get(0).iconImageFile.shouldBe(Condition.visible,Duration.ofSeconds(10));
        $x(".//*[@data-e2e=\"iconImage\"]").shouldBe(Condition.visible, Duration.ofSeconds(10));
        $x(".//*[@data-e2e='nextAnswerMod']/div/div[2]").shouldBe(Condition.visible, Duration.ofSeconds(10));
        // Открыть картинку
        fileModal = pc.participantsConversationStream.get(0).openFileModal.clickButton(FileModal.class);
        $x("//*[@data-e2e='fileModal']//img").shouldBe(Condition.visible, Duration.ofSeconds(10));
        fileModal.closeBtn.clickButton();
        if (fileModal.imageModal.isDisplayed()){
            fileModal.closeBtn.clickButton();
        }
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("5. Expand a video to a larger view and see the video play, the positive case")
    public void test5ExpandVideoToLargerViewAndSeeVideoPlay(){
        // Добавить видео для ответа
        File file = new File("src/test/java/../resources/video.mp4");
        $x(".//*[@data-e2e=\"filesUploader\"]//input").uploadFile(file);
        $x("//span[contains(text(), \"Uploading Media...\")]").shouldBe(Condition.visible, Duration.ofSeconds(5));
        // Нажать на кнопку "send"
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@data-e2e='moderateAnswerInputMod']//button")));
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленное видео
        $x(".//*[@data-e2e=\"videoIcon\"]").shouldBe(Condition.visible, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@data-e2e='iconImage']")));
        // Открыть и запустить видео
        fileModal = pc.participantsConversationStream.get(0).openFileModal.clickButton(FileModal.class);
        if (!fileModal.videoModal.isDisplayed()) {
            pc.participantsConversationStream.get(0).openFileModal.clickButton(FileModal.class);
        }
        $x("//*[contains(text(), \" Video\")]").shouldBe(Condition.visible, Duration.ofSeconds(5));
        fileModal.closeBtn.clickButton();
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("6. Edit a moderator response, the positive case")
    public void test6EditModeratorResponse(){
        // Ответить на ответ участника текстом
        pc.participantsConversationStream.get(0).moderateAnswerInput.setClearInput("ModerateAnswer1",false);
        // Нажать на кнопку "send"
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленный ответ
        pc.participantsConversationStream.get(0).nextAnswer.get(0).nextAnswerText.checkPropertyHeader("ModerateAnswer1");
        // Отредактировать отправленный ответ
        pc.participantsConversationStream.get(0).nextAnswer.get(0).EditAnswer("NewModerateAnswer1");
        // Проверить отредактированный ответ
        pc.participantsConversationStream.get(0).nextAnswer.get(0).nextAnswerText.checkPropertyHeader("NewModerateAnswer1");
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("7. Edit a participant response, the positive case")
    public void test7EditParticipantResponse(){
        // Отредактировать ответ участника
        pc.participantsConversationStream.get(0).EditAnswerPart("NewAnswer1");
        // Проверить отредактированный ответ
        pc.participantsConversationStream.get(0).partAnswer.checkPropertyHeader("NewAnswer1");
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("8. Resolve a question, the positive case")
    public void test8ResolveQuestion(){
        // Проверяем, что вопрос еще не подтвержден
        $x("//div[contains(text(), \"Awaiting reply\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Проверяем проценты
        pc.partCard.partAllProgressPercent.checkPropertyHeader("0%");
        pc.partCard.partInProgressPercent.checkPropertyHeader("100%");
        pc.partCard.partCompletedPercent.checkPropertyHeader("0%");
        pc.partCard.partNotStartedPercent.checkPropertyHeader("0%");
        // Нажать на чекбокс "Resolve question"
        proxy.checkExistence($x("//div[contains(text(), \"Awaiting reply\")]"), true);
        pc.participantsConversationStream.get(0).resQuestionCheck.clickCheckBox();
        proxy.checkExistence($x("//div[contains(text(), \"Awaiting reply\")]"), false);
        // Проверяем проценты
        pc.partCard.partAllProgressPercent.checkPropertyHeader("100%");
        pc.partCard.partInProgressPercent.checkPropertyHeader("0%");
        pc.partCard.partCompletedPercent.checkPropertyHeader("100%");
        pc.partCard.partNotStartedPercent.checkPropertyHeader("0%");
        // Проверяем отсутствие ввода
        $x(".//*[@data-e2e='moderateAnswerInputMod']/div/div/div/div/div[1]").should(Condition.disappear);
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("9. Delete a moderator response, the positive case")
    public void test9DeleteModeratorResponse(){
        // Ответить на ответ участника текстом
        pc.participantsConversationStream.get(0).moderateAnswerInput.setClearInput("ModerateAnswer1",false);
        // Нажать на кнопку "send"
        pc.participantsConversationStream.get(0).sendBtn.clickButton();
        // Проверить отправленный ответ
        pc.participantsConversationStream.get(0).nextAnswer.get(0).nextAnswerText.checkPropertyHeader("ModerateAnswer1");
        // Удалить отправленный ответ
        pc.participantsConversationStream.get(0).nextAnswer.get(0).DeleteAnswer();
        // Проверить что ответ удален
        proxy.checkExistence($x("//*[contains(text(), \"ModerateAnswer1\")]"), false);
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("10. Delete a participant response, the positive case")
    public void test10DeleteParticipantResponse(){
        // Удалить ответ
        pc.participantsConversationStream.get(0).DeleteAnswerPart();
        // Проверить что ответ удален
        proxy.checkExistence($x("//*[contains(text(), \"Answer1\")]"), false);
    }

    @Test
    @Category({LoginTests.class, PositiveTests.class})
    @DisplayName("11. Add a star to a participant response, the positive case")
    public void test11AddStarToParticipantResponse(){
        pc.participantsConversationStream.get(0).star.clickButton();
        $x(".//*[@data-e2e='star']/div/*[contains(@class, \"icon fill-current text-neutral-01\")]").should(Condition.visible);
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
