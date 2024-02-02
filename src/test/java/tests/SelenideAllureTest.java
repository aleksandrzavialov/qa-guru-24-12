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

public class SelenideAllureTest extends TestBase{

    @Test
    @Feature("Issue in repo")
    @Story("Search for issue in certain repo")
    @Owner("azavialov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("It is possible to search for an issue in certain repo using simple link with Selenide")
    void simpleAllureWithSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-button").shouldBe(interactable).click();
        $("#query-builder-test").setValue(RepoData.repositoryName).pressEnter();
        $(byLinkText(RepoData.repositoryName)).click();
        $("#issues-tab").click();
        $(byText("issue_to_test_allure_report")).shouldBe(interactable);

    }
}
