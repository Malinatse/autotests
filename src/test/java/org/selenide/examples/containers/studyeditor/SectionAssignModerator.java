package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.elements.addmoderatorlayout.ModeratorSection;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;

public class SectionAssignModerator extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "div[1]/div[1]")
    public Button plusIcon;

    @FindBy(how = How.XPATH, xpath = ".//h3")
    public TextProperty headerText;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[3]/div[1]/label/span[1]")
    public Button onOffBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[3]/div[1]/label/span[2]")
    public TextProperty statusText;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[4]")
    private Button showHideBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addModeratorBtn']")
    public Button addModeratorBtn;

//    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='moderatorSection']")
//    public List<ModeratorSection> assignModeratorSections;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='moderatorSelect']")
    public List<TextInput> moderatorSelect;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupsModeratorSelect']")
    public List<TextInput> groupsModeratorSelect;

    public void checkModeratorName(Integer id, String txt) {
        TextInput section = moderatorSelect.get(id);
        section.checkInputSpan(txt);
    }

    public void setModeratorName(Integer id, String txt) {
        TextInput section = moderatorSelect.get(id);
        section.setClearInput(txt, true);
    }

    public void setModeratorGroup(Integer id, String txt) {
        TextInput section = groupsModeratorSelect.get(id);
        section.setClearInput(txt, true);
    }

    public SectionAssignModerator clickHeader()
    {
        headerText.clickPropertyHeader();
        return page(SectionAssignModerator.class);
    }
}
