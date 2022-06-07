package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.group.CreateGroupsLayout;
import org.selenide.examples.testdriver.elements.participants.ProspectsDatabaseLayout;
import org.selenide.examples.testdriver.elements.previewtables.PreviewTablesLayout;
import org.selenide.examples.testdriver.elements.reviewgrid.ReviewGridLayout;
import org.selenide.examples.testdriver.elements.screeenings.ScreeningsLayout;

public class SectionGroupsParticipants extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());


    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='createStudyGroups']")
    public CreateGroupsLayout sectionCreateGroups;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='addStudyProspects']")
    public ProspectsDatabaseLayout sectionProspectsDatabase;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='reviewParticipants']")
    public ReviewGridLayout sectionReviewParticipants;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='previewTables']")
    public PreviewTablesLayout sectionPreviewTables;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='addScreeningQuestions']")
    public ScreeningsLayout sectionCreateScreenings;
}
