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

public class ParticipantsMiniCardContainer extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partGroupCard']")
    public TextProperty partGroup;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partNameCars']")
    public TextProperty partNameCars;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='partBook']")
    public Button partBookmark;




}
