package org.selenide.examples.containers.moderate;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class NotebookContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='optionsListNotebook']")
    public Button optionsListNotebook;

    public void deleteTag(){
        optionsListNotebook.clickButton();
        proxy.clickElement($x("//div[contains(text(), 'Delete')]"));
    }

}
