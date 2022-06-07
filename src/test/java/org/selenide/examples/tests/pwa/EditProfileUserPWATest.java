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
import org.selenide.examples.PWA.modal.EditProfileModalPWA;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.PWA.pages.FirstVisitPagePWA;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.groupinterfaces.NegativeTests;
import org.selenide.examples.testdriver.groupinterfaces.PositiveTests;
import org.selenide.examples.testdriver.pages.ControlPanelPage;
import org.selenide.examples.testdriver.pages.LoginPageNew;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

@DisplayName("PWA Profile User tests")
public class EditProfileUserPWATest {
    @Rule
    public TextReport report = new TextReport();

    public static LoginPageNew loginPage;
    public static ControlPanelPagePWA controlPanelPWA;
    public static EditProfileModalPWA editProfileModal;
    public static ControlPanelPage controlPanel;
    public static StudyEditor studyEditor;
    public SectionGroupsParticipants gp;
    public static FirstVisitPagePWA firstVisitPage;
    public String invLnk;
    String email;
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
        $x("//*[contains(text(), \"Approved\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        studyId = APIHelper.getStudyIdFromUrl(WebDriverRunner.url());
        invLnk = APIHelper.getStudyInviteById(studyId);
        controlPanel.userLogout();
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
    }

    @Test
    @Category({EditProfileUserPWATest.class, PositiveTests.class})
    @DisplayName("1.Profile Editing, the positive case")
    public void test1ProfileEditing(){
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
        controlPanelPWA.userProfileDashboard.userEmailDashboard.checkPropertyHeader("Email: "+email);
        controlPanelPWA.userProfileDashboard.userPhoneDashboard.checkPropertyHeader("Phone: +17777777777");
        // Open edit user modal
        editProfileModal = controlPanelPWA.userEdit();
        // Check the previously entered values
        editProfileModal.userName.checkPropertyHeader("Test Name");
        editProfileModal.checkNowDate();
        editProfileModal.userEmail.checkPropertyHeader("Email: "+email);
        editProfileModal.userPhone.checkPropertyHeader("Phone: +17777777777");
        // Enter new data
        editProfileModal.userPhoneNumber.setClearInput("8888888888",false);
        editProfileModal.userPhoneCod.setClearInput("+7",true);
        editProfileModal.userGender.click();
        proxy.clickElement($x("//div[contains(text(), 'Female')]"));
        editProfileModal.userYear.click();
        proxy.clickElement($x("//div[contains(text(), '2001')]"));
        editProfileModal.languageSelector.click();
        proxy.clickElement($x("//div/div[3][contains(text(), 'English')]"));
        editProfileModal.userLocationInput.setClearInput("Moscow",false);
        $x("//div[contains(@class,'LLPopper__tooltip')]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        $(".LLPopper__tooltip").$x(".//*[contains(text(), 'Moscow')]").click();
        // Click on all the checkboxes
        for (int i = 0; i < 22; i++){
             editProfileModal.clickCheckBox(i);
        }
        controlPanelPWA = editProfileModal.SaveBtn.clickButton(ControlPanelPagePWA.class);
        editProfileModal = controlPanelPWA.userEdit();
        // Checking the new data
        editProfileModal.userName.checkPropertyHeader("Test Name");
        editProfileModal.checkNowDate();
        $x(".//*[@data-e2e='phoneFirstVisit']//div/span[1]//div[2]//div[contains(text(), \"+7\")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
        editProfileModal.userPhoneNumber.checkInputValue("8888888888");
        editProfileModal.userGender.shouldHave(Condition.text("Female"));
        editProfileModal.userYear.shouldHave(Condition.text("2001"));
        editProfileModal.languageSelector.shouldHave(Condition.text("English"));
        editProfileModal.userLocationInput.checkInputValue("Moscow, Russia");
        for (int i = 0; i < 22; i++){
            editProfileModal.checkCheckBox(i);
        }
        controlPanelPWA = editProfileModal.closeBtn.clickButton(ControlPanelPagePWA.class);
    }

    @Test
    @Category({EditProfileUserPWATest.class, NegativeTests.class})
    @DisplayName("2.Profile Editing with blank inputs, the negative case")
    public void test2ProfileEditingWithBlankInput(){
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
        controlPanelPWA.userProfileDashboard.userEmailDashboard.checkPropertyHeader("Email: "+email);
        controlPanelPWA.userProfileDashboard.userPhoneDashboard.checkPropertyHeader("Phone: +17777777777");
        // Open edit user modal
        editProfileModal = controlPanelPWA.userEdit();
        // Check the previously entered values
        editProfileModal.userName.checkPropertyHeader("Test Name");
        editProfileModal.userEmail.checkPropertyHeader("Email: "+email);
        editProfileModal.userPhone.checkPropertyHeader("Phone: +17777777777");
        // Leave blank fields
        editProfileModal.userPhoneNumber.setClearInput(" ",false);
        // Checking the message about the empty field
        editProfileModal.userPhoneNumberErrorMessage.checkPropertyHeader("This field is required");
        editProfileModal.userPhoneCodErrorMessage.checkPropertyHeader("This field is required");
        controlPanelPWA = editProfileModal.closeBtn.clickButton(ControlPanelPagePWA.class);
    }

    @Test
    @Category({EditProfileUserPWATest.class, PositiveTests.class})
    @DisplayName("3.Add avatar, the positive case")
    public void test3AddAvatar(){
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
        // Add image
        controlPanelPWA.addAvatar();
        $x("//*[contains(text(), \" Welcome back, Test! \")]").shouldBe(Condition.visible, Duration.ofSeconds(3));
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
