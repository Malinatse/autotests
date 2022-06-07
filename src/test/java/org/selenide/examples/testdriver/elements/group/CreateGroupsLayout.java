package org.selenide.examples.testdriver.elements.group;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.Button;
import org.selenide.examples.baseelements.TextProperty;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;

import java.util.List;

/**
 * Объект для взяимодействия со списком групп при редактировании стади
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class CreateGroupsLayout extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = "div//h3")
    public TextProperty groupHeader;

    @FindBy(how = How.XPATH, xpath = "div[1]/div[3]/svg")
    public Button showHideBtn;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='groupsList']/div")
    public List<GroupEditor> groupEditors;

    @FindBy(how = How.XPATH, xpath = ".//div[@data-e2e='groupsList']/button")
    public Button addGroupBtn;

    /**
     * Проверка загаловка группы с заданным порядковым нумером
     * @param txt проверяемый текст
     * @param id номер группы
     */
    public void checkGroupHeader(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupHeaderText.checkPropertyHeader(txt);
    }

    /**
     * Нажатие на загаловок группы с заданным порядковым нумером
     * @param id номер группы
     */
    public void clickGroupHeader(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupHeaderButton.clickButton();
    }

    /**
     * Проверка имени группы по заданному порядковому номеру
     * @param txt проверяемый текст
     * @param id номер группы
     */
    public void checkGroupName(Integer id, String txt)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupNameInput.checkInputValue(txt);
    }

    /**
     * Установка названия группы
     * @param txt новое название группы
     * @param id номер группы
     * @param enterPress признак необходимости нажать клавишу enter
     */
    public void setGroupName(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupNameInput.setInput(txt, enterPress);
    }

    /**
     * Установка названия группы после очистки содержимого текстового поля
     * @param txt новое название группы
     * @param id номер группы
     * @param enterPress признак необходимости нажать клавишу enter
     */
    public void setClearGroupName(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupNameInput.setClearInput(txt, enterPress);
    }

    /**
     * Установка подробностей для партисипантов
     * @param txt текст подробностей
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши enter
     */
    public void setDetailParticipants(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.setMultilinedText(txt, enterPress);
    }

    /**
     * Установка подробностей для партисипантов с предварительным очищением содержимого
     * @param txt текст подробностей
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши enter
     */
    public void setClearDetailParticipants(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.setClearMultilinedText(txt, enterPress);
    }

    /**
     * Получение подробностей для партисипантов
     * @param id номер группы
     * @return String
     */
    public String getDetailParticipants(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        return group.detailsForParticipants.getMultilinedText();
    }

    public void checkDetailParticipants(Integer id, String txt)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.checkMultilinedText(txt);
    }

    /**
     * Нажатие на кнопку Bold подробностей для партисипантов
     * @param id номер группы
     */
    public void clickDetailParticipantsBold(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.clickBold();
    }

    /**
     * Нажатие на кнопку Italic подробностей для партисипантов
     * @param id номер группы
     */
    public void clickDetailParticipantsItalic(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.clickItalic();
    }

    /**
     * Нажатие на кнопку Underlined подробностей для партисипантов
     * @param id номер группы
     */
    public void clickDetailParticipantsUnderlined(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.clickUnderLined();
    }

    /**
     * Нажатие на кнопку Bullet List подробностей для партисипантов
     * @param id номер группы
     */
    public void clickDetailParticipantsBullet(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.clickBulletList();
    }

    /**
     * Нажатие на кнопку Ordered List подробностей для партисипантов
     * @param id номер группы
     */
    public void clickDetailParticipantsOrdered(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.detailsForParticipants.clickOrderedList();
    }

    /**
     * Нажатие на чекбокс "Apply"
     * @param id номер группы
     */
    public void clickApply(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.applyForAllBox.clickCheckBox();
    }

    /**
     * Проверка кодового значения цвета
     * @param txt ожидаемое значение
     * @param id номер группы
     */
    public void checkColorCode(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.colorPicker.checkColorCode(txt);
    }

    /**
     * Получение кодового значения цвета
     * @param id номер группы
     * @return String
     */
    public String getColorCode(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        return group.colorPicker.getColorCode();
    }

    /**
     * Установка ползунка колорпикера в соответствующее значение
     * @param txt значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода значения
     */
    public void setRanBowValue(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.colorPicker.setRainbowValue(txt, enterPress);
    }

    /**
     * Установка описания группы
     * @param txt описание
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    public void setGroupDescription(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.setMultilinedText(txt, enterPress);
    }

    /**
     * Установка описания группы с предворительной очисткой содержимого
     * @param txt описание
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    public void setClearGroupDescription(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.setClearMultilinedText(txt, enterPress);
    }

    /**
     * Проверка текста описания группы
     * @param txt проверяемый текст
     * @param id номер группы
     */
    public void checkGroupDescription(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.checkMultilinedText(txt);
    }

    /**
     * Нажатие на кнопку Bold описания группы
     * @param id номер группы
     */
    public void clickDescriptionBold(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.clickBold();
    }

    /**
     * Нажатие на кнопку Italic описания группы
     * @param id номер группы
     */
    public void clickDescriptionItalic(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.clickItalic();
    }

    /**
     * Нажатие на кнопку Underlined описания группы
     * @param id номер группы
     */
    public void clickDescriptionUnderlined(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.clickUnderLined();
    }

    /**
     * Нажатие на кнопку Bullet List описания группы
     * @param id номер группы
     */
    public void clickDescriptionBullet(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.clickBulletList();
    }

    /**
     * Нажатие на кнопку Ordered List описания группы
     * @param id номер группы
     */
    public void clickDescriptionOrdered(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupDescription.clickOrderedList();
    }

    /**
     * Проверка количества пригшлашенных
     * @param txt количество
     * @param id номер группы
     */
    public void checkNumberOfParticipants(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupParticipants.checkInputValue(txt);
    }

    /**
     * Изменение количества приглашенных
     * @param txt устанавливаемое количество
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    public void setNumberOfParticipants(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupParticipants.setInput(txt, enterPress);
    }

    /**
     * Изменение количества приглашенных после предварительной очистки содержимого
     * @param txt устанавливаемое количество
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    public void setClearNumberOfParticipants(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupParticipants.setClearInput(txt, enterPress);
    }

    public void checkNumberOfParticipantsByGender(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupParticipantsByGender.checkInputValue(txt);
    }

    /**
     * Изменение количества приглашенных
     * @param txt устанавливаемое количество
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    public void setNumberOfParticipantsByGender(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupParticipantsByGender.setInput(txt, enterPress);
    }

    /**
     * Изменение количества приглашенных после предварительной очистки содержимого
     * @param txt устанавливаемое количество
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    public void setClearNumberOfParticipantsByGender(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.groupParticipantsByGender.setClearInput(txt, enterPress);
    }

    /**
     * Нажатие на чекбокс Gender Preference
     * @param id номер группы
     */
    public void clickGenderPreference(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.genderCheckBox.clickCheckBox();
    }

    /**
     * Установка количества мужчин
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setMaleCount(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.maleCount.setInput(txt, enterPress);
    }

    /**
     * Установка количества мужчин после очистки содержимого поля
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearMaleCount(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.maleCount.setClearInput(txt, enterPress);
    }

    /**
     * Проверка значения количества мужчин
     * @param txt устанавливаемое значение
     * @param id номер группы
     */
    public void checkMaleCount(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.maleCount.checkInputValue(txt);
    }

    /**
     * Установка количества женщин
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setFemaleCount(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.femaleCount.setInput(txt, enterPress);
    }

    /**
     * Установка количества женщин после очистки содержимого поля
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearFemaleCount(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.femaleCount.setClearInput(txt, enterPress);
    }

    /**
     * Проверка значения количества женщин
     * @param txt устанавливаемое значение
     * @param id номер группы
     */
    public void checkFemaleCount(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.femaleCount.checkInputValue(txt);
    }

    /**
     * Установка количества непонятно кого
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setNonBinaryCount(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.nonBinaryCount.setInput(txt, enterPress);
    }

    /**
     * Установка количества непонятно кого после очистки содержимого поля
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearNonBinaryCount(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.nonBinaryCount.setClearInput(txt, enterPress);
    }

    /**
     * Проверка значения количества непонятно кого
     * @param txt устанавливаемое значение
     * @param id номер группы
     */
    public void checkNonBinaryCount(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.nonBinaryCount.checkInputValue(txt);
    }

    /**
     * Нажатие на раскрывающийся список "More fields"
     * @param id номер группы
     */
    public void clickMoreFields(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.moreFieldsBtn.clickButton();
    }

    /**
     * Установка местонахождения
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setGeography(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.geography.setInput(txt, enterPress);
    }

    /**
     * Установка местонахождения после очистки содержимого поля
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearGeography(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.geography.setClearInput(txt, enterPress);
    }

    /**
     * Проверка значения местонахождения
     * @param txt устанавливаемое значение
     * @param id номер группы
     */
    public void checkGeography(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.geography.checkInputValue(txt);
    }

    /**
     * Установка присутствия детей дома
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setChildrenAtHome(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.childrenAtHome.setInput(txt, enterPress);
    }

    /**
     * Установка присутствия детей дома после очистки содержимого поля
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearChildrenAtHome(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.childrenAtHome.setClearInput(txt, enterPress);
    }

    /**
     * Проверка значения присутствия детей дома
     * @param txt устанавливаемое значение
     * @param id номер группы
     */
    public void checkChildrenAtHome(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.childrenAtHome.checkInputValue(txt);
    }

    /**
     * Установка комментариев для рекрутёра
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setCommentsForRecruiter(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.setMultilinedText(txt, enterPress);
    }

    /**
     * Установка комментариев для рекрутёра после очистки содержимого поля
     * @param txt устанавливаемое значение
     * @param id номер группы
     * @param enterPress признак необходимости нажатия клавиши Enter после ввода
     */
    public void setClearCommentsForRecruiter(String txt, Integer id, Boolean enterPress)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.setClearMultilinedText(txt, enterPress);
    }

    /**
     * Проверка значения комментариев для рекрутёра
     * @param txt устанавливаемое значение
     * @param id номер группы
     */
    public void checkCommentsForRecruiter(String txt, Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.checkMultilinedText(txt);
    }

    /**
     * Нажатие на кнопку Bold комментариев для рекрутёра
     * @param id номер группы
     */
    public void clickCommentsForRecruiterBold(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.clickBold();
    }

    /**
     * Нажатие на кнопку Italic комментариев для рекрутёра
     * @param id номер группы
     */
    public void clickCommentsForRecruiterItalic(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.clickItalic();
    }

    /**
     * Нажатие на кнопку Underlined комментариев для рекрутёра
     * @param id номер группы
     */
    public void clickCommentsForRecruiterUnderlined(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.clickUnderLined();
    }

    /**
     * Нажатие на кнопку Bullet List комментариев для рекрутёра
     * @param id номер группы
     */
    public void clickCommentsForRecruiterBullet(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.clickBulletList();
    }

    /**
     * Нажатие на кнопку Bold комментариев для рекрутёра
     * @param id номер группы
     */
    public void clickCommentsForRecruiterOrdered(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.commentsForRecruiter.clickOrderedList();
    }

    /**
     * Нажатие на кнопку сохранения группы
     * @param id номер группы
     */
    public void clickSaveGroup(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.saveBtn.clickButton();
    }

    /**
     * Нажатие на кнопку удаления группы
     * @param id номер группы
     */
    public void clickDeleteGroup(Integer id)
    {
        if ((id < 0) || (id > groupEditors.size()))
        {
            //TODO:
            // Raise exception
        }
        GroupEditor group = groupEditors.get(id);
        group.deleteBtn.clickButton();
    }
}
