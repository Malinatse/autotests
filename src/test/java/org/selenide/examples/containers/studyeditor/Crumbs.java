package org.selenide.examples.containers.studyeditor;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.PWA.pages.ControlPanelPagePWA;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ControlPanelPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class Crumbs extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//a")
    private List<SelenideElement> crumbsLinks;

    /**
     * Клик по кнопке Home
     * @return ControlPanelPage
     */
    public ControlPanelPage clickHome()
    {
        $x("//body").scrollTo();
        proxy.clickElement(crumbsLinks.get(0));
        return page(ControlPanelPage.class);
    }
}
