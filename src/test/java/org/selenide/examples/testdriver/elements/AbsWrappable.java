package org.selenide.examples.testdriver.elements;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public abstract class AbsWrappable extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = ".//h3")
    public TextProperty groupHeader;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[3]")
    public Button showHideBtn;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

}
