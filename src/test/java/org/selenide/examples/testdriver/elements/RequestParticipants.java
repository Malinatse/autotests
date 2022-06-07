package org.selenide.examples.testdriver.elements;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.CheckBox;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

/**
 * Объект для взаимодействия с элементами контейнера Request Participants
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class RequestParticipants extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='requestParticipantsTab']/.//div[@data-e2e='requestParticipantsCheckbox']")
    private List<CheckBox> groupCheckBoxes;


    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='requestParticipantsTab']/.//div[@data-e2e='sendRequestParticipants']")
    public Button sendRequest;

    /**
     * Получение чекбокса по заданному порядковому номеру
     * @param id номер чекбокса
     * @return CheckBox
     */
    public CheckBox getGroupById(Integer id)
    {
        if ((id < 0) || (id > groupCheckBoxes.size()))
        {
            //TODO:
            // raise an exception
        }
        return groupCheckBoxes.get(id);
    }
}
