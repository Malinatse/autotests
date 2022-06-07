package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ModeratorPage;

public class ContactParticipantModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal']")
    public Button<ModeratorPage> closeBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partSelectModModal']")
    public TextInput partSelect;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='subjectInputModModal']")
    public TextInput subjectInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='dateModModal']")
    public TextInput dateInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='timeModModal']")
    public TextInput timeInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='messageModModal']")
    public TextInput messageInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='sendBtnModModal']")
    public Button<ModeratorPage> sendBtn;
}
