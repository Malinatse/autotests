package org.selenide.examples.PWA.container;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.PWA.pages.LiveStudyPagePWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class StudyCardLivePWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyHead']")
    public TextProperty studyHead;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyDate']")
    public TextProperty studyDate;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='answeredPercents']")
    public TextProperty answeredPercents;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='completedPercents']")
    public TextProperty completedPercents;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='notStartedPercents']")
    public TextProperty notStartedPercents;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='liveStudyBtn']")
    public Button<LiveStudyPagePWA> liveStudyBtn;

    public LiveStudyPagePWA goToLiveStudy(){
       return liveStudyBtn.clickButton(LiveStudyPagePWA.class);
    }

    public void thisIsLiveStudyCard(){
        String header="Live conversation";
        String date="Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"));
        String button="Jump to the Conversation";
        studyHead.checkPropertyHeader(header);
        studyDate.checkPropertyHeader(date);
        liveStudyBtn.checkButtonText(button);
    }

}
