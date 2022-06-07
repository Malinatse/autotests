package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.WysiwygTextarea;
import org.selenide.examples.baseelements.questiontypes.MultipleChoiceQuestion;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с модальным окном создания скринингово вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class AddTagsModal extends ElementsContainer {
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

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal' ]")
    public Button closeBtn;
}
