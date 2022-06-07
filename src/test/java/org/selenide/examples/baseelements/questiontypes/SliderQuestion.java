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
 * Объект для взаимодействия с полями слайдер вопроса
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class SliderQuestion extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//p")
    private SelenideElement questionSliderHeader;

    @FindBy(how = How.XPATH, xpath = "//div[1]/div[1]")
    public TextInput questionLeft;

    @FindBy(how = How.XPATH, xpath = "//div[1]/div[2]")
    public TextInput questionCenter;

    @FindBy(how = How.XPATH, xpath = "//div[1]/div[2]")
    public TextInput questionRight;

    /**
     * Проверка заголовка Да/Нет вопроса
     * @param txt проверяемый текст
     */
    public void checkYesNoHeader(String txt)
    {
        proxy.checkText(questionSliderHeader, txt);
    }
}
