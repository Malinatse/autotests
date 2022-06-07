package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.ConfProperties;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class StudyEditor extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='studyEditorCrumbs']")
    public Crumbs crumbs;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='studyDetails']")
    public StudyDetails studyDetails;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='studySections']")
    public StudySections studySections;

    public void createStudy(String studyName){
        studyDetails.studyNameInput.setClearInput(studyName, false);
        studyDetails.studyStartDatePicker.setClearInput(ConfProperties.getProperty("studyTestStartDate"), false);
        studyDetails.studyObjectivesInput.setClearMultilinedText(ConfProperties.getProperty("studyTestObjectives"), false);
        studyDetails.studyCreateBtn.clickButton();
    }
}
