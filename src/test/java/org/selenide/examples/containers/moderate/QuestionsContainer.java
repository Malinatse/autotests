package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

public class QuestionsContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='moderateFilter']")
    private ModeratorFilterContainer moderateFilter;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='queTopicListMod']")
    private List<QuestionsMiniCardContainer> miniCardPartMod;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='queTopicTitleMod']")
    private TextProperty queTopicTitle;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topPageInfo']/div/div/div/div")
    private TextProperty questIdTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topPageInfo']/div/div/div/div[2]")
    private TextProperty questBodyTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='profInfoBtnTop']")
    private Button questBtnTop;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='queCarouselMod']")
    private QuestionsCardContainer questCard;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='conversationStream']")
    private List<ParticipantsConversationStreamContainer> participantsConversationStream;
}
