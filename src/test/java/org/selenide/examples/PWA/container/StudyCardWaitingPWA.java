package org.selenide.examples.PWA.container;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.PWA.pages.LiveStudyPagePWA;
import org.selenide.examples.PWA.pages.StudyDetailsPagePWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class StudyCardWaitingPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyHead']")
    public TextProperty studyHead;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public Button<StudyDetailsPagePWA> studyNameBtn;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyDate']")
    public TextProperty studyDate;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='waitingStudyBody']/div[1]")
    public TextProperty waitingStudyText;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='waitingStudyDetailsText']")
    public TextProperty waitingStudyDetailsText;

    public void thisIsWaitingStudyCard(){
        String header="Approved! The conversation will start soon!";
        String date="Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"));
        String text="What's Next?";
        String details="This conversation is scheduled to start on "+ (ConfProperties.getProperty("studyTestStartDate"))+". We will email you a reminder. If, for any reason, you are not available, please contact us as soon as possible.";
        studyHead.checkPropertyHeader(header);
        studyDate.checkPropertyHeader(date);
        waitingStudyText.checkPropertyHeader(text);
        waitingStudyDetailsText.checkPropertyHeader(details);
    }

}
