package org.selenide.examples.PWA.elements;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

public class TopicsCardPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topicNumber']")
    public TextProperty topicNumber;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='topicName']")
    public TextProperty topicName;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='questionNum']")
    public TextProperty questionNum;

}
