package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с модальным окном создания скринингово вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class EditTopicModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topicNameInput' ]")
    public TextInput topicNameInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='saveTopicBtn' ]")
    public Button saveTopicBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal' ]")
    public Button closeBtn;
}
