package org.selenide.examples.testdriver.elements.participants;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.WysiwygTextarea;
import org.selenide.examples.containers.lltables.participantdb.LLTable;
import org.selenide.examples.containers.vgtables.vgaddparticipants.VGTable;
import org.selenide.examples.testdriver.elements.AbsWrappable;
import org.selenide.examples.testdriver.elements.ProspectFilter;
import org.selenide.examples.testdriver.modals.AddProspectModal;
import org.selenide.examples.testdriver.modals.AssignToStudyModal;
import org.selenide.examples.testdriver.modals.InviteToStudyModal;


/**
 * Объект для взяимодействия со скрининговыми вопросами стади
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ProspectsDatabaseLayout extends AbsWrappable {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectsDatabase']")
    public LLTable prospectsDatabase;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectEmailList']")
    public TextInput prospectsEmailInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectEmailHeader']")
    public Button prospectsEmailHeader;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addProspectsBtn']")
    public Button addProspectsBtn;

}
