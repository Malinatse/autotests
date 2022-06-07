package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ControlPanelPage;

/**
 * Объект для взаимодействия с модальным окном Assign participant
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class AssignToStudyModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteGroup']")
    public SelectInput assignGroup;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteGroupFromLive']")
    public SelectInput assignGroupFromLive;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteDashboard']")
    public Radio assignDashboard;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteStudyPage']")
    public Radio assignStudyPage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteSubject']")
    public TextInput assignSubject;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteLanguage']")
    public SelectInput assignLanguage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteMessage']")
    public WysiwygTextarea assignMessage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteSendBtn']")
    public Button<ControlPanelPage> assignSendBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectAssignBtn']/.//*[@data-e2e='inviteCancelBtn']")
    public Button<ControlPanelPage> assignCancelBtn;

}
