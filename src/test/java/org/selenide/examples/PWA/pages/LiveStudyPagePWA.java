package org.selenide.examples.PWA.pages;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.container.QuestionPWA;
import org.selenide.examples.PWA.elements.TopicsCardPWA;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.studyeditor.Crumbs;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

public class LiveStudyPagePWA extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='crumbLink']")
    public Crumbs crumbsLine;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyName']")
    public TextProperty studyName;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyDates']")
    public TextProperty studyDates;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyRoundProgress']")
    public TextProperty studyRoundProgress;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='StudyProgress']")
    public TextProperty StudyProgress;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='answeredPercents']")
    public TextProperty answeredPercents;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='completedPercents']")
    public TextProperty completedPercents;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='notStartedPercents']")
    public TextProperty notStartedPercents;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='selectTopic']")
    public List<TopicsCardPWA> topics;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='selectTopicBtn']")
    public List<Button> topicsUp;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topicInstruction']")
    public TextProperty topicInstruction;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='question']")
    public List<QuestionPWA> questions;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void selectTopic(Integer id){
         topics.get(id).questionNum.clickPropertyHeader();
    }

    public void clickQuestion(Integer id){
        questions.get(id).titleQuestion.clickPropertyHeader();
    }



}
