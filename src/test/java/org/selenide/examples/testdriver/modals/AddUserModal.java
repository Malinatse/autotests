package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.DatePicker;
import org.selenide.examples.baseelements.SelectInput;
import org.selenide.examples.baseelements.TextInput;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.UserManagementPage;

import java.util.List;


/**
 * Объект для взаимодействия с модальным окном добавления пользователя
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class AddUserModal extends ElementsContainer {

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='userManagementEmail']")
    public TextInput mailInputs;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='roleField']")
    public List<SelectInput> roleSelects;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='roleField']/div")
    public TextInput roleSelectsInput;

    @FindBy(how = How.XPATH, xpath = "//div[@data-e2e='addUserDatePicker']")
    private List<DatePicker> datePickers;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='btnAddAnotherUser']")
    public Button addAnother;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='addUserBtnCancel']")
    public Button<UserManagementPage> cancel;

    @FindBy(how = How.XPATH, xpath = "//button[@data-e2e='addUserBtnInvite']")
    public Button<UserManagementPage> invite;

    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    /**
     * Проверка текста метки e-mail
     * @param id номер элемента
     * @param txt искомый текст
     */
    public void checkMailLabel(Integer id, String txt)
    {
        //TextInput inp = mailInputs.get(id);
       // inp.checkHeader(txt);
    }

    /**
     * Проверка текста сообщения об ошибке
     * @param id номер элемента
     * @param txt искомый текст
     */
    public void checkMailErrorText(Integer id, String txt)
    {
        //TextInput inp = mailInputs.get(id);
        //inp.checkError(txt);
    }


    /**
     * Установка поля email в заданное значение
     * @param id номер элемента
     * @param mailTxt значение email, которое нужно вписать в input
     * @param enterPress флаг нажатия клавиши Enter после ввода
     */
    public void setEmail(Integer id, String mailTxt, Boolean enterPress)
    {
        //TextInput inp = mailInputs.get(id);
        //inp.setInput(mailTxt, enterPress);
    }

    /**
     * Установка значения для поля role
     * @param id номер элемента
     * @param roleTxt новое значение. Работает автодополнение после нажатие Enter
     * @param enterPress флаг нажатия клавиши Enter после ввода
     */
    public void setRole(Integer id, String roleTxt, Boolean enterPress)
    {
        SelectInput sel = roleSelects.get(id);
        sel.setClearInput(roleTxt, enterPress);
    }

    /**
     * Установка даты срока действия
     * @param id номер элемента
     * @param expDateTxt новое значение даты
     * @param enterPress флаг нажатия клавиши Enter после ввода
     */
    public void setExpirationDate(Integer id, String expDateTxt, Boolean enterPress)
    {
        DatePicker dat = datePickers.get(id);
        dat.setClearInput(expDateTxt, enterPress);
    }

}
