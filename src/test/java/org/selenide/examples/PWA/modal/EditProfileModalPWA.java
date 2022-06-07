package org.selenide.examples.PWA.modal;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.elements.CheckBoxPWA;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditProfileModalPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='saveBtn' ]")
    public Button<ControlPanelPagePWA> SaveBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal']")
    public Button<ControlPanelPagePWA> closeBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userName' ]")
    public TextProperty userName;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userCreated' ]")
    public TextProperty userCreated;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userEmail' ]")
    public TextProperty userEmail;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userPhone' ]")
    public TextProperty userPhone;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[1]//div[2]")
    public SelectInput userPhoneCod;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[1]//div[3]")
    public TextProperty userPhoneCodErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[2]//div/div")
    public TextInput userPhoneNumber;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='phoneFirstVisit']//div/span[2]//div[2]/div")
    public TextProperty userPhoneNumberErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userPhoneInput']/div[2]")
    public TextProperty phoneErrorMessage;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='genderFirstVisit']/div[2]")
    public TextInput userGenderInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='genderFirstVisit']/div[2]")
    public SelenideElement userGender;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='yearsFirstVisit']//div[2]")
    public TextInput userYearsInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='yearsFirstVisit']//div[2]")
    public SelenideElement userYear;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userLanguageInput']/div[2]")
    public TextInput userLanguageInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userLanguageInput']/div[2]")
    public SelenideElement languageSelector;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='locationFirstVisit']//div")
    public TextInput userLocationInput;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='selectInterestsCheckbox']")
    public List<CheckBoxPWA> InterestsCheckboxList;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='userImage']")
    public SelenideElement userImage;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    private SelenideElement cookiesAccept;

    public void acceptCookies(){
        if (cookiesAccept.isDisplayed()){
            proxy.clickElementIfExist(cookiesAccept);
        }
    }

    public void clickCheckBox(int num)
    {
        InterestsCheckboxList.get(num).clickCheckBox();
    }

    public void checkCheckBox(int num)
    {
        InterestsCheckboxList.get(num).checkCheckBox();
    }

    public void checkNowDate()
    {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("MM/dd/yyyy");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        userCreated.checkPropertyHeader("Created: "+retStrFormatNowDate);
    }
}
