package org.selenide.examples.PWA.elements;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.baseelements.WysiwygTextarea;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.awt.*;

public class UserProfileDashboardPWA extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='userFirstNameWelcomeBack']")
    public TextProperty userFirstNameWelcomeBack;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='userFirstName']")
    public TextProperty userFirstName;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='userEmailDashboard']")
    public TextProperty userEmailDashboard;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='userPhoneDashboard']")
    public TextProperty userPhoneDashboard;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='userStory']")
    public TextProperty userStory;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userWysiwygDashboardTextArea']")
    public WysiwygTextarea userStoryArea;

    @FindBy(how = How.XPATH, xpath = ".//button[@data-e2e='userWysiwygSaveBtn']")
    public Button userStoryAreaBtn;


}
