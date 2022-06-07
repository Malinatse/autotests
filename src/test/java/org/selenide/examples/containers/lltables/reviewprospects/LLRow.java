package org.selenide.examples.containers.lltables.reviewprospects;

import com.codeborne.selenide.ElementsContainer;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.modals.ProspectDetailsModal;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Объект для взаимодействия с одним радом таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class LLRow extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    public void checkCell(String xpathName, String txt, int rowId)
    {
        rowId = rowId+1;
        xpathName = "(.//td[@data-e2e='"+xpathName+"'])"+"["+rowId+"]";
        proxy.checkText($x(xpathName), txt);
    }

    public void clickCell(String xpathName, int rowId)
    {
        rowId = rowId+1;
        xpathName = "(.//td[@data-e2e='"+xpathName+"'])"+"["+rowId+"]";
        proxy.clickElement($x(xpathName));
    }

    public void clickSelectorInCell(String xpathName, int rowId)
    {
        rowId = rowId+1;
        xpathName = "(.//*[@data-e2e='"+xpathName+"'])"+"["+rowId+"]";
        proxy.clickElement($x(xpathName));
    }

    public void clickName(String xpathName, int rowId)
    {
        rowId = rowId+1;
        xpathName = "(.//td[@data-e2e='name'])/div/div/button";
        proxy.clickElement($x(xpathName));
    }

    public void clickCheckBox(String tableName, int rowId)
    {
        rowId = rowId+1;
        String xpathName = "(//*[@data-e2e='"+tableName+"']/"+".//*[@data-e2e='rowCheckBox'])"+"["+rowId+"]";
        proxy.clickElement($x(xpathName));
    }


}
