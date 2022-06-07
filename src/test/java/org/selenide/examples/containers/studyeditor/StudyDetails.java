package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с элементом Study Details
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class StudyDetails extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyName']")
    public TextInput studyNameInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyTeam']")
    public SelectInput studyTeamInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyClone']")
    public TextInput studyCloneInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyStartDate']")
    public DatePicker studyStartDatePicker;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyEndDate']")
    public DatePicker studyEndDatePicker;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyObjectives']")
    public WysiwygTextarea studyObjectivesInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studySaveBtn']")
    public Button studySaveBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyCreateBtn']")
    public Button studyCreateBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='btnActivateStudy']")
    public Button studyActivateBtn;

}
