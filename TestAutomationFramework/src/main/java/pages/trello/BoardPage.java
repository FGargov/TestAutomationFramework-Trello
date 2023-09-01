package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void createList() {
        String listName = getUIMappingByKey("trello.listName");

        actions.waitForElementClickable("trello.boardPage.addNewList");
        actions.clickElement("trello.boardPage.addNewList");

        actions.waitForElementClickable("trello.boardPage.listTitleInput");
        actions.clickElement("trello.boardPage.listTitleInput");
        actions.typeValueInField(listName, "trello.boardPage.listTitleInput");

        actions.clickElement("trello.createList.button");
    }

    public void addCardToList(String cardName) {
        actions.waitForElementVisible("trello.createCard.addNewCard.Button");
        actions.clickElement("trello.createCard.addNewCard.Button");
        actions.waitForElementVisible("trello.createCard.titleInput");
        actions.typeValueInField(cardName, "trello.createCard.titleInput");
        actions.clickElement("trello.createCard.submitButton");
    }

    public void moveCardToList(String cardName, String listName) {
        actions.waitForElementVisible("trello.boardPage.cardByName", cardName);
        WebElement card = driver.findElement(By.xpath(String.format("//span[@class='list-card-title js-card-name' and text()='%s']", cardName)));
        WebElement destination = driver.findElement(By.xpath(String.format("//div[@class='list js-list-content' and (descendant::h2[text()='%s'])]", listName)));
        Actions actionsOne = new Actions(actions.getDriver());
        actionsOne.clickAndHold(card).moveToElement(destination).release().perform();
    }

    public void assertCardInList(String cardName, String listName) {
        actions.waitForElementPresent("trello.checkIfCard.IsInList", cardName, listName);
    }

    public void assertCardNotInList(String listName, String cardName) {
        actions.waitForElementPresent("trello.checkIfCard.NotInList", listName, cardName);
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertAddListExists() {
        actions.waitForElementPresent("trello.boardPage.listWrapper");
    }
    public void assertCardExistInList(String cardName, String listName) {
        actions.waitForElementPresent("trello.boardPage.lastCreatedCard.IsInList", cardName, listName);
    }

    public void clickOnBoard(String boardName) {
        actions.waitForElementVisible("trello.boardsPage.boardByTeamAndName", boardName);
        actions.clickElement("trello.boardsPage.boardByTeamAndName", boardName);
    }

    public void openBoard() {
        String boardName = getUIMappingByKey("trello.boardName");

        actions.waitForElementVisible("trello.boardsPage.boardByTeamAndName", boardName);
        actions.waitForElementClickable("trello.boardsPage.boardByTeamAndName", boardName);
        actions.clickElement("trello.boardsPage.boardByTeamAndName", boardName);
    }



    public void deleteBoard() {
        actions.waitForElementPresent("trello.deleteBoard.showBoardMenu");
        actions.clickElement("trello.deleteBoard.showBoardMenu");

        actions.waitForElementVisible("trello.deleteBoard.closeBoardLink");
        actions.clickElement("trello.deleteBoard.closeBoardLink");
        actions.clickElement("trello.deleteBoard.closeBoardConfirmLink");

        actions.waitForElementVisible("trello.deleteBoard.permanentlyDeleteLink");
        actions.clickElement("trello.deleteBoard.permanentlyDeleteLink");
        actions.clickElement("trello.deleteBoard.confirmPermanentlyDeleteButton");
    }
}
