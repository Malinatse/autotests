package org.selenide.examples.tests.draftstudy;

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
import org.selenide.examples.containers.studyeditor.SectionTopicsAndQuestions;
import org.selenide.examples.containers.studyeditor.StudyDetails;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.containers.vgtables.topicsandquestions.VGTable;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.DraftStudy;
import org.selenide.examples.testdriver.groupinterfaces.DraftStudyGroupTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.modals.EditTopicModal;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Draft study topics and questions tests")
public class DraftStudyTopicsAndQuestionsTest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static CreateEditTopicModal topicModal;
    public static EditTopicModal editTopicModal;
    public static CreateEditQuestionModal questionModal;
    public static ControlPanelPage controlPanel;
    private static StudyEditor studyEditor;
    public static StudyDetails sd;
    private SectionTopicsAndQuestions tq;
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
        // Set name
        sd.studyNameInput.setClearInput(ConfProperties.getProperty("studyTestName"), false);
        // Set start date
        sd.studyStartDatePicker.setClearInput(ConfProperties.getProperty("studyTestStartDate"), false);
        // Set objectives
        sd.studyObjectivesInput.setClearMultilinedText(ConfProperties.getProperty("studyTestObjectives"), false);
        // Click Save button
        sd.studyCreateBtn.clickButton();
    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("1. Checking the created values of the Topics&questions field, the positive case")
    public void test1CheckingCreatedValuesOfTopicsAndQuestionsField(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName");
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName");
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("2. Creating questions of all types, the positive case")
    public void test2CreatingQuestionsOfAllType(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
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
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TopicNameWithAllTypeOfQuestions");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.TYPE, "Yes/No");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.TYPE, "Multiple choice");
        tq.getTable(0).rows.checkTextCellData(3, VGTable.TableFields.TYPE, "Slider ranking");
        tq.getTable(0).rows.checkTextCellData(4, VGTable.TableFields.TYPE, "Stars");
        tq.getTable(0).rows.checkTextCellData(5, VGTable.TableFields.TYPE, "Single Choice");
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("3. Creating a topic with 5 questions, the positive case")
    public void test3CreatingTopicWith5Questions(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testNameFor5Questions",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testNameFor5Questions");
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion4",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion4",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion5",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion5",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testNameFor5Questions");
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.ID, "T01 Q03");
        tq.getTable(0).rows.checkTextCellData(3, VGTable.TableFields.ID, "T01 Q04");
        tq.getTable(0).rows.checkTextCellData(4, VGTable.TableFields.ID, "T01 Q05");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion2");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "testBodyQuestion3");
        tq.getTable(0).rows.checkTextCellData(3, VGTable.TableFields.QUESTION, "testBodyQuestion4");
        tq.getTable(0).rows.checkTextCellData(4, VGTable.TableFields.QUESTION, "testBodyQuestion5");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(3, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(4, VGTable.TableFields.TYPE, "Text field");

    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("4. Creating a 5 topics with 1 question, the positive case")
    public void test4Creating5TopicsWith1Question(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1.1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1.1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName2",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2.1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2.1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName3",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3.1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3.1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName4",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(3).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion4.1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion4.1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName5",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(4).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion5.1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion5.1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.ID, "T02 Q01");
        tq.getTable(2).rows.checkTextCellData(0, VGTable.TableFields.ID, "T03 Q01");
        tq.getTable(3).rows.checkTextCellData(0, VGTable.TableFields.ID, "T04 Q01");
        tq.getTable(4).rows.checkTextCellData(0, VGTable.TableFields.ID, "T05 Q01");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1.1");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion2.1");
        tq.getTable(2).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion3.1");
        tq.getTable(3).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion4.1");
        tq.getTable(4).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion5.1");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(2).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(3).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(4).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");

    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("5. Creating a 3 topics with 3 questions, the positive case")
    public void test5Creating3TopicsWith3Question() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1.1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1.1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1.2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1.2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1.3", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1.3", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName2", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2.1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2.1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2.2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2.2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2.3", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2.3", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName3", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3.1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3.1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3.2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3.2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3.3", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3.3", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.ID, "T01 Q03");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.ID, "T02 Q01");
        tq.getTable(1).rows.checkTextCellData(1, VGTable.TableFields.ID, "T02 Q02");
        tq.getTable(1).rows.checkTextCellData(2, VGTable.TableFields.ID, "T02 Q03");
        tq.getTable(2).rows.checkTextCellData(0, VGTable.TableFields.ID, "T03 Q01");
        tq.getTable(2).rows.checkTextCellData(1, VGTable.TableFields.ID, "T03 Q02");
        tq.getTable(2).rows.checkTextCellData(2, VGTable.TableFields.ID, "T03 Q03");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1.1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion1.2");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "testBodyQuestion1.3");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion2.1");
        tq.getTable(1).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion2.2");
        tq.getTable(1).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "testBodyQuestion2.3");
        tq.getTable(2).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion3.1");
        tq.getTable(2).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion3.2");
        tq.getTable(2).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "testBodyQuestion3.3");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(1).rows.checkTextCellData(1, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(1).rows.checkTextCellData(2, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(2).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(2).rows.checkTextCellData(1, VGTable.TableFields.TYPE, "Text field");
        tq.getTable(2).rows.checkTextCellData(2, VGTable.TableFields.TYPE, "Text field");
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("6. Checking question type and body changes, the positive case")
    public void test6CheckingQuestionTypeAndBodyChanges() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        //Select question type
        questionModal.questionType.setClearInput("Yes No", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Yes/No");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Edit question
        questionModal =tq.getTable(0).rows.clickCellEditButton(0);
        // Enter a question name
        questionModal.questionTitle.setClearInput("newTestTitleQuestion1",false);
        //Select question type
        questionModal.questionType.setClearInput("Text Field", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("newTestBodyQuestion1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName");
        // Check the id in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        // Check the question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "newTestBodyQuestion1");
        // Check the question type
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("7. Adding and editing a topic, the positive case")
    public void test7AddingAndEditingTopic(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName");
        // Click edit topic
        editTopicModal = tq.editTopic.clickButton(EditTopicModal.class);
        // Editing the title of the topic
        editTopicModal.topicNameInput.setClearInput("newTestName",false);
        // Click save
        editTopicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("newTestName");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("8. Adding and removing a topic, the positive case")
    public void test8AddingAndRemovingTopic(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName123",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName123");
        // Click remove topic
        tq.getDeleteTopicBtn(0).clickButton();
        // Click "Confirm"
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), false);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), false);
    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("9. Adding and removing a topic with questions, the positive case")
    public void test9AddingAndRemovingTopicWithQuestions(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName123",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName123");
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Click remove topic
        tq.getDeleteTopicBtn(0).clickButton();
        // Click "Confirm"
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), false);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
    }

    /**
     * Тест падает так как при удалении первой темы страница выдает ошибку
     * [vuex] do not mutate vuex store state outside mutation handlers.
     */
    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("10. Adding and removing multiple topics, positive case")
    public void test10AddingAndRemovingMultipleTopic(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName2",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName3",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Click remove topic
        tq.getDeleteTopicBtn(0).clickButton();
        // Click "Confirm"
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), false);
        // Check the topic deletion

    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("11. Adding and removing multiple threads with questions, positive case")
    public void test11AddingAndRemovingMultipleThreadsWithQuestions(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName2",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName3",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1.1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1.1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1.2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1.2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2.1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2.1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(1).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2.2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2.2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3.1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3.1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(2).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3.2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3.2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Click remove topic (3)
        tq.getDeleteTopicBtn(2).clickButton();
        // Click "Confirm"
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), false);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the id in the table
        tq = studyEditor.studySections.topicsAndQuestions;
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(1).rows.checkTextCellData(0, VGTable.TableFields.ID, "T02 Q01");
        tq.getTable(1).rows.checkTextCellData(1, VGTable.TableFields.ID, "T02 Q02");
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("12. Adding and removing a question, the positive case")
    public void test12AddingAndRemovingQuestion() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Delete question
        tq.getTable(0).rows.clickCellDeleteButton(0);
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        // Check the deletion of a question
        proxy.checkExistence($x("//*[contains(text(), \"T01 Q01\")]"), false);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("13. Adding and removing multiple questions, positive case")
    public void test13AddingAndRemovingMultipleQuestions() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.ID, "T01 Q03");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion2");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "testBodyQuestion3");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Delete question
        tq.getTable(0).rows.clickCellDeleteButton(2);
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion2");
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("14. Adding, editing and deleting a question, positive case")
    public void test14AddingEditingAndDeletingQuestion() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Edit question
        questionModal =tq.getTable(0).rows.clickCellEditButton(0);
        // Enter a question name
        questionModal.questionTitle.setClearInput("newTestTitleQuestion1",false);
        //Select question type
        questionModal.questionType.setClearInput("Yes No", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("newTestBodyQuestion1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "newTestBodyQuestion1");
        // Delete question
        tq.getTable(0).rows.clickCellDeleteButton(0);
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        // Check the deletion of a question
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("15. Adding, editing and deleting multiple questions, positive case")
    public void test15AddingEditingAndDeletingMultipleQuestion() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion2", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion2", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion3", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion3", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.ID, "T01 Q03");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "testBodyQuestion2");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "testBodyQuestion3");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Edit question
        questionModal =tq.getTable(0).rows.clickCellEditButton(0);
        // Enter a question name
        questionModal.questionTitle.setClearInput("newTestTitleQuestion1",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("newTestBodyQuestion1",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Edit question
        questionModal =tq.getTable(0).rows.clickCellEditButton(1);
        // Enter a question name
        questionModal.questionTitle.setClearInput("newTestTitleQuestion2",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("newTestBodyQuestion2",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Edit question
        questionModal =tq.getTable(0).rows.clickCellEditButton(2);
        // Enter a question name
        questionModal.questionTitle.setClearInput("newTestTitleQuestion3",false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("newTestBodyQuestion3",false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.ID, "T01 Q03");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "newTestBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "newTestBodyQuestion2");
        tq.getTable(0).rows.checkTextCellData(2, VGTable.TableFields.QUESTION, "newTestBodyQuestion3");
         // Delete question
        tq.getTable(0).rows.clickCellDeleteButton(2);
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.ID, "T01 Q02");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "newTestBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(1, VGTable.TableFields.QUESTION, "newTestBodyQuestion2");
        // Delete question
        tq.getTable(0).rows.clickCellDeleteButton(1);
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[2]"));
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "newTestBodyQuestion1");
    }


    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("16. Canceling topic deletion, the positive case")
    public void test16CancelingTopicDeletion() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName123", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName123");
        // Click remove topic
        tq.getDeleteTopicBtn(0).clickButton();
        // Click "Cancel"
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[1]"));
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), true);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        proxy.checkExistence($x("//*[contains(text(), \"testName123\")]"), true);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("17. Cancelling the question deletion, the positive case")
    public void test17CancellingQuestionDeletion() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Delete question
        tq.getTable(0).rows.clickCellDeleteButton(0);
        // Cancel deletion
        proxy.clickElement($x("(//div[contains(@class,'vue-dialog-buttons')]/button)[1]"));
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
    }

    @Test
    @Category({DraftStudyTopicsAndQuestionsTest.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("18. Canceling topic editing, the positive case")
    public void test18CancelingTopicEditing(){
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName",false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("testName");
        // Click edit topic
        editTopicModal = tq.editTopic.clickButton(EditTopicModal.class);
        // Editing the title of the topic
        editTopicModal.topicNameInput.setClearInput("newTestName",false);
        // Click cancel
        editTopicModal.closeBtn.clickButton();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TestName");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check the title of the topic
        tq.topicName.checkPropertyHeader("TestName");
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("19. Cancelling the question editing, the positive case")
    public void test19CancellingQuestionEditing() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click save
        questionModal.saveQuestion.clickButton();
        // Check id and body question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.ID, "T01 Q01");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        // Edit question
        questionModal =tq.getTable(0).rows.clickCellEditButton(0);
        // Enter a question name
        questionModal.questionTitle.setClearInput("newTestTitleQuestion1",false);
        //Select question type
        questionModal.questionType.setClearInput("Yes No", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("newTestBodyQuestion1",false);
        // Click cancel
        questionModal.closeBtn.clickButton();
        // Check body and type question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "TestBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check body and type question in the table
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.QUESTION, "testBodyQuestion1");
        tq.getTable(0).rows.checkTextCellData(0, VGTable.TableFields.TYPE, "Text field");
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("20. Canceling the topic creation, the positive case")
    public void test20CancelingTopicCreation() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click cancel
        topicModal.closeBtn.clickButton();
        // Check topic
        proxy.checkExistence($x("//*[contains(text(), \"testName1\")]"), false);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check topic
        proxy.checkExistence($x("//*[contains(text(), \"testName1\")]"), false);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("21. Canceling the question creation, positive case")
    public void test21CancelingQuestionCreation() {
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Unwrap groups wrapper
        tq = studyEditor.studySections.topicsAndQuestions;
        // Add Topic
        topicModal = tq.addTopic.clickButton(CreateEditTopicModal.class);
        // Enter the topic title
        topicModal.topicNameInput.setClearInput("testName1", false);
        // Click save
        topicModal.saveTopicBtn.clickButton();
        // Add question
        questionModal = tq.getAddQuestionBtn(0).clickButton(CreateEditQuestionModal.class);
        // Enter a question name
        questionModal.questionTitle.setClearInput("testTitleQuestion1", false);
        // Enter the content of the question
        questionModal.questionBody.setClearMultilinedText("testBodyQuestion1", false);
        // Click cancel
        questionModal.closeBtn.clickButton();
        // Check question
        proxy.checkExistence($x("//*[contains(text(), \"testTitleQuestion1\")]"), false);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Click on Topics&Questions
        studyEditor.studySections.topicsAndQuestions.headerText.clickPropertyHeader();
        // Check topic
        proxy.checkExistence($x("//*[contains(text(), \"testTitleQuestion1\")]"), false);
    }

    @After
    public void logout() throws Exception {
        // Delete study
        HTTPApiHelper APIHelper = new HTTPApiHelper();
        String id = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        APIHelper.deleteStudyById(id);
        // Logout
        controlPanel = controlPanel.crumbsLine.clickHome();
        controlPanel.userLogout();
        closeWebDriver();
    }

    @AfterClass
    public static void tearDown() {
        closeWebDriver();
    }
}

