package org.selenide.examples.testdriver.elements.addmoderatorlayout;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.containers.studyeditor.SectionAssignModerator;
import org.selenide.examples.containers.vgtables.reviewparticipants.VGTable;
import org.selenide.examples.containers.vgtables.topicsandquestions.VGRow;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.AbsWrappable;

import java.util.List;

/**
 * Объект для взяимодействия с Add moderator стади
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ModeratorSection extends ElementsContainer {

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='moderatorSelect']")
    public SelectInput moderatorSelect;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupsModeratorSelect']")
    public SelectInput groupsModeratorSelect;

    public void checkModeratorName(String txt) {
        moderatorSelect.checkSelectSpan(txt);
    }
}
