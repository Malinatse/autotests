package org.selenide.examples.containers.lltables.reviewprospects;

import com.codeborne.selenide.ElementsContainer;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Объект для взаимодействия с одним радом таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class LLColumn extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void checkHeader(String tableName, String xpathName, String txt)
    {
        xpathName = "//*[@data-e2e='"+tableName+"']/"+".//th[@data-e2e='"+xpathName+"']";
        proxy.checkText($x(xpathName), txt);
    }

    public void clickHeader(String tableName, String xpathName)
    {
        xpathName = "//*[@data-e2e='"+tableName+"']/"+".//th[@data-e2e='"+xpathName+"']";
        proxy.clickElement($x(xpathName));
    }
}
