package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.CheckBox;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

public class ModeratorFilterContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='searchInput']")
    private TextInput searchInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='searchBtn']")
    private Button searchBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='filterBtn']")
    private Button filterBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='selectSortOptions']")
    private TextInput selectSortOptions;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkFUnres']")
    private CheckBox checkFUnres;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkFA']")
    private CheckBox checkFA;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkFMed']")
    private CheckBox checkFMed;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='checkFStart']")
    private CheckBox checkFStart;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='selectQuestionType']")
    private TextInput selectQuestionType;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='applyBtn']")
    private Button applyBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='resetBtn']")
    private Button resetBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='removeLabelFilter']")
    private List<Button> removeLabelFilter;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='removeAllLabelFilter']")
    private Button removeAllLabelFilter;

}
