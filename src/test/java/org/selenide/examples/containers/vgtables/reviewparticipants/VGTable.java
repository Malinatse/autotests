package org.selenide.examples.containers.vgtables.reviewparticipants;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Контейнер элементов таблицы Review Participants
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGTable extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "div[1]/div/div")
    public VGSearch searchElement;

    @FindBy(how = How.XPATH, xpath = ".//table/thead/tr")
    public VGHeaders headers;

    @FindBy(how = How.XPATH, xpath = ".//table/tbody")
    public VGRows rows;

    @FindBy(how = How.XPATH, xpath = "div[2]/div/div[4]")
    public VGPagination pagination;
}
