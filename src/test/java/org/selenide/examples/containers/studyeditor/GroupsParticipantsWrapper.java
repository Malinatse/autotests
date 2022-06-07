package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.group.CreateGroupsLayout;
import org.selenide.examples.testdriver.elements.screeenings.ScreeningsLayout;

/**
 * Объект для взаимодействия с элементом Study Sections
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class GroupsParticipantsWrapper extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studySectionSpoilerWrapper']")
    public SectionGroupsParticipants sectionGroupsParticipants;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='studySectionSpoilerWrapper']/div[1]/button[1]/div[1]/div[1]")
    public Button<SectionGroupsParticipants> sectionGroupsShowHideBtn;

}
