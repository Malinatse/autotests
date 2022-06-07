package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.html.primitives.IHeader;
import org.selenide.examples.baseelements.html.primitives.IInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class DatePicker extends ElementsContainer implements IHeader, IInput {
    @FindBy(how = How.XPATH, xpath = "label")
    private SelenideElement datePickerHeader;

    @FindBy(how = How.XPATH, xpath = ".//input")
    private SelenideElement datePickerInput;

    @FindBy(how = How.XPATH, xpath = ".//svg")
    private SelenideElement datePickerCalendar;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Проверка заголовка поля ввода даты
     * @param txt искомый текст
     */
    public void checkHeader(String txt)
    {
        proxy.checkText(datePickerHeader, txt);
    }

    /**
     * Установка значения для поля
     * @param dateTxt новое значение. Работает автодополнение после нажатие Enter
     * @param enterPress флаг нажатия клавиши Enter после ввода
     */
    public void setClearInput(String dateTxt, Boolean enterPress)
    {
        proxy.setClearText(datePickerInput, dateTxt, enterPress);
    }

    public void setInput(String dateTxt, Boolean enterPress) {
        proxy.setText(datePickerInput, dateTxt, enterPress);
    }

    public void chekInput(String dateTxt) {
        proxy.checkTextFromInput(datePickerInput, dateTxt);
    }

    public void chekInput(String dateTxt, Boolean shouldHave) {
        if (shouldHave)
            proxy.checkTextFromInput(datePickerInput, dateTxt);
    }
}
