package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;


/**
 * Объект для взаимодействия со страницей логина
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class LoginPage extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = "//*[contains(@type, 'email')]")
    private SelenideElement loginField;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='signInEmailInput']/div[2]")
    private SelenideElement loginErrorMsg;

    @FindBy(how = How.XPATH, xpath = "//*[contains(@type, 'password')]")
    private SelenideElement passwdField;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='signInPasswordInput']/div[2]")
    private SelenideElement passErrorMsg;

    @FindBy(how = How.XPATH, xpath = "//*[contains(@data-e2e, 'signInSubmitBtn')]")
    private SelenideElement loginBtn;

    @FindBy(how = How.XPATH, xpath = "//*[contains(@data-e2e, 'signInSubmitBtn')]")
    public Button<ControlPanelPage> loginBtn2;

    @FindBy(how = How.XPATH, xpath = "//div[contains(@data-e2e, 'userError')]")
    private SelenideElement userErrorMessage;

    @FindBy(how = How.XPATH, xpath = "//button[contains(@class, 'btn CookiesAgreement__button btn_mini btn-accent')]")
    private SelenideElement cookiesAccept;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Ввод логина
     * @param login логин
     */
    public void setLogin(String login) {
        proxy.setText(loginField, login, false);
        proxy.clickElement(cookiesAccept);
    }

    /**
     * Проверка существования сообщения об ошибке в email
     * @param existence флаг существования
     */
    public void checkErrorMailExists(Boolean existence)
    {
        proxy.checkExistence(loginErrorMsg, existence);
    }

    /**
     * Проверка текста ошибки в email
     * @param errMsg текст ошибки
     */
    public void checkErrorMailText(String errMsg)
    {
        proxy.checkText(loginErrorMsg, errMsg);
    }

    /**
     * Ввод пароля
     * @param passwd пароль
     */
    public void setPasswd(String passwd) {
        proxy.setText(passwdField, passwd, false);
    }

    /**
     * Проверка существования сообщения об ошибке в пароле
     * @param existence
     */
    public void checkErrorPassExists (Boolean existence)
    {
        proxy.checkExistence(passErrorMsg, existence);
    }

    /**
     * Проверка текста ошибки в пароле
     * @param errMsg текст ошибки
     */
    public void checkErrorPassText(String errMsg)
    {
        proxy.checkText(passErrorMsg, errMsg);
    }

    /**
     * Нажатие на кнопку SignIn
     * @return ControlPanelPage главная страница при удачной авторищации
     */
    public ControlPanelPage clickLoginBtn() {
        proxy.clickElement(loginBtn);
        return Selenide.page(ControlPanelPage.class);
    }

    /**
     * Проверка существования сообщения об ошибке пользователя
     * @param existence флаг существования
     */
    public void checkErrorUserExists(Boolean existence)
    {
        proxy.checkExistence(userErrorMessage, existence);
    }

    /**
     * Проверка текста ошибки авторизации пользователя
     * @param errMsg текст ошибки
     */
    public void checkErrorUserText(String errMsg)
    {
        proxy.checkText(userErrorMessage, errMsg);
    }
}
