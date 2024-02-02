package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.RepoData;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class LambdaStepsAllureTest extends TestBase{

    @Test
    @Feature("Issue in repo")
    @Story("Search for issue in certain repo")
    @Owner("azavialov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("It is possible to search for an issue in certain repo using simple lambda steps")
    void simpleAllureWithLambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Github main page", ()-> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });

        step("Search for " + RepoData.repositoryName + " repository", ()-> {
            $(".header-search-button").shouldBe(interactable).click();
            $("#query-builder-test").setValue(RepoData.repositoryName).pressEnter();
        });

        step("Select " + RepoData.repositoryName + " repository", ()-> {
            $(byLinkText(RepoData.repositoryName)).click();
        });

        step("Open issues tab", ()-> {
            $("#issues-tab").click();

        });

        step("Check if "+RepoData.issueText + " is in the list", ()-> {
            $(byText(RepoData.issueText)).shouldBe(interactable);
        });




    }
}
