package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.elements.TextInputPWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.FileModal;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ParticipantsConversationStreamNextAnswerContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "div/div/div[2]")
    public TextProperty nextAnswerText;

    @FindBy(how = How.XPATH, xpath = "div/div[2]")
    public SelenideElement nextAnswerDot;

    @FindBy(how = How.XPATH, xpath = "div/div/div[2]/div[2]/div/div")
    public TextInputPWA nextEditInput;

    @FindBy(how = How.XPATH, xpath = "div/div/div[3]//button[1]")
    public Button nextEditSave;

    @FindBy(how = How.XPATH, xpath = "div/div/div[3]//button[2]")
    public Button nextEditCancel;

    public void EditAnswer(String txt)
    {
        proxy.clickElement(nextAnswerDot);
        proxy.clickElement($x("//div[contains(text(), 'Edit')]"));
        nextEditInput.setClearInput(txt,false);
        nextEditSave.clickButton();
    }

    public void DeleteAnswer()
    {
        proxy.clickElement(nextAnswerDot);
        proxy.clickElement($x("//div[contains(text(), 'Delete')]"));
        getWebDriver().switchTo().alert().accept();
    }
}
