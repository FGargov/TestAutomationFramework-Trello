package test.cases.trello;

import org.junit.*;
import org.junit.runners.MethodSorters;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardTest extends BaseTest {
    private BoardPage boardPage;
    private BoardsPage boardsPage;

    @Before
    public void pagesSetup() {
        boardPage = new BoardPage(actions.getDriver());
        boardsPage = new BoardsPage(actions.getDriver());
    }

    @Test
    public void test1_createBoardWhenCreateBoardClicked() {
        login();

        boardsPage.createBoard();

        boardPage.assertAddListExists();
    }

    @Test
    public void test2_createNewCardInExistingBoardWhenCreateCardClicked() {
        login();

        boardPage.openBoard();
        boardPage.createList();

        String cardName = getUIMappingByKey("trello.cardName");
        boardPage.addCardToList(cardName);

        String firstListName = getUIMappingByKey("trello.listName");
        boardPage.assertCardExistInList(cardName, firstListName);
    }

    @Test
    public void test3_moveCardBetweenStatesWhenDragAndDropIsUsed() {
        login();

        boardPage.openBoard();
        boardPage.createList();
        String cardName = getUIMappingByKey("trello.cardName");
        boardPage.addCardToList(cardName);

        String targetListName = getUIMappingByKey("trello.targetListName");
        String firstListName = getUIMappingByKey("trello.firstListName");

        boardPage.moveCardToList(cardName, targetListName);

        boardPage.assertCardInList(cardName, targetListName);
        boardPage.assertCardNotInList(firstListName, cardName);
    }

    @Test
    public void test4_deleteBoardWhenDeleteButtonIsClicked() {
        login();

        boardPage.openBoard();

        boardPage.deleteBoard();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
    }
}
