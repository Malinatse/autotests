package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.WysiwygTextarea;
import org.selenide.examples.baseelements.questiontypes.MultipleChoiceQuestion;
import org.selenide.examples.baseelements.questiontypes.SliderQuestion;
import org.selenide.examples.baseelements.questiontypes.StarsQuestion;
import org.selenide.examples.baseelements.questiontypes.YesNoQuestion;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ControlPanelPage;

/**
 * Объект для взаимодействия с модальным окном создания скринингово вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class AddScreeningQuestionModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addSQType']")
    public SelectInput questionType;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addSQQuestion']")
    public WysiwygTextarea questionText;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addSQYesNo']")
    public YesNoQuestion  questionYesNo;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addSQMultiple']")
    public MultipleChoiceQuestion questionMultiChoise;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addSQSlider']")
    public SliderQuestion questionSlider;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addSQStars']")
    public StarsQuestion questionStars;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/.//div[@data-e2e='addScreeningQuestion']")
    public Button<ControlPanelPage> questionSave;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addSQModal']/div[2]/button")
    public Button<ControlPanelPage> questionClose;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addSQSaveBtn']")
    public Button SQSaveBtn;

}
