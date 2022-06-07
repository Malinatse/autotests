package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.containers.ExpandablePanel;
import org.selenide.examples.containers.cards.DraftStudyContainer;
import org.selenide.examples.containers.cards.LiveStudyContainer;
import org.selenide.examples.containers.studyeditor.Crumbs;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.StudyCard;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Объект для взаимодействия с главной страницей
 */
public class ControlPanelPage extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='draftStudies']")
    public DraftStudyContainer studiesDraftContainer;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='studyCard']")
    public List<StudyCard> liveStudyCards;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='crumbLink']")
    public Crumbs crumbsLine;

    @FindBy(how = How.XPATH, xpath = "//article[@data-e2e='draftStudies']")
    private DraftStudyContainer draftStudyCont;

    @FindBy(how = How.XPATH, xpath = "//div[@class='dropdown-nav relative ml-2']/button/span/span")
    private SelenideElement userDropDown;

    @FindBy(how = How.XPATH, xpath = "//div[@class='dropdown-nav relative ml-8 mob-only:ml-2']/button")
    public Button profileBtn;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='headerLogoutBtn']")
    public Button logoutBtn;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='headerProfileLnk']")
    public Button openProfileBtn;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='headerUserManagement']")
    private SelenideElement userManagementButton;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='headerTeams']")
    private SelenideElement teamsButton;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='liveTab']")
    private SelenideElement studiesLiveTab;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='draftTab']")
    private SelenideElement studiesDraftTab;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='completedTab']")
    private SelenideElement studiesCompletedTab;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='archivedTab']")
    private SelenideElement studiesArchivedTab;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='dashboardStudies']")
    public ExpandablePanel studiesExpandablePanel;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='dashboardCreateStudy']")
    public Button createStudyBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='dashboardStudiesCreateStudyStandard']")
    public Button<StudyEditor> standardStudyBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='languageSelector']/button")
    public SelenideElement languageSelector;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    private SelenideElement cookiesAccept;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void acceptCookies() {
        if (cookiesAccept.isDisplayed()){
        proxy.clickElementIfExist(cookiesAccept);
        }
    }

    public void selectEng() {
        proxy.clickElement(languageSelector);
        proxy.clickElement($x("//button[contains(text(), 'English')]"));
    }

    /**
     * Возвращает имя пользователя, отображаемое в navbar
     *
     * @return String userName
     */
    public String getUserName() {
        return proxy.getText(userDropDown);
    }

    public void checkUserName(String userName) {
        proxy.checkText(userDropDown, userName);
    }

    /**
     * Производит выход из Looklook
     */
    public void userLogout() {
        // Ждём, пока пропадут попапы, если такие есть
        ElementsCollection popUps = $$x("//div[contains(@class, 'toasted toasted-primary success')]");
        for (SelenideElement a : popUps) {
            a.shouldNot(Condition.exist, Duration.ofSeconds(60));
        }
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dropdown-nav relative ml-8 mob-only:ml-2']/button")));
        profileBtn.clickButton();
        logoutBtn.clickButton();
    }

    /**
     * Переход на страницу профиля
     */
    public UserProfilePage clickUserProfile() {
        profileBtn.clickButton();
        logoutBtn.clickButton();
        return page(UserProfilePage.class);
    }

    /**
     * Переход на страницу User Management
     */
    public UserManagementPage clickUserManagement() {
        proxy.clickElement(userManagementButton);
        return page(UserManagementPage.class);
    }

    /**
     * Переход на страницу Teams
     */
    public TeamsPage clickTeams() {
        proxy.clickElement(teamsButton);
        return page(TeamsPage.class);
    }


    public ControlPanelPage clickStudiesDraftTab() {
        proxy.clickElement(studiesDraftTab);
        return page(ControlPanelPage.class);
    }

    public ControlPanelPage clickStudiesLiveTab() {
        proxy.clickElement(studiesLiveTab);
        return page(ControlPanelPage.class);
    }

    public StudyEditor goToEditLiveStudy(int id) {
        liveStudyCards.get(id).cardHeader.clickPropertyHeader();
        return page(StudyEditor.class);
    }
}
