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
public class LLCustomElements extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void setSearch(String tableName, String txt, Boolean enterPress)
    {
        String xpathName = "(//*[@data-e2e='"+tableName+"']/"+".//*[@data-e2e='prospectTableHeaderSearchBar']/input)";
        proxy.setClearTextForInput($x(xpathName), txt, enterPress);
    }

    public void clickAddProspects(String tableName)
    {
        String xpathName = "(//*[@data-e2e='"+tableName+"']/"+".//*[@data-e2e='prospectTableHeaderAddBtn']/div/div)";
    }
}
