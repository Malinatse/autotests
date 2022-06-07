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
 * Объект для взаимодействия с модальным окном Invite participant
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class InviteToStudyModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteGroup']")
    public SelectInput inviteGroup;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteGroupFromLive']")
    public SelectInput inviteGroupFromLive;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteDashboard']")
    public Radio inviteDashboard;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteStudyPage']")
    public Radio inviteStudyPage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteSubject']")
    public TextInput inviteSubject;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteLanguage']")
    public SelectInput inviteLanguage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteMessage']")
    public WysiwygTextarea inviteMessage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteSendBtn']")
    public Button<ControlPanelPage> inviteSendBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='prospectInviteBtn']/.//*[@data-e2e='inviteCancelBtn']")
    public Button<ControlPanelPage> inviteCancelBtn;

}
