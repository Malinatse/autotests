package org.selenide.examples.dynamicproxy;

import com.codeborne.selenide.SelenideElement;

/**
 * Интерфейс для взаимодействия со стандартными объектами web страницы
 */
public interface SeleniumWrapper {
    /**
     * Получение текста указанного элемента
     * @param element элемент web страницы, текст которого необходимо получить
     * @return String res - текст указанного элемента
     */
    String getText(SelenideElement element);

    /**
     * Выполнение клика по web элементу
     * @param element элемент web страницы, по которому необходимо кликнуть
     */
    void clickElement(SelenideElement element);

    void clickElementIfExist(SelenideElement element);

    void clickElementAt(SelenideElement element, int x, int y);

    /**
     * Установка web элементу текста
     * @param element элемент web страницы, которому необходимо присвоить текст
     * @param txt устанавливаемый текст
     */
    void setText(SelenideElement element, String txt, Boolean enterPress);

    /**
     * Проверка наличия заданного текста у web элемента
     * @param element элемент web страницы, для которого проверяется наличие текста
     * @param txt искомый текст
     */
    void checkText(SelenideElement element, String txt);

    /**
     * Проверка наличия заданного текста у web элемента Input
     *
     * @param element элемент web страницы, для которого проверяется наличие текста
     * @param txt     искомый текст
     */
    void checkTextFromInput(SelenideElement element, String txt);

    /**
     * Проверка отсутствия заданного текста у web элемента Input
     *
     * @param element элемент web страницы, для которого проверяется наличие текста
     * @param txt     искомый текст
     */
    void checkNoTextFromInput(SelenideElement element, String txt);

    /**
     * Установка заданного текста у web элемента Input
     *
     * @param element    элемент web страницы, для которого устанавливается текст
     * @param txt        устанавливаемый текст
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    void setTextForInput(SelenideElement element, String txt, Boolean enterPress);

    /**
     * Установка заданного текста у web элемента Input с предварительной очисткой содержимого
     * @param element элемент web страницы, для которого устанавливается текст
     * @param txt устанавливаемый текст
     * @param enterPress признак необходимости нажатия клавиши Enter
     */
    void setClearTextForInput(SelenideElement element, String txt, Boolean enterPress);

    /**
     * Установка web элементу текста и предварительная очистка его содержимого
     * @param element элемент web страницы, которому необходимо присвоить текст
     * @param txt устанавливаемый текст
     */
    void setClearText(SelenideElement element, String txt, Boolean enterPress);

    /**
     * Установка выбранного значения элемента select по заданному тексту
     * @param element изменяемый select
     * @param txt текст устанавливаемой опции
     */
    void setSelectByText(SelenideElement element, String txt);

    /**
     * Проверка видимости элемента
     * @param element проверямый элемент
     * @param visibility флаг видимости
     */
    void checkVisibility(SelenideElement element, Boolean visibility);

    /**
     * Получение видимости элемента
     * @param element проверямый элемент
     * @return
     */
    Boolean getVisibility(SelenideElement element);

    /**
     * Проверка существования элемента
     * @param element проверямый элемент
     * @param existence флаг видимости
     */
    void checkExistence(SelenideElement element, Boolean existence);

//    /**
//     * Проверка наличия заданного текста у web элемента в списке
//     * @param elements элементы web страницы, для которыч проверяется наличие текста
//     * @param args искомый текст
//     */
//    public void checkTextList(List<?> elements, Method m, Object[] args);

}
