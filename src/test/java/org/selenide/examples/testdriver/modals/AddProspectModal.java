package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с модальным окном создания участника
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class AddProspectModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='forceYes']")
    public Radio prospectYesRadio;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='forceNo']")
    public Radio prospectNoRadio;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectMail']")
    public TextInput prospectMail;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectFirstName']")
    public TextInput prospectFirstName;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectLastName']")
    public TextInput prospectLastName;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectPhone']")
    public TextInput prospectPhone;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectLanguage']")
    public SelectInput prospectLanguage;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectPhotoIcon']")
    public SelectInput prospectPhotoIcon;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectYearOfBirth']")
    public SelectInput prospectYearOfBirth;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectGender']")
    public SelectInput prospectGender;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectLocation']")
    public TextInput prospectLocation;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectInvite']")
    public Radio prospectInvite;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectAssign']")
    public Radio prospectAssign;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectStudy']")
    public SelectInput prospectStudy;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectGroup']")
    public SelectInput prospectGroup;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectDashboard']")
    public Radio prospectDashboard;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectStudyPage']")
    public Radio prospectStudyPage;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectSubject']")
    public TextInput prospectSubject;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectSubjectLanguage']")
    public SelectInput prospectSubjectLanguage;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectMessage']")
    public WysiwygTextarea prospectMessage;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectSendAndAdd']")
    public Button prospectSendAndAddBtn;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addProspectBtn']/.//div[@data-e2e='prospectSendAndExit']")
    public Button prospectSendAndExitBtn;

}
