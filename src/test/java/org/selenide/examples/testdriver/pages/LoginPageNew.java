package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.PWA.pages.FirstVisitPagePWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPageNew extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='emailInput']")
    public TextInput loginField;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='passInput']")
    public TextInput passInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='buttonInput']")
    public Button loginBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='passBtn']")
    public Button<ControlPanelPage> loginBtn2;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='passBtn']")
    public Button<ControlPanelPagePWA> loginBtn2PWA;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='passBtn']")
    public Button<FirstVisitPagePWA> loginBtn2PWAFirstVisit;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='loginValidError']")
    public SelenideElement loginValidError;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='registerPasswordInput']")
    public TextInput registerPassInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='registerPasswordBtn']")
    public Button<FirstVisitPagePWA> registerLoginBtn2;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='cookiesBtn']")
    public SelenideElement cookiesAccept;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='resetPasswordInput1']")
    public TextInput resetPasswordInput1;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='resetPasswordInput2']")
    public TextInput resetPasswordInput2;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='resetPasswordBtn']")
    public Button<ControlPanelPagePWA> resetPasswordBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='languageSelector']/button")
    public SelenideElement languageSelector;

    public void selectEng()
    {
        proxy.clickElement(languageSelector);
        proxy.clickElement($x("//button[contains(text(), 'English')]"));
    }

    public void acceptCookies(){
        if (cookiesAccept.isDisplayed()){
            proxy.clickElementIfExist(cookiesAccept);
        }
    }

    public void checkErrorInvalidMailText(String errMsg)
    {
        proxy.checkText(loginValidError, errMsg);
    }

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Ввод логина
     * @param login логин
     */
    public void setLogin(String login) {
        loginField.setClearInput(login, false);
    }

    public void setPassword(String password) {
        passInput.setClearInput(password, false);
    }

    public void setRegisterPassword(String password) {
        registerPassInput.setClearInput(password, false);
    }

}

