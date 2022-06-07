package org.selenide.examples.containers.cards;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.StudyCard;
import org.selenide.examples.testdriver.pages.ModeratorPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с элементом Draft Study Container
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class LiveStudyContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());


    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='studyCard']")
    public List<StudyCard> liveStudyCards;

    /**
     * Поверка параметра StartDate для указанной карточки Study
     * @param id номер карты
     * @param startDate проверяемое значение
     */
    public void checkCardStartDate(Integer id, String startDate)
    {
        StudyCard card = liveStudyCards.get(id);
        card.cardStartTime.checkPropertyHeader(startDate);
    }

    /**
     * Поверка параметра Team для указанной карточки Study
     * @param id номер карты
     * @param team проверяемое значение
     */
    public void checkCardTeam(Integer id, String team)
    {
        StudyCard card = liveStudyCards.get(id);
        card.cardTeam.checkPropertyHeader(team);
    }

    /**
     * Поверка параметра Header для указанной карточки Study
     * @param id номер карты
     * @param header проверяемое значение
     */
    public void checkCardHeader(Integer id, String header)
    {
        StudyCard card = liveStudyCards.get(id);
        card.cardHeader.checkPropertyHeader(header);
    }

    /**
     * Нажатие на кнопку редактирования указанной карточки Study
     * @param id номер карты
     * @return StudyEditor card
     */
    public StudyEditor clickCardEdit(Integer id)
    {
        StudyCard card = liveStudyCards.get(id);
        StudyEditor res = card.cardEditBtn.clickButton(StudyEditor.class);
        return res;
    }

    public StudyEditor clickCardHeader(Integer id)
    {
        StudyCard card = liveStudyCards.get(id);
        card.cardHeader.clickPropertyHeader();
        return page(StudyEditor.class);
    }

    public ModeratorPage clickCardModerate(Integer id)
    {
        StudyCard card = liveStudyCards.get(id);
        ModeratorPage res = card.cardModerateBtn.clickButton(ModeratorPage.class);
        return res;
    }
}
