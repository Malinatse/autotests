package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.baseelements.questiontypes.MultipleChoiceQuestion;
import org.selenide.examples.baseelements.questiontypes.SliderQuestion;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

/**
 * Объект для взаимодействия с модальным окном создания скринингово вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class CreateEditQuestionModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='modalQuestionType']")
    public SelectInput questionType;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionTitle']")
    public TextInput questionTitle;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionBody']")
    public WysiwygTextarea questionBody;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='saveQuestionBtn']")
    public Button saveQuestion;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionMultiple']")
    public MultipleChoiceQuestion multiChoiceQuestion;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionSlider']/div[1]/div[1]")
    public TextInput sliderQuestionLeft;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionSlider']/div[1]/div[2]")
    public TextInput sliderQuestionRight;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionCheck']")
    public CheckBox modalQuestionCheck;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionAddProbeBtn']")
    public Button modalQuestionAddProbeBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionProbeText']/div[2]/div/div[2]")
    public Radio modalQuestionProbeRadio;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='probeEditWysiwygTextArea']")
    public WysiwygTextarea modalQuestionProbeText;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='modalQuestionProbeText']/div[3]/div[2]/button[2]")
    public Button modalQuestionProbeBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='automaticallyResolveModalCheckbox']")
    public CheckBox automaticallyResolveModalCheckbox;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='radioGroup']")
    public Radio radioGroup;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='selectedGroup']")
    public List<Button> selectedGroupBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal']")
    public Button closeBtn;

    public void createTextQuestion(String name,String body){
        questionTitle.setClearInput(name,false);
        questionType.setClearInput("Text Field", false);
        questionBody.setClearMultilinedText(body,false);
        saveQuestion.clickButton();
    }

    public void createYesNoQuestion(String name,String body, Boolean textInput){
        questionTitle.setClearInput(name,false);
        questionType.setClearInput("Yes No", false);
        questionBody.setClearMultilinedText(body,false);
        if (textInput==true){ modalQuestionCheck.clickCheckBox();}
        saveQuestion.clickButton();
    }

    public void createMultipleChoiceQuestion(String name,String body,String answer1,String answer2,Boolean textInput){
        questionTitle.setClearInput(name,false);
        questionType.setClearInput("Multiple Choice", false);
        questionBody.setClearMultilinedText(body,false);
        multiChoiceQuestion.setAnswer(answer1,0,false);
        multiChoiceQuestion.setAnswer(answer2,1,false);
        if (textInput==true){ modalQuestionCheck.clickCheckBox();}
        saveQuestion.clickButton();
    }

    public void createSliderQuestion(String name,String body, String sliderLeft, String sliderRight,Boolean textInput){
        questionTitle.setClearInput(name,false);
        questionType.setClearInput("Slider Ranking", false);
        questionBody.setClearMultilinedText(body,false);
        sliderQuestionLeft.setClearInput(sliderLeft,false);
        sliderQuestionRight.setClearInput(sliderRight,false);
        if (textInput==true){ modalQuestionCheck.clickCheckBox();}
        saveQuestion.clickButton();
    }

    public void createStarsQuestion(String name,String body,Boolean textInput){
        questionTitle.setClearInput(name,false);
        questionType.setClearInput("Stars", false);
        questionBody.setClearMultilinedText(body,false);
        if (textInput==true){ modalQuestionCheck.clickCheckBox();}
        saveQuestion.clickButton();
    }

    public void createSingleChoiceQuestion(String name,String body,String answer1,String answer2,Boolean textInput) {
        questionTitle.setClearInput(name, false);
        questionType.setClearInput("Single Choice", false);
        questionBody.setClearMultilinedText(body, false);
        multiChoiceQuestion.setAnswer(answer1, 0, false);
        multiChoiceQuestion.setAnswer(answer2, 1, false);
        if (textInput==true){ modalQuestionCheck.clickCheckBox();}
        saveQuestion.clickButton();
    }
}
