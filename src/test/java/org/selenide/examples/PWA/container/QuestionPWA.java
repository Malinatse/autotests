package org.selenide.examples.PWA.container;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.elements.CheckBoxPWA;
import org.selenide.examples.PWA.elements.TextInputPWA;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.baseelements.questiontypes.StarsQuestion;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class QuestionPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='title']")
    public TextProperty titleQuestion;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionInput']")
    public TextInputPWA questionInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionValue']")
    public TextProperty questionValue;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='sendBtn']")
    public Button sendBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addMoreBtn']")
    public Button addMoreBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionTextLocked']")
    public List<TextProperty> questionTextLocked;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userOrModeratorName']")
    public List<TextProperty> userOrModeratorName;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionRadio']")
    public List<Radio> questionRadio;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionCheckbox']")
    public List<CheckBoxPWA> questionCheckbox;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionDecrementBtn']")
    public Button questionDecrementBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionIncrementBtn']")
    public Button questionIncrementBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionSliderValue']")
    public TextProperty questionSliderValue;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionStrMax']")
    public TextProperty questionStrMax;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionStrMin']")
    public TextProperty questionStrMin;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionStarBlockBtn']")
    public List<Button> questionStarBlockBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionSingleChoiceRadio']")
    public List<Radio> questionSingleChoiceRadio;

    public void setQuestionIncrementBtn(Integer num){
        for (int i=0;i<num;i++)
            questionIncrementBtn.clickButton();
    }

    public void checkStarsCount(int count)
    {
        $$x(".//div[@data-e2e=\"questionStarBlockBtn\"]/div[1]/div[2][contains(@class, \"star-fill_visible\")]").shouldHaveSize(count);
    }

}
