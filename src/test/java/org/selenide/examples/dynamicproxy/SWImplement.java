package org.selenide.examples.dynamicproxy;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.selenide.examples.ConfProperties;

import java.time.Duration;

/**
 * Реализация стандартного взаимодействия с элементамии web страницы
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class SWImplement implements SeleniumWrapper{

    private final Integer defaultDurationSec = 30;

    @Override
    public Boolean getVisibility(SelenideElement element)
    {
        return element.isDisplayed();
    }

    @Override
    public String getText(SelenideElement element)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        String res = element.getText();
        return res;
    }

    @Override
    public void clickElement(SelenideElement element)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.shouldNotHave(Condition.attribute("disabled"));
        element.click();
    }

    @Override
    public void clickElementIfExist(SelenideElement element)
    {
        try {
            element.click();
        } catch (Exception ignored) { }
    }

    @Override
    public void clickElementAt(SelenideElement element, int x, int y)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.click();
        ClickOptions cl = ClickOptions.usingDefaultMethod();
        cl = cl.offset(x, y);
        element.click(cl);
    }

    @Override
    public void setText(SelenideElement element, String txt, Boolean enterPress)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.sendKeys(txt);
        if (enterPress)
            element.pressEnter();
    }

    @Override
    public void checkText(SelenideElement element, String txt)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.shouldHave(Condition.exactText(txt));
    }

    @Override
    public void setClearText(SelenideElement element, String txt, Boolean enterPress)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        if (ConfProperties.getProperty("OS").equals("MacOS")) {
            element.sendKeys(Keys.COMMAND + "a");
        } else {
            element.sendKeys(Keys.CONTROL + "a");
        }
        element.sendKeys(Keys.DELETE);
        element.sendKeys(txt);
        if (enterPress)
            element.pressEnter();
    }

    @Override
    public void setSelectByText(SelenideElement element, String txt)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.selectOptionContainingText(txt);
    }

    @Override
    public void checkVisibility(SelenideElement element, Boolean visibility)
    {
        if(visibility)
        {
            element.shouldBe(Condition.visible);
        }
        else
        {
            element.shouldNotBe(Condition.visible);
        }
    }

    @Override
    public void checkExistence(SelenideElement element, Boolean existence)
    {
        if(existence)
        {
            element.should(Condition.exist);
        }
        else
        {
            element.shouldNot(Condition.exist);
        }
    }

    @Override
    public void checkTextFromInput(SelenideElement element, String txt) {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.shouldHave(Condition.exactValue(txt));
    }

    @Override
    public void checkNoTextFromInput(SelenideElement element, String txt) {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.shouldNotHave(Condition.exactValue(txt));
    }

    @Override
    public void setTextForInput(SelenideElement element, String txt, Boolean enterPress) {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.setValue(txt);
        if (enterPress)
            element.click();
            element.sendKeys(Keys.ENTER);
    }

    @Override
    public void setClearTextForInput(SelenideElement element, String txt, Boolean enterPress)
    {
        element.should(Condition.appear, Duration.ofSeconds(defaultDurationSec));
        element.should(Condition.exist);
        element.should(Condition.visible);
        element.should(Condition.enabled);
        element.click();
        if (ConfProperties.getProperty("OS").equals("MacOS")) {
            element.sendKeys(Keys.COMMAND + "a");
        } else {
            element.sendKeys(Keys.CONTROL + "a");
        }
        element.sendKeys(Keys.DELETE);
        element.setValue(txt);
        if (enterPress)
            element.sendKeys(Keys.ENTER);
    }
//    @Override
//    public void checkTextList(List<T> elements, Method m, Object[] args)
//    {
////        if ((id < 0) || (id > elements.size()))
////        {
////            // TODO: 14.04.2021 Throw exception
////        }
//        Class<? extends Object> clazz = elements.get(0);
//        elements.getClass().getInterfaces()[0].getSimpleName();
////        Class<? extends Object> clazz = elements.get(id).getClass();
////        if (clazz == TextInput.class)
////        {
////            switch(fieldName) {
////                case "123":
////                    break;
////            }
////            TextInput elem = (TextInput)elements.get(id);
////            elem.checkInputError(txt);
////        }
//    }
}
