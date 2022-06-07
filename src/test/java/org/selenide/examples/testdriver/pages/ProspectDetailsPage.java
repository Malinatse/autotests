package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

/**
 * Объект для взаимодействия со страницей просмотра участника
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ProspectDetailsPage extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectSuperStar']/div[2]/div/span")
    private SelenideElement prospectSuperstar;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLuxury']/div[2]/div/span")
    private SelenideElement prospectLuxury;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsGender']")
    public TextProperty prospectGender;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsBirthday']")
    public TextProperty prospectAge;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='closeModal']")
    public Button<StudyEditor> closeBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsLocation']")
    public TextProperty prospectLocation;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsMarital']")
    public TextProperty prospectMaterialStatus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsChildren']")
    public TextProperty prospectChildren;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsEthnicity']")
    public TextProperty prospectEthnicity;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsHousehold']")
    public TextProperty prospectHouseholdIncome;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsEducation']")
    public TextProperty prospectEducation;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsEmployment']")
    public TextProperty prospectEmploymentStatus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsOccupation']")
    public TextProperty prospectOccupation;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelText']")
    public List<TextProperty> labelTextLst;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelClear']")
    public List<Button> labelClearLst;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelNewText']")
    public TextInput labelNewText;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelAdd']")
    public Button labelAddBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelCancel']")
    public Button labelCancelBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectDetailsBtn']")
    public Button prospectDetailsBtn;

}
