package org.selenide.examples.PWA.elements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='selectInterestsCheckbox']")
    private SelenideElement boxHeader;

    @FindBy(how = How.XPATH, xpath = ".//label")
    private SelenideElement boxCheck;

    @FindBy(how = How.XPATH, xpath = ".//span")
    private SelenideElement boxCheckSpan;

    public String getBoxHeader()
    {
        return proxy.getText(boxHeader);
    }


    public void checkBoxHeader(String txt)
    {
        proxy.checkText(boxHeader, txt);
    }

    //TODO:
    //Перенести реализацию в класс SWImplement
    public void checkCheckBox()
    {
        boxCheckSpan.shouldHave(Condition.cssClass("is-checked"));
    }

    public void clickCheckBox()
    {
        proxy.clickElement(boxCheck);
    }

    public void clickCheckBoxQuestion()
    {
        proxy.clickElement(boxCheckSpan);
    }
}
