package org.selenide.examples.PWA.container;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.PWA.pages.StudyScreeningPagePWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class StudyCardInvitedPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyHead']")
    public TextProperty studyHead;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyDate']")
    public TextProperty studyDate;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='acceptInvitedStudyBtn']")
    public Button acceptInvitedStudyBtn;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='goToScreenInvitedStudyBtn']")
    public Button<StudyScreeningPagePWA> acceptInvitedStudyBtnSQ;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='rejectInvitedStudyBtn']")
    public Button rejectInvitedStudyBtn;

    public void thisIsInvitedStudyCard(){
        String header="Test, you are invited to a conversation";
        String date="Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"));
        String buttonYes="I'm In!";
        String buttonNo="Not This Time";
        studyHead.checkPropertyHeader(header);
        studyDate.checkPropertyHeader(date);
        acceptInvitedStudyBtn.checkButtonText(buttonYes);
        rejectInvitedStudyBtn.checkButtonText(buttonNo);
    }
}
