package org.selenide.examples.testdriver.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.CheckBox;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с элементом Study Card
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ProspectFilter extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='onlyWeChat']")
    public CheckBox onlyWeChat;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='firstName']")
    public TextInput firstName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='firstNamePlus']")
    public SelenideElement firstNamePlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='firstNameRemoval']")
    public SelenideElement firstNameRemoval;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='lastName']")
    public TextInput lastName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='lastNamePlus']")
    public SelenideElement lastNamePlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='lastNameRemoval']")
    public SelenideElement lastNameRemoval;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='mail']")
    public TextInput mail;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='mailPlus']")
    public SelenideElement mailPlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='mailRemoval']")
    public SelenideElement mailRemoval;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='phone']")
    public TextInput phone;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='phonePlus']")
    public SelenideElement phonePlus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='phoneRemoval']")
    public SelenideElement phoneRemoval;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='occupation']")
    public SelectInput occupation;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='nonWeChat']")
    public CheckBox nonWeChat;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='ageMin']")
    public TextInput ageMin;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='ageMax']")
    public TextInput ageMax;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='maleBox']")
    public CheckBox maleBox;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='femaleBox']")
    public CheckBox femaleBox;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='nonBinaryBox']")
    public CheckBox nonBinaryBox;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='hhiMin']")
    public TextInput hhiMin;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='hhiMax']")
    public TextInput hhiMax;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='childrenMin']")
    public TextInput childrenMin;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='childrenMax']")
    public TextInput childrenMax;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='educationLevel']")
    public SelectInput educationLevel;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='maritalStatus']")
    public SelectInput maritalStatus;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='studiesMin']")
    public TextInput studiesMin;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='studiesMax']")
    public TextInput studiesMax;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='luxYes']")
    public CheckBox luxYes;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='luxNo']")
    public CheckBox luxNo;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='luxAll']")
    public CheckBox luxAll;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='superstarYes']")
    public CheckBox superstarYes;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='superstarNo']")
    public CheckBox superstarNo;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='superstarAll']")
    public CheckBox superstarAll;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='ethnicity']")
    public SelectInput ethnicity;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='country']")
    public SelectInput country;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='region']")
    public SelectInput region;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='city']")
    public SelectInput city;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='label']")
    public SelectInput label;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='clearBtn']")
    public Button clearBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectFilters']/.//*[@data-e2e='applyBtn']")
    public SelenideElement applyBtn;

}
