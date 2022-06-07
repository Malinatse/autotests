package org.selenide.examples.containers;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.containers.cards.DraftStudyContainer;
import org.selenide.examples.containers.cards.LiveStudyContainer;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import static com.codeborne.selenide.Selenide.page;

public class ExpandablePanel extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.CLASS_NAME, using  = "StudiesList")
    public DraftStudyContainer studiesDraftContainer;

    @FindBy(how = How.CLASS_NAME, using  = "StudiesList")
    public LiveStudyContainer studiesLiveContainer;

}
