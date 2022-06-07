package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ModeratorPage;

import java.awt.*;

import static com.codeborne.selenide.Selenide.$x;


public class FileModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='fileModal']//img")
    public SelenideElement imageModal;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='videoModal']")
    public SelenideElement videoModal;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal' ]")
    public Button<ModeratorPage> closeBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='addNote']")
    public Button addNote;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='addTagInput']")
    public TextInput addTagInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='addTagBtn']")
    public Button addTagBtn;

    public void addTag(String tag) {
        addNote.clickButton();
        proxy.clickElement($x("(//div[contains(@class,'HighlightMenu__item')])[3]"));
        addTagInput.setClearInput(tag,false);
        addTagBtn.clickButton();
//        addNote.clickButton();
//        addNote.clickButton();
//        proxy.clickElement($x("(//div[contains(@class,'HighlightMenu__item')])[3]"));
//        addTagInput.setClearInput(tag,true);
//        // proxy.clickElement($x("(//div[contains(@class,'underline tag-note__suggestion-item cursor-pointer')])[2]"));
//        proxy.clickElement($x("(//div[contains(@class,'tag-note__suggestion-item flex w-full font-semibold items-center')])"));
    }
}
