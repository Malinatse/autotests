package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.vgtables.topicsandquestions.VGTable;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.addmoderatorlayout.ModeratorSection;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.modals.EditTopicModal;

import java.util.List;

public class SectionTopicsAndQuestions extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "div[1]/div[1]")
    public Button plusIcon;

    @FindBy(how = How.XPATH, xpath = ".//div/h3")
    public TextProperty headerText;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[3]/div[1]/label/span[1]")
    public Button onOffBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[3]/div[1]/label/span[2]")
    public TextProperty statusText;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[4]")
    private Button showHideBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addModeratorBtn']")
    public Button addModeratorBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='tQTable']")
    public List<VGTable> tQTable;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addTopicBtn']")
    public Button<CreateEditTopicModal> addTopic;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addQuestionBtn']")
    public List<Button> addQuestion;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topicName']")
    public TextProperty topicName;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='editTopicBtn']")
    public Button<EditTopicModal> editTopic;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='deleteTopicA']")
    public List<Button> deleteTopic;

    public Button<CreateEditQuestionModal> getAddQuestionBtn(Integer id)
    {
        return addQuestion.get(id);
    }

    public VGTable getTable(Integer id)
    {
        return tQTable.get(id);
    }

    public Button getDeleteTopicBtn(Integer id)
    {
        return deleteTopic.get(id);
    }
}
