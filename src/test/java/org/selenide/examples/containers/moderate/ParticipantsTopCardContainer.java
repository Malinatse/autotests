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

public class ParticipantsTopCardContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partGroupTop']")
    private TextProperty partGroupTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partNameTop']")
    private TextProperty partNameTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partGenTop']")
    private TextProperty partGenTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partLocTop']")
    private TextProperty partLocTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partAgeTop']")
    private TextProperty partAgeTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partDNATop']/div/div/div/div/div")
    private List<TextProperty> DNALabelsTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partDNATop']/div/div/div[2]")
    private TextInput DNAInputTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partBioTop']")
    private TextProperty specBioTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partViewBtnTop']")
    private Button<ProspectDetailsModal> partViewBtnTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='profInfoBtnTop']")
    private Button profInfoBtnTop;

}
