package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.vgtables.topicsandquestions.VGTable;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.AddTagsModal;
import org.selenide.examples.testdriver.modals.CreateEditQuestionModal;
import org.selenide.examples.testdriver.modals.CreateEditTopicModal;
import org.selenide.examples.testdriver.modals.EditTopicModal;

import java.util.List;

public class SectionAddTags extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div/h3")
    public TextProperty headerText;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addTagsBtn']")
    public Button<AddTagsModal> addTagsBtn;

}
