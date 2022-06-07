package org.selenide.examples.PWA.container;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.PWA.pages.StudyDetailsPagePWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class StudyCardPendingPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyHead']")
    public TextProperty studyHead;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyDate']")
    public TextProperty studyDate;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='pendingStudyBody']/div[1]")
    public TextProperty pendingStudyText;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='pendingStudyDetailsText']")
    public TextProperty pendingStudyDetailsText;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public Button<StudyDetailsPagePWA> studyNameBtn;


    public void thisIsPendingStudyCard(){
        String header="Pending approval for a conversation";
        String date="Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"));
        String text="What's Next?";
        String details="We are reviewing candidates and will get back to you once the decision is made.";
        studyHead.checkPropertyHeader(header);
        studyDate.checkPropertyHeader(date);
        pendingStudyText.checkPropertyHeader(text);
        pendingStudyDetailsText.checkPropertyHeader(details);
    }
}
