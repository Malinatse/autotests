package org.selenide.examples.PWA.pages;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.elements.ScreeningQuestionPWA;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.baseelements.Button;

import java.awt.*;
import java.util.List;


public class StudyScreeningPagePWA extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='topicsNum']")
    public TextProperty topicsNum;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='questionNum']")
    public TextProperty questionNum;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='screeningQuestionsInstructions']")
    public TextProperty screeningQuestionsInstructions;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningQuestion']")
    public List<ScreeningQuestionPWA> screeningQuestion;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='saveScreeningQuestionBtn']")
    public Button saveScreeningQuestionBtn;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='submitScreeningQuestionBtn']")
    public Button<ControlPanelPagePWA> submitScreeningQuestionBtn;

}
