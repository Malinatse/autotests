package org.selenide.examples.PWA.elements;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.baseelements.questiontypes.SliderQuestion;
import org.selenide.examples.baseelements.questiontypes.StarsQuestion;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

public class ScreeningQuestionPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='questionBody']")
    public TextProperty questionBody;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningAnswerText']/div/div[2]/div/div/div")
    public WysiwygTextarea screeningAnswerText;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningAnswerRadio']")
    public List<Radio> screeningAnswerRadio;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningAnswerSlider']")
    public SliderQuestion screeningAnswerSlider;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningAnswerCheckbox']")
    public List<CheckBox> screeningAnswerCheckbox;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningAnswerSingleChoice']")
    public List<Radio> screeningAnswerSingleChoice;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningAnswerStars']")
    public StarsQuestion screeningAnswerStars;
}




