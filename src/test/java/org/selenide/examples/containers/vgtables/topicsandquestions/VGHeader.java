package org.selenide.examples.containers.vgtables.topicsandquestions;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для инициализации каждого заголовка
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGHeader extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());
    @FindBy(how = How.XPATH, xpath = "span")
    private SelenideElement header;

    /**
     * Получение текста заголовка
     *
     * @return String текст заголовка
     */
    public String getName() {
        return proxy.getText(header);
    }
}
