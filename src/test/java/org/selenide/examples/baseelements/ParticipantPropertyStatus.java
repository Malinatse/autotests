package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с окном свойства партисипанта
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class ParticipantPropertyStatus extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "div[1]/h5")
    public TextProperty propHeader;

    @FindBy(how = How.XPATH, xpath = "div[2]/svg")
    public Button propEditBtn;

    @FindBy(how = How.XPATH, xpath = "div[2]/div[1]/div[1]/span")
    public TextProperty propStatus;

    @FindBy(how = How.XPATH, xpath = "div[2]/div[1]/div[1]/label")
    public TextProperty propYesBtn;

    @FindBy(how = How.XPATH, xpath = "div[2]/div[1]/div[2]/label")
    public TextProperty propNoBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[1]/svg")
    public TextProperty propOkBtn;

}
