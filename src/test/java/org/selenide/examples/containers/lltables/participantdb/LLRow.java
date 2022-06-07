package org.selenide.examples.containers.lltables.participantdb;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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

    public void clickCheckBox(String tableName, int rowId)
    {
        rowId = rowId+1;
        String xpathName = "(//*[@data-e2e='"+tableName+"']/"+".//*[@data-e2e='rowCheckBox'])"+"["+rowId+"]";
        proxy.clickElement($x(xpathName));
    }


}
