package pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;

public class RepoListPage {

    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com");
        attachment("Source", webdriver().driver().source());
    }

    @Step("Search for a {repoName} repository")
    public void searchForRepository(String repoName) {
        $(".header-search-button").shouldBe(interactable).click();
        $("#query-builder-test").setValue(repoName).pressEnter();
    }

    @Step("Select repo by link text = {text}")
    public void selectRepositoryByLinkText(String text) {
        $(byLinkText(text)).click();
    }

    @Step("Open issues tab for a repo")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check if issue {issueText} is in the repo")
    public void checkIfIssueExists(String issueText) {
        $(byText(issueText)).shouldBe(interactable);
    }

    @Step("Take screenshot")
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
