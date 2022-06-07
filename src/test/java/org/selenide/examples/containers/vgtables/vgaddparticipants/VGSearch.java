package org.selenide.examples.containers.vgtables.vgaddparticipants;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

public class VGSearch extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "input")
    private SelenideElement input;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Введение значение в строку поиска
     * @param str вводимое значение
     * @param pressEnter признак необходимости нажатия клавиши Enter после окончания ввода
     */
    public void setInput(String str, Boolean pressEnter){
        proxy.setText(input, str, pressEnter);
    }

    /**
     * Введение значение в строку поиска после очистки её содержимого
     * @param str вводимое значение
     * @param pressEnter признак необходимости нажатия клавиши Enter после окончания ввода
     */
    public void setClearInput(String str, Boolean pressEnter){
        proxy.setClearText(input, str, pressEnter);
    }
}
