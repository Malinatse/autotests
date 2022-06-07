package org.selenide.examples.testdriver.elements.previewtables;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.containers.vgtables.reviewparticipants.VGTable;
import org.selenide.examples.testdriver.elements.AbsWrappable;

import java.util.List;

/**
 * Объект для взяимодействия с Review Grid стади
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class PreviewTablesLayout extends AbsWrappable {

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='previewTable']")
    public List<VGTable> previewTables;

}
