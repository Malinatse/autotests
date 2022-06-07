package org.selenide.examples.testdriver.elements;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ModeratorPage;

/**
 * Объект для взаимодействия с элементом Study Card
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class StudyCard extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "span")
    public TextProperty cardState;

    @FindBy(how = How.XPATH, xpath = "*//h2/a")
    public TextProperty cardHeader;

    @FindBy(how = How.XPATH, xpath = "*//h2")
    public TextProperty cardHeaderText;

    @FindBy(how = How.XPATH, xpath = "header/p")
    public TextProperty cardTeam;

    @FindBy(how = How.XPATH, xpath = "div[3]/div[1]/div[1]/span")
    public TextProperty cardStartTime;

    @FindBy(how = How.XPATH, xpath = "div[3]/div[1]/div[2]/span")
    public TextProperty cardParticipants;

    @FindBy(how = How.XPATH, xpath = "div[3]/div[1]/div[3]/span")
    public TextProperty cardGroups;

//    @FindBy(how = How.XPATH, xpath = "div[3]/div[2]/button")
//    public Button<StudyEditor> cardEditBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='editBtn']")
    public Button<StudyEditor> cardEditBtn;

    @FindBy(how = How.XPATH, xpath = "*//div/a[1]")
    public Button<ModeratorPage> cardModerateBtn;
}
