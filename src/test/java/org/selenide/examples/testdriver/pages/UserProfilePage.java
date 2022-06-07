package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class UserProfilePage extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userSince']")
    public TextProperty userSince;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEdit']")
    public Button userEditBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userFirstName']")
    public TextProperty userFirstName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userFirstNamePencil']")
    public Button userFirstNamePencil;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userFirstNamePlus']")
    public Button userFirstNamePlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userFirstNameEdit']/div/input")
    public TextInput userFirstNameEdit;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLastName']")
    public TextProperty userLastName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLastNamePencil']")
    public Button userLastNamePencil;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLastNamePlus']")
    public Button userLastNamePlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLastNameEdit']/div/input")
    public TextInput userLastNameEdit;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userMail']")
    public TextProperty userMail;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhone']")
    public TextProperty userPhone;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhonePencil']")
    public Button userPhonePencil;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhonePlus']")
    public Button userPhonePlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhoneEdit']/div/input")
    public TextInput userPhoneEdit;

}
