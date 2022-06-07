package org.selenide.examples.testdriver.elements.group;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;


/**
 * Объект для взяимодействия с элементами конкретной группы
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class GroupEditor extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupDetailsforParticipants']")
    public WysiwygTextarea detailsForParticipants;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupApply']")
    public CheckBox applyForAllBox;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupColors']")
    public ColorPicker colorPicker;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupDescription']")
    public WysiwygTextarea groupDescription;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupParticipantsNumber']")
    public TextInput groupParticipants;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupParticipantsByGender']")
    public TextInput groupParticipantsByGender;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupGenderPreferenceCheckbox']")
    public CheckBox genderCheckBox;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupGeography']")
    public TextInput geography;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupChildrenHome']")
    public TextInput childrenAtHome;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupCommentsForRecruiter']")
    public WysiwygTextarea commentsForRecruiter;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupName']")
    public TextInput groupNameInput;

    @FindBy(how = How.XPATH, xpath = "(.//*[@data-e2e='groupGenderCount'])[1]")
    public TextInput maleCount;

    @FindBy(how = How.XPATH, xpath = "(.//*[@data-e2e='groupGenderCount'])[2]")
    public TextInput femaleCount;

    @FindBy(how = How.XPATH, xpath = "(.//*[@data-e2e='groupGenderCount'])[3]")
    public TextInput nonBinaryCount;

    @FindBy(how = How.XPATH, xpath = "div/div[1]/div/div[1]")
    public Button groupHeaderButton;

    @FindBy(how = How.XPATH, xpath = "div/div[1]/div/div[2]/div/h3")
    public TextProperty groupHeaderText;

    @FindBy(how = How.XPATH, xpath = "div/div[2]/div/div[2]/div/h3")
    public TextInput cloneInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupStudyMoreFields']")
    public Button moreFieldsBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupSave']")
    public Button saveBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='groupDelete']")
    public Button deleteBtn;

}
