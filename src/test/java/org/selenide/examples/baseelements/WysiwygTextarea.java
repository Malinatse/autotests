package org.selenide.examples.baseelements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

/**
 * Объект для взаимодействия с многострочным полем ввода текста
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class WysiwygTextarea extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "div[2]/div/div")
    private SelenideElement textArea;

    @FindBy(how = How.XPATH, xpath = "div/div/div")
    private SelenideElement textAreaSQ;

    @FindBy(how = How.XPATH, xpath = "div[1]/label")
    private SelenideElement textHeader;

    @FindBy(how = How.XPATH, xpath = "div[1]/div/button[1]")
    private SelenideElement textBoldBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div/button[2]")
    private SelenideElement textItalicBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div/button[3]")
    private SelenideElement textUnderlinedBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div/button[4]")
    private SelenideElement textBulletListBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div/button[5]")
    private SelenideElement textOrderedListBtn;

    @FindBy(how = How.XPATH, xpath = "div[1]/div/button[6]")
    private SelenideElement textLinkBtn;

    /**
     * Установка текста многострочному полю
     * @param txt устанавливаемый текст
     * @param enterPress признак необходимости нажатия клавиши enter после ввода
     */
    public void setMultilinedText(String txt, Boolean enterPress)
    {
        proxy.setText(textArea, txt, enterPress);
    }

    /**
     * Установка текста многострочному полю после очистки его содержимого
     * @param txt устанавливаемый текст
     * @param enterPress признак необходимости нажатия клавиши enter после ввода
     */
    public void setClearMultilinedText(String txt, Boolean enterPress)
    {
        proxy.setClearText(textArea, txt, enterPress);
    }

    public void setClearMultilinedTextSQ(String txt, Boolean enterPress)
    {
        proxy.setClearText(textAreaSQ, txt, enterPress);
    }

    /**
     * Получение текста у многострочного поля
     * @return String
     */
    public String getMultilinedText()
    {
        return proxy.getText(textArea);
    }

    /**
     * Проверка текста многострочног поля
     * @param txt проверяемый текст
     */
    public void checkMultilinedText(String txt)
    {
        proxy.checkText(textArea, txt);
    }

    /**
     * Выделение текста
     * @param startIndex индекс начала выделения
     * @param endIndex индекс конца выделения
     */
    public void selectText(Integer startIndex, Integer endIndex)
    {
        //TODO: find, how to select text without of using .SendKeys()
    }

    /**
     * Получение заголовка текстового многострочного поля
     * @return String
     */
    public String getHeader()
    {
        return proxy.getText(textHeader);
    }

    /**
     * Проверка заголовка текстового многострочного поля
     * @param txt проверяемый текст
     */
    public void checkHeader(String txt)
    {
        proxy.checkText(textHeader, txt);
    }

    /**
     * Установка жирного шрифта выбранносу тексту
     */
    public void clickBold()
    {
        proxy.clickElement(textBoldBtn);
    }

    /**
     * Установка наклонного шрифта выбранносу тексту
     */
    public void clickItalic()
    {
        proxy.clickElement(textItalicBtn);
    }

    /**
     * Установка подчёркнутого шрифта выбранному тексту
     */
    public void clickUnderLined()
    {
        proxy.clickElement(textUnderlinedBtn);
    }

    /**
     * Установка режима ввода текста в виде списка
     */
    public void clickBulletList()
    {
        proxy.clickElement(textBulletListBtn);
    }

    /**
     * Установка режима ввода текста в виде упорядоченного списка
     */
    public void clickOrderedList()
    {
        proxy.clickElement(textOrderedListBtn);
    }
}
