package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.ProspectDetailsModal;
import org.w3c.dom.Text;

import java.util.List;

public class QuestionsCardContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='queCarouselMod']/div/div[1]")
    private Button prevBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='queCarouselMod']/div/div[3]")
    private Button nextBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='textQuestion']/span[1]")
    private TextProperty questName;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='textQuestion']/span[2]")
    private TextProperty questBody;
}
