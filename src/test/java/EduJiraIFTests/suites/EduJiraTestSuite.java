package EduJiraIFTests.suites;

import EduJiraIFTests.pages.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginTest.class,
        DashboardPageTest.class,
        ProjectsPageTest.class,
        BrowsePageTest.class,
        CreateNewIssuePageTest.class
})
public class EduJiraTestSuite {
}
