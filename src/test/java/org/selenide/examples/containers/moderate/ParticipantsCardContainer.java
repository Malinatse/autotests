package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.ProspectDetailsModal;

import java.util.List;

public class ParticipantsCardContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='group']")
    public TextProperty partGroup;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='viewProfBtn']")
    public Button<ProspectDetailsModal> viewProfileBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partNameWithGroup']")
    public TextProperty partNameWithGroup;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partGender']")
    public TextProperty partGender;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAge']")
    public TextProperty partAge;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partLocation']")
    public TextProperty partLocation;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='DNA']/div/div/div/div")
    public List<TextProperty> DNALabels;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='DNA']/div/div[2]")
    public TextInput DNAInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='specBio']")
    public TextProperty specBio;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partPrevMod']")
    public Button partPrevMod;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partNextMod']")
    public Button partNextMod;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partProgress']/div/div[3]/div/div")
    public TextProperty partAllProgressPercent;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partProgress']/div[2]/div/div[1]/div/div[2]")
    public TextProperty partInProgressPercent;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partProgress']/div[2]/div/div[2]/div/div[2]")
    public TextProperty partCompletedPercent;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partProgress']/div[2]/div/div[3]/div/div[2]")
    public TextProperty partNotStartedPercent;
}
