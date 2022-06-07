package org.selenide.examples.tests.draftstudy;

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
import org.selenide.examples.containers.studyeditor.SectionGroupsParticipants;
import org.selenide.examples.containers.studyeditor.StudyDetails;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.DraftStudy;
import org.selenide.examples.testdriver.groupinterfaces.DraftStudyGroupTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Draft study group tests")
public class DraftStudyGroupTest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPage controlPanel;
    private static StudyEditor studyEditor;
    public static StudyDetails sd;
    private SectionGroupsParticipants gp;
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
        // Refresh the data of the study details
        sd = studyEditor.studyDetails;
              // Accept cookies
        controlPanel.acceptCookies();  studyEditor.createStudy("TestStudy");

    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("1. Default group field's values check, the positive case")
    public void test1UnassignedGroupDefaultFields(){
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Check group name
        gp.sectionCreateGroups.checkGroupName(0, "Unassigned");
        // Check Conversation Details for Participants
        gp.sectionCreateGroups.checkDetailParticipants(0, "");
        // Check Unassigned Description
        gp.sectionCreateGroups.checkGroupDescription("", 0);
        // Click Gender preference
        gp.sectionCreateGroups.clickGenderPreference(0);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("", 0);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("", 0);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("", 0);
        // Click "More fields"
        gp.sectionCreateGroups.clickMoreFields(0);
        // Check Geography
        gp.sectionCreateGroups.checkGeography("", 0);
        // Check Presence of children in the home
        gp.sectionCreateGroups.checkChildrenAtHome("", 0);
        // Check Comments for recruiter
        gp.sectionCreateGroups.checkCommentsForRecruiter("", 0);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("2. Default group changes enabled check, the positive case")
    public void test2UnassignedGroupFieldsChanges(){
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Check group name
        gp.sectionCreateGroups.setClearGroupName("Changed", 0,false);
        // Check Conversation Details for Participants
        gp.sectionCreateGroups.setClearDetailParticipants("Changed", 0, false);
        // Check Unassigned Description
        gp.sectionCreateGroups.setClearGroupDescription("Changed", 0, false);
        // Check Number of participants
        gp.sectionCreateGroups.setClearNumberOfParticipants("1", 0, false);
        // Click "More fields"
        gp.sectionCreateGroups.clickMoreFields(0);
        // Check Geography
        gp.sectionCreateGroups.setClearGeography("Changed", 0, false);
        // Check Presence of children in the home
        gp.sectionCreateGroups.setClearChildrenAtHome("Changed", 0, false);
        // Check Comments for recruiter
        gp.sectionCreateGroups.setClearCommentsForRecruiter("Changed", 0, false);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(0);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Check group name
        gp.sectionCreateGroups.checkGroupName(0, "Changed");
        // Check Conversation Details for Participants
        gp.sectionCreateGroups.checkDetailParticipants(0, "Changed");
        // Check Unassigned Description
        gp.sectionCreateGroups.checkGroupDescription("Changed", 0);
        // Check Number of participants
        gp.sectionCreateGroups.checkNumberOfParticipants("1", 0);
        // Click "More fields"
        gp.sectionCreateGroups.clickMoreFields(0);
        // Check Geography
        gp.sectionCreateGroups.checkGeography("Changed", 0);
        // Check Presence of children in the home
        gp.sectionCreateGroups.checkChildrenAtHome("Changed", 0);
        // Check Comments for recruiter
        gp.sectionCreateGroups.checkCommentsForRecruiter("Changed", 0);
    }

    //падает при попытке поменять количество людей конкретного гендера
    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("3. Default group gender changes, the positive case")
    public void test3UnassignedGroupGenderChanges(){
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Click Gender preference
        gp.sectionCreateGroups.clickGenderPreference(0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", 0);
        // Set male amount
        gp.sectionCreateGroups.setClearMaleCount("1", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
        // Set female amount
        gp.sectionCreateGroups.setClearFemaleCount("1", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("2", 0);
        // Set non binary amount
        gp.sectionCreateGroups.setClearNonBinaryCount("1", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("3", 0);
        // Set non binary amount
        gp.sectionCreateGroups.setClearNonBinaryCount("0", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("2", 0);
        // Set female amount
        gp.sectionCreateGroups.setClearFemaleCount("0", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
        // Set male amount
        gp.sectionCreateGroups.setClearMaleCount("0", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", 0);
    }

    //падает при попытке поменять количество людей конкретного гендера
    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("4. Default group gender male changes, the positive case")
    public void test4UnassignedGroupGenderChangesOnlyMale(){
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Click Gender preference
        gp.sectionCreateGroups.clickGenderPreference(0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", 0);
        // Set male amount
        gp.sectionCreateGroups.setClearMaleCount("1", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(0);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("1", 0);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("", 0);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("", 0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
    }

    //падает при попытке поменять количество людей конкретного гендера
    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("5. Default group gender female changes, the positive case")
    public void test5UnassignedGroupGenderChangesOnlyFemale(){
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Click Gender preference
        gp.sectionCreateGroups.clickGenderPreference(0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", 0);
        // Set male amount
        gp.sectionCreateGroups.setClearFemaleCount("1", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(0);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("", 0);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("1", 0);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("", 0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
    }

    //падает при попытке поменять количество людей конкретного гендера
    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("6. Default group gender female changes, the positive case")
    public void test6UnassignedGroupGenderChangesOnlyNonBinary(){
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Click Gender preference
        gp.sectionCreateGroups.clickGenderPreference(0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", 0);
        // Set male amount
        gp.sectionCreateGroups.setClearNonBinaryCount("1", 0, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(0);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(0);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("", 0);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("", 0);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("1", 0);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", 0);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("7. New group default field's values check, the positive case")
    public void test7NewGroupDefaultFields(){
        int groupId = 1;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Add new group
        gp.sectionCreateGroups.addGroupBtn.clickButton();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Check group name
        gp.sectionCreateGroups.checkGroupName(groupId, "Group 2");
        // Check Conversation Details for Participants
        gp.sectionCreateGroups.checkDetailParticipants(groupId, "");
        // Check Unassigned Description
        gp.sectionCreateGroups.checkGroupDescription("", groupId);
        // Check Number of participants
        gp.sectionCreateGroups.checkNumberOfParticipants("1", groupId);
        // Click Gender preference
        gp.sectionCreateGroups.clickGenderPreference(groupId);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("0", groupId);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("0", groupId);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("0", groupId);
        // Click "More fields"
        gp.sectionCreateGroups.clickMoreFields(groupId);
        // Check Geography
        gp.sectionCreateGroups.checkGeography("", groupId);
        // Check Presence of children in the home
        gp.sectionCreateGroups.checkChildrenAtHome("", groupId);
        // Check Comments for recruiter
        gp.sectionCreateGroups.checkCommentsForRecruiter("", groupId);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("8. New group changes enabled check, the positive case")
    public void test8NewGroupFieldsChanges(){
        int groupId = 1;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Add new group
        gp.sectionCreateGroups.addGroupBtn.clickButton();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Check group name
        gp.sectionCreateGroups.setClearGroupName("Changed", groupId,false);
        // Check Conversation Details for Participants
        gp.sectionCreateGroups.setClearDetailParticipants("Changed", groupId, false);
        // Check Unassigned Description
        gp.sectionCreateGroups.setClearGroupDescription("Changed", groupId, false);
        // Check Number of participants
        gp.sectionCreateGroups.setClearNumberOfParticipants("1", groupId, false);
        // Click "More fields"
        gp.sectionCreateGroups.clickMoreFields(groupId);
        // Check Geography
        gp.sectionCreateGroups.setClearGeography("Changed", groupId, false);
        // Check Presence of children in the home
        gp.sectionCreateGroups.setClearChildrenAtHome("Changed", groupId, false);
        // Check Comments for recruiter
        gp.sectionCreateGroups.setClearCommentsForRecruiter("Changed", groupId, false);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(groupId);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Check group name
        gp.sectionCreateGroups.checkGroupName(groupId, "Changed");
        // Check Conversation Details for Participants
        gp.sectionCreateGroups.checkDetailParticipants(groupId, "Changed");
        // Check Unassigned Description
        gp.sectionCreateGroups.checkGroupDescription("Changed", groupId);
        // Check Number of participants
        gp.sectionCreateGroups.checkNumberOfParticipants("1", groupId);
        // Click "More fields"
        gp.sectionCreateGroups.clickMoreFields(groupId);
        // Check Geography
        gp.sectionCreateGroups.checkGeography("Changed", groupId);
        // Check Presence of children in the home
        gp.sectionCreateGroups.checkChildrenAtHome("Changed", groupId);
        // Check Comments for recruiter
        gp.sectionCreateGroups.checkCommentsForRecruiter("Changed", groupId);
    }

    //падает при попытке поменять количество людей конкретного гендера
    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("9. New group gender changes, the positive case")
    public void test9NewGroupGenderChanges(){
        int groupId = 1;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Add new group
        gp.sectionCreateGroups.addGroupBtn.clickButton();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Click Gender preference
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.checkBoxHeader("Gender preference");
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.clickCheckBox();
      //  gp.sectionCreateGroups.clickGenderPreference(groupId);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", groupId);
        // Set male amount
        gp.sectionCreateGroups.setClearMaleCount("1", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
        // Set female amount
        gp.sectionCreateGroups.setClearFemaleCount("1", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("2", groupId);
        // Set non binary amount
        gp.sectionCreateGroups.setClearNonBinaryCount("1", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("3", groupId);
        // Set non binary amount
        gp.sectionCreateGroups.setClearNonBinaryCount("0", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("2", groupId);
        // Set female amount
        gp.sectionCreateGroups.setClearFemaleCount("0", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
        // Set male amount
        gp.sectionCreateGroups.setClearMaleCount("0", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", groupId);
    }

    //падает при попытке поменять количество людей конкретного гендера
    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("10. New group gender male changes, the positive case")
    public void test10NewGroupGenderChangesOnlyMale(){
        int groupId = 1;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Add new group
        gp.sectionCreateGroups.addGroupBtn.clickButton();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Click Gender preference
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.checkBoxHeader("Gender preference");
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.clickCheckBox();
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", groupId);
        // Set male amount
        gp.sectionCreateGroups.setClearMaleCount("1", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(groupId);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("1", groupId);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("0", groupId);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("0", groupId);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("11. New group gender female changes , the positive case")
    public void test11NewGroupGenderChangesOnlyFemale(){
        int groupId = 1;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Add new group
        gp.sectionCreateGroups.addGroupBtn.clickButton();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Click Gender preference
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.checkBoxHeader("Gender preference");
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.clickCheckBox();
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", groupId);
        // Set male amount
        gp.sectionCreateGroups.setClearFemaleCount("1", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(groupId);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("0", groupId);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("1", groupId);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("0", groupId);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
    }

    @Test
    @Category({DraftStudyGroupTests.class, PositiveTests.class, DraftStudy.class})
    @DisplayName("12. New group gender non binary changes, the positive case")
    public void test12NewGroupGenderChangesOnlyNonBinary(){
        int groupId = 1;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Add new group
        gp.sectionCreateGroups.addGroupBtn.clickButton();
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Click Gender preference
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.checkBoxHeader("Gender preference");
        gp.sectionCreateGroups.groupEditors.get(1).genderCheckBox.clickCheckBox();
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("0", groupId);
        // Set male amount
        gp.sectionCreateGroups.setClearNonBinaryCount("1", groupId, false);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
        // Save changes
        gp.sectionCreateGroups.clickSaveGroup(groupId);
        // Goto main page
        controlPanel = controlPanel.crumbsLine.clickHome();
        // Goto draft studies
        controlPanel.clickStudiesDraftTab();
        // Edit changed study
        studyEditor = controlPanel.studiesExpandablePanel.studiesDraftContainer.clickCardEdit(0);
        // Renew data
        sd = studyEditor.studyDetails;
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsParticipants;
        // Unwrap main wrapper
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        gp = studyEditor.studySections.groupsParticipantsWrapper.sectionGroupsShowHideBtn.clickButton(SectionGroupsParticipants.class);
        // Unwrap groups wrapper
        gp.sectionCreateGroups.groupHeader.clickPropertyHeader();
        $x("//*[contains(text(), \"Unassigned\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        // Unwrap group
        gp.sectionCreateGroups.clickGroupHeader(groupId);
        // Check male amount
        gp.sectionCreateGroups.checkMaleCount("0", groupId);
        // Check female amount
        gp.sectionCreateGroups.checkFemaleCount("0", groupId);
        // Check non binary amount
        gp.sectionCreateGroups.checkNonBinaryCount("1", groupId);
        // Check total amount
        gp.sectionCreateGroups.checkNumberOfParticipantsByGender("1", groupId);
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
