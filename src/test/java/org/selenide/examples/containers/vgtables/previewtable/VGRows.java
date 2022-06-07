package org.selenide.examples.containers.vgtables.previewtable;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Объект для взаимодействия с записями таблицы User Management
 *
 * @author Fedosov I.
 * @version 1.0
 * @since 1.0
 */
public class VGRows extends ElementsContainer {
    @FindBy(how = How.XPATH, xpath = "tr")
    private List<VGRow> tableRows;

    /**
     * Получение текстового значения указанной ячейки
     *
     * @param rowId искомая строка
     * @param field искомый столбец
     * @return String result
     */
    public String getTextCellData(Integer rowId, VGTable.TableFields field) {
        if ((rowId < 0) || (rowId > tableRows.size())) {
            //TODO:
            // Raise exception
        }
        VGRow row;
        String retString;
        switch (field) {
            case PARTICIPANTS:
                row = tableRows.get(rowId);
                return row.getParticipants();
            case GROUPS:
                row = tableRows.get(rowId);
                return row.getGroups();
            case MODERATOR:
                row = tableRows.get(rowId);
                return row.getModerator();
            case POSTS:
                row = tableRows.get(rowId);
                return row.getPosts();
            case GROUP_PICS:
                row = tableRows.get(rowId);
                return row.getGroupPics();
            case GROUP_VIDEOS:
                row = tableRows.get(rowId);
                return row.getGroupVideos();
            case COMPLETED_QUESTIONS:
                row = tableRows.get(rowId);
                return row.getCompletedQuestions();
            default:
                return "Table field does not exists";

        }
    }

    /**
     * Проверка текстового значения указанной ячейки
     *
     * @param rowId искомая строка
     * @param field искомый столбец
     * @param txt   искомый текст
     */
    public void checkTextCellData(Integer rowId, VGTable.TableFields field, String txt) {
        if ((rowId < 0) || (rowId > tableRows.size())) {
            //TODO:
            // Raise exception
        }
        VGRow row;
        switch (field) {
            case PARTICIPANTS:
                row = tableRows.get(rowId);
                row.checkParticipants(txt);
                break;
            case GROUPS:
                row = tableRows.get(rowId);
                row.checkGroups(txt);
                break;
            case MODERATOR:
                row = tableRows.get(rowId);
                row.checkModerator(txt);
                break;
            case POSTS:
                row = tableRows.get(rowId);
                row.checkPosts(txt);
                break;
            case GROUP_PICS:
                row = tableRows.get(rowId);
                row.checkGroupPics(txt);
                break;
            case GROUP_VIDEOS:
                row = tableRows.get(rowId);
                row.checkGroupVideos(txt);
                break;
            case COMPLETED_QUESTIONS:
                row = tableRows.get(rowId);
                row.checkCompletedQuestions(txt);
                break;
            default:
                /*noting to do*/
                break;
        }
    }
}
