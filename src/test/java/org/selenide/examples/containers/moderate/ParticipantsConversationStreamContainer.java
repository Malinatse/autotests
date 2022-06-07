package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.elements.TextInputPWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.CheckBox;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.FileModal;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ParticipantsConversationStreamContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='star']")
    public Button star;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionBodyMod']")
    public TextProperty questionBody;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAnswerMod']/div/div/div[3]")
    public TextProperty partAnswer;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAnswerMod']/div/div[2]")
    public SelenideElement partAnswerDot;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='editBtnMod']")
    public Button editBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAnswerMod']/div/div/div[3]/div[2]/div")
    public TextInputPWA partEditInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAnswerMod']//button[1]")
    public Button partEditSave;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAnswerMod']//button[2]")
    public Button partEditCancel;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='removeBtnMod']")
    public Button removeBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='nextAnswerMod']")
    public List<ParticipantsConversationStreamNextAnswerContainer> nextAnswer;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='iconImage']")
    public Button<FileModal> openFileModal;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='iconImage']")
    public SelenideElement iconImageFile;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='moderateAnswerInputMod']/div/div/div/div/div")
    public TextInputPWA moderateAnswerInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='moderateAnswerInputMod']//button")
    public Button sendBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='resQuestionCheckMod']")
    public CheckBox resQuestionCheck;

    public void EditAnswerPart(String txt)
    {
        proxy.clickElement(partAnswerDot);
        proxy.clickElement($x("//div[contains(text(), 'Edit')]"));
        partEditInput.setClearInput(txt,false);
        partEditSave.clickButton();
    }


    public void DeleteAnswerPart()
    {
        proxy.clickElement(partAnswerDot);
        proxy.clickElement($x("//div[contains(text(), 'Delete')]"));
        getWebDriver().switchTo().alert().accept();
    }
}
