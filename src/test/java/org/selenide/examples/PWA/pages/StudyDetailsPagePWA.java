package org.selenide.examples.PWA.pages;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.studyeditor.Crumbs;

import java.awt.*;

public class StudyDetailsPagePWA extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='crumbLink']")
    public Crumbs crumbsLine;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studyDates']")
    public TextProperty studyDate;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='waitingTitleBlock']")
    public TextProperty waitingTitleBlock;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='waitingDescriptionBlock']")
    public TextProperty waitingDescriptionBlock;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='goToDashboardBtn']")
    public Button goToDashboardBtn;

    public void thisIsWaitingStudyPage(){
        String date="Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"));
        studyDate.checkPropertyHeader(date);

    }

    public void thisIsPendingStudyPage(){
        String date="Conversation date: "+ (ConfProperties.getProperty("studyTestStartDate"));
        studyDate.checkPropertyHeader(date);
    }
}
