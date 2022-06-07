package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.moderate.ModeratorFilterContainer;
import org.selenide.examples.containers.moderate.NotebookContainer;
import org.selenide.examples.containers.moderate.ParticipantsContainer;
import org.selenide.examples.containers.studyeditor.Crumbs;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ModeratorPage extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='crumbLink']")
    public Crumbs crumbsLine;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='studyNameMod']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userNameMod']")
    public TextProperty adminName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='studyInfoMod']/div[2]/div/span")
    public TextProperty studyDate;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='studyInfoMod']/div[2]/div[2]/span")
    public TextProperty studyNumberOfPart;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='studyInfoMod']/div[2]/div[3]/span")
    public TextProperty studyNumberOfGroup;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='contactParticipantsModBtn']")
    public Button contactParticipantsBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='translateSwitchModBtn']")
    public Button translateSwitch;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='moderateFilter']")
    public ModeratorFilterContainer filter;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='moderateTabs']/div/nav/a[1]")
    public Button<ParticipantsContainer> partBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='moderateTabs']/div/nav/a[2]")
    public Button questBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='notebookBtn']")
    public Button<NotebookContainer> notebookBtn;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='moderateTabs']")
    public ParticipantsContainer partView;

    @FindBy(how = How.XPATH, xpath = "//div[@class='dropdown-nav relative ml-2']/button/span/span")
    private SelenideElement userDropDown;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='tagInfo']/div")
    public TextProperty tagInfo;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='addTagInput']")
    public TextInput addTagInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='addTagBtn']")
    public Button addTagBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='highlighter']/p//div[3]")
    public Button deleteTextTag;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='languageSelector']/button")
    public SelenideElement languageSelector;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    private SelenideElement cookiesAccept;


    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void acceptCookies(){
        proxy.clickElementIfExist(cookiesAccept);
    }

    public void selectEng()
    {
        proxy.clickElement(languageSelector);
        proxy.clickElement($x("//button[contains(text(), 'English')]"));
    }
}
