package org.selenide.examples.baseelements.questiontypes;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с полями вопроса со звёздами
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class StarsQuestion extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "//p")
    private SelenideElement questionStarsHeader;

    @FindBy(how = How.XPATH, xpath = "//div[1]/input")
    private SelenideElement questionStarsInput;

    /**
     * Проверка заголовка Да/Нет вопроса
     * @param txt проверяемый текст
     */
    public void checkYesNoHeader(String txt)
    {
        proxy.checkText(questionStarsHeader, txt);
    }

    /**
     * Установка количества звёзд (1 .. 10)
     * @param txt устанавливаемое значение
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setStarsInputValue(String txt, Boolean enterPress)
    {
        proxy.setTextForInput(questionStarsInput, txt, enterPress);
    }
}
