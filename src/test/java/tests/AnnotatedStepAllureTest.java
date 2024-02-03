package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.RepoData;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import pages.RepoListPage;

public class AnnotatedStepAllureTest {
    @Test
    @Feature("Issue in repo")
    @Story("Search for issue in certain repo")
    @Owner("azavialov")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("It is possible to search for an issue in certain repo using annotated steps")
    void simpleAllureWithAnnotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        RepoListPage steps = new RepoListPage();

        steps.openMainPage();
        steps.searchForRepository(RepoData.repositoryName);
        steps.selectRepositoryByLinkText(RepoData.repositoryName);
        steps.openIssuesTab();
        steps.checkIfIssueExists(RepoData.issueText);
        steps.takeScreenshot();
    }

}
