package org.selenide.examples.PWA.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenide.examples.PWA.container.StudyCardInvitedPWA;
import org.selenide.examples.PWA.container.StudyCardLivePWA;
import org.selenide.examples.PWA.container.StudyCardPendingPWA;
import org.selenide.examples.PWA.container.StudyCardWaitingPWA;
import org.selenide.examples.PWA.elements.UserProfileDashboardPWA;
import org.selenide.examples.PWA.modal.EditProfileModalPWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ControlPanelPagePWA extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e=\"liveStudyBody\"]/ancestor::div[@data-e2e=\"studyCard\"]")
    public List<StudyCardLivePWA> liveStudyCardsPWA;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e=\"waitingStudyBody\"]/ancestor::div[@data-e2e=\"studyCard\"]")
    public List<StudyCardWaitingPWA> waitingStudyCardsPWA;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e=\"pendingStudyBody\"]/ancestor::div[@data-e2e=\"studyCard\"]")
    public List<StudyCardPendingPWA> pendingStudyCardsPWA;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e=\"invitedStudyBody\"]/ancestor::div[@data-e2e=\"studyCard\"]")
    public List<StudyCardInvitedPWA> invitedStudyCardsPWA;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='UserProfileDropdown']/button")
    public Button profileBtn;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='headerLogoutBtn']")
    public Button logoutBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEditModalBtn']")
    public Button userEditModalBtn;

    @FindBy(how = How.XPATH, xpath = "//a[@data-e2e='headerProfileLnk']")
    public Button openProfileBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='switchLanguage']")
    public SelenideElement languageSelector;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    private SelenideElement cookiesAccept;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userProfileDashboard']")
    public UserProfileDashboardPWA userProfileDashboard;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='privacyLink']")
    public Button privacyLink;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='termsLink']")
    public Button termsLink;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='siteLogo']")
    public Button<ControlPanelPagePWA> logoBtn;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void acceptCookies(){
        if (cookiesAccept.isDisplayed()){
            proxy.clickElementIfExist(cookiesAccept);
        }
    }

    public void selectEng()
    {
        proxy.clickElement(languageSelector);
        proxy.clickElement($x("//button[contains(text(), 'English')]"));
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
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-e2e='UserProfileDropdown']/button")));
        profileBtn.clickButton();
        logoutBtn.clickButton();
    }
    public EditProfileModalPWA userEdit() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-e2e='UserProfileDropdown']/button")));
        profileBtn.clickButton();
        userEditModalBtn.clickButton(EditProfileModalPWA.class);
        return page(EditProfileModalPWA.class);
    }

    public LiveStudyPagePWA goToLiveStudyId(Integer id){
        return liveStudyCardsPWA.get(id).goToLiveStudy();
    }

    public void thisIsLiveStudy(Integer id) { liveStudyCardsPWA.get(id).thisIsLiveStudyCard();}

    public void thisIsWaitingStudy(Integer id) { waitingStudyCardsPWA.get(id).thisIsWaitingStudyCard();}

    public void thisIsPendingStudy(Integer id) { pendingStudyCardsPWA.get(id).thisIsPendingStudyCard();}

    public void thisIsInvitedStudy(Integer id) { invitedStudyCardsPWA.get(id).thisIsInvitedStudyCard();}

    public void checkPrivateAndTerms(){
        privacyLink.clickButton();
        $x("//*[contains(text(), \"LookLook® Privacy Policy\")]").shouldBe(Condition.visible);
        logoBtn.clickButton(ControlPanelPagePWA.class);
        termsLink.clickButton();
        $x("//*[contains(text(), \"LookLook® Terms of Service\")]").shouldBe(Condition.visible);
        logoBtn.clickButton(ControlPanelPagePWA.class);
    }

    public void addAvatar(){
        File file = new File("src/test/java/../resources/image.png");
        $x(".//*[@data-e2e=\"addAvatar\"]//input").uploadFile(file);
        $x(".//img[contains(@class, 'vue-preview__image')]").shouldBe(Condition.visible, Duration.ofSeconds(120));
        proxy.clickElement($x("//div[contains(text(), \"Confirm\")]"));
        proxy.checkExistence($x("(//button[contains(@class,'LLAvatarCropper__action')])"), false);

    }
}
