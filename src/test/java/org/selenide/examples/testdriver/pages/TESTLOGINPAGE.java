package org.selenide.examples.testdriver.pages;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextInput;

public class TESTLOGINPAGE extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = "//*[@class='LLSIInput mt-6']")
    public TextInput loginInput;

    @FindBy(how = How.XPATH, xpath = "//button[@class='LLSIButton']")
    public Button nextButton;

    public void enterLogin(String login){
        loginInput.setClearInput(login,false);
        nextButton.clickButton();
    }
}

