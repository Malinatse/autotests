package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с элементом Study Sections
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class StudySections extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='addGroupsParticipants']")
    public GroupsParticipantsWrapper groupsParticipantsWrapper;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='topicsAndQuestionsSection']")
    public SectionTopicsAndQuestions topicsAndQuestions;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='addTagsSection']")
    public SectionAddTags addTags;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='moderatorData']")
    public SectionAssignModerator moderatorData;

}
