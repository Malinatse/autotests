package org.selenide.examples.testdriver.elements.reviewgrid;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.containers.lltables.participantdb.LLTable;
import org.selenide.examples.containers.lltables.reviewprospects.LLReviewProspectsTable;
import org.selenide.examples.containers.studyeditor.SectionGroupsParticipants;
import org.selenide.examples.containers.vgtables.reviewparticipants.VGTable;
import org.selenide.examples.testdriver.elements.AbsWrappable;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взяимодействия с Review Grid стади
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ReviewGridLayout extends AbsWrappable {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='reviewProspectTable']")
    public LLReviewProspectsTable reviewProspectTable;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='reviewProspectsSearch']")
    public TextInput searchInput;

    public SectionGroupsParticipants performSearch(String txt)
    {
        searchInput.setClearInput(txt, true);
        return page(SectionGroupsParticipants.class);
    }

}
