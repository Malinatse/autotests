package org.selenide.examples.PWA.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FirstVisitPagePWA extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='submitBtn']")
    public Button submitBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='firstNameFirstVisit']/div[1]")
    public TextInput firstNameFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='firstNameFirstVisit']/div[2]")
    public TextProperty firstNameFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='lastNameFirstVisit']/div[1]")
    public TextInput lastNameFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='lastNameFirstVisit']/div[2]")
    public TextProperty lastNameFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[1]//div[2]")
    public SelectInput phoneFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[1]//div[3]")
    public TextProperty phoneFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[2]")
    public TextInput phoneNumberFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[2]//div[2]/div")
    public TextProperty phoneNumberFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='genderFirstVisit']/div[2]")
    public SelectInput genderFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='genderFirstVisit']/div[2]")
    public SelenideElement genderFirstVisitSelect;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='genderFirstVisit']/div[3]")
    public TextProperty genderFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userLanguageInput']/div[2]")
    public SelectInput languageFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userLanguageInput']/div[2]")
    public SelenideElement languageFirstVisitSelector;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='yearsFirstVisit']//div[2]")
    public SelectInput yearsFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='yearsFirstVisit']//div[2]")
    public SelenideElement yearFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='yearsFirstVisit']//div[3]")
    public TextProperty yearsFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='locationFirstVisit']//div")
    public TextInput locationFirstVisit;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='locationFirstVisit']//div")
    public SelenideElement locationFirstVisitSE;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='locationFirstVisit']/div[1]//span/div/div[2]")
    public TextProperty locationFirstVisitErrorMessage;

    @FindBy(how = How.XPATH, xpath = "//div[7]/div/div/div[2]/div")
    public SelenideElement locationFirstVisitCont;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='siteLogo']")
    public Button<ControlPanelPagePWA> logoBtn;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    private SelenideElement cookiesAccept;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void acceptCookies(){
        if (cookiesAccept.isDisplayed()){
            proxy.clickElementIfExist(cookiesAccept);
        }
    }

    public void clickSubmitBtn(){
        $x(".//button[@data-e2e='submitBtn']").shouldBe(Condition.enabled, Duration.ofSeconds(5));
        submitBtn.clickButton();
    }

    public void checkErrorMassageFirstVisit(){
        String em="This field is required";
        firstNameFirstVisitErrorMessage.checkPropertyHeader(em);
        lastNameFirstVisitErrorMessage.checkPropertyHeader(em);
        phoneFirstVisitErrorMessage.checkPropertyHeader(em);
        phoneNumberFirstVisitErrorMessage.checkPropertyHeader(em);
        genderFirstVisitErrorMessage.checkPropertyHeader(em);
        yearsFirstVisitErrorMessage.checkPropertyHeader(em);
        locationFirstVisitErrorMessage.checkPropertyHeader(em);
    }

    public void enterDataForRegistration(){
        $x(".//button[@data-e2e='submitBtn']").shouldBe(Condition.visible, Duration.ofSeconds(120));
        firstNameFirstVisit.setClearInput("Test",false);
        lastNameFirstVisit.setClearInput("Name",false);
        phoneFirstVisit.setClearInput("1",true);
        phoneNumberFirstVisit.setClearInput("7777777777",true);
        genderFirstVisitSelect.click();
        proxy.clickElement($x("//div[contains(text(), 'Male')]"));
        yearFirstVisit.click();
        proxy.clickElement($x("//div[contains(text(), '2000')]"));
        languageFirstVisitSelector.click();
        proxy.clickElement($x("//div/div[3][contains(text(), 'English')]"));
        locationFirstVisit.setInput("Russia",false);
        $x("//div[contains(@class,'LLPopper__tooltip')]").shouldBe(Condition.visible, Duration.ofSeconds(30));
        $(".LLPopper__tooltip").$x(".//*[contains(text(), 'Russia')]").click();
    }

}
