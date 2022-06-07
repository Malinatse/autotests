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
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ControlPanelPage;

/**
 * Объект для взаимодействия с модальным окном редактирования скринингово вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class EditScreeningQuestionModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningSelect']")
    public SelectInput questionType;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningTextEditor']")
    public WysiwygTextarea questionText;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningYesNoQuestion']")
    public YesNoQuestion  questionYesNo;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningMultiChoiseQuestion']")
    public MultipleChoiceQuestion questionMultiChoise;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningSliderQuestion']")
    public SliderQuestion questionSlider;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningStarsQuestion']")
    public StarsQuestion questionStars;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/.//div[@data-e2e='editScreeningSave']")
    public Button<ControlPanelPage> questionSave;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='editScreeningModal']/div[1]/div[2]/button")
    public Button<ControlPanelPage> questionClose;



}
