package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.CheckBox;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

public class ParticipantsContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='moderateFilter']")
    public ModeratorFilterContainer moderateFilter;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkPFAll']")
    public CheckBox partFilterAllCheck;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkPFAssigned']")
    public CheckBox partFilterAssignedCheck;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkPFAssigned']")
    public CheckBox partFilterPinnedCheck;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='miniCardPartMod']")
    public List<ParticipantsMiniCardContainer> miniCardPartMod;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topPageInfo']")
    public ParticipantsTopCardContainer partTopCard;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partInfo']")
    public ParticipantsCardContainer partCard;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkExpand']")
    public CheckBox checkExpand;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='conversationStream']")
    public List<ParticipantsConversationStreamContainer> participantsConversationStream;


}
