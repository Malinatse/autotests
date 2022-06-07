package org.selenide.examples.baseelements.questiontypes;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с полями Да/Нет вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class YesNoQuestion extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//p")
    private SelenideElement questionYesNoHeader;

    @FindBy(how = How.XPATH, xpath = "//div[1]/div[1]")
    public TextInput questionYes;

    @FindBy(how = How.XPATH, xpath = "//div[1]/div[2]")
    public TextInput questionNo;

    /**
     * Проверка заголовка Да/Нет вопроса
     * @param txt проверяемый текст
     */
    public void checkYesNoHeader(String txt)
    {
        proxy.checkText(questionYesNoHeader, txt);
    }
}
