package org.selenide.examples.testdriver.elements.screeenings;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.baseelements.WysiwygTextarea;
import org.selenide.examples.containers.vgtables.vgscreeningquestions.VGTable;
import org.selenide.examples.testdriver.elements.AbsWrappable;
import org.selenide.examples.testdriver.modals.AddScreeningQuestionModal;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Объект для взяимодействия со скрининговыми вопросами стади
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ScreeningsLayout extends AbsWrappable {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningQuestionsTable']")
    public VGTable screeningTable;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningInstructionsSave']")
    public Button saveInstructionsBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningUploadFile']/Label")
    public Button screeningUploadBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningPrintFile']")
    public Button screeningPrintBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='addSQAddBtn']")
    public Button<AddScreeningQuestionModal> screeningAddBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='screeningInstructions']")
    public WysiwygTextarea instructionsEditor;

    @FindBy(how = How.XPATH, xpath = ".//div/h3")
    public TextProperty headerText;

    public void addSQInstruction(String instruction){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        wait.until(ExpectedConditions.attributeToBe(By.xpath(".//*[@data-e2e='screeningInstructions']/div[2]/div/div"),"contenteditable","true"));
        instructionsEditor.setMultilinedText(instruction,false);
        saveInstructionsBtn.clickButton();
    }

}
