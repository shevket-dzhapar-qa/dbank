//package co.wedevx.digitalbank.automation.ui.steps.data_transformers;
//
//import co.wedevx.digitalbank.automation.ui.models.AccountCard;
//import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
//import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
//import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
//import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
//import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
//import co.wedevx.digitalbank.automation.ui.utils.Driver;
//import io.cucumber.java.After;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.WebDriver;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//
//public class CheckingAccountSteps {
//
//    WebDriver driver = Driver.getDriver();
//    private final LoginPage loginPage = new LoginPage(driver);
//    private final CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
//    private final ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);
//
//
//    @Given("The user is logged in as {string} {string}")
//    public void The_user_is_logged_in_as(String username, String password) {
//        loginPage.login(username, password);
//    }
//
//    @When ("the user creates a new checking account with the following data")
//    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
//        createCheckingPage.createNewChecking(checkingAccountInfoList);
//    }
//
//    @Then("the user should see the green {string} message")
//    public void the_user_should_see_the_green_message(String expectedConfMessage) {
//        expectedConfMessage = "Confirmation " + expectedConfMessage + "\n×";
//        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualConfirmationMessage());
//    }
//
//    @Then("the user should see newly added account card")
//    public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {
//        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();
//        AccountCard expectedResult = accountCardList.get(0);
//
//        assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
//        assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
//        assertEquals("Ownership: " + expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
//        assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));
//
//        String expectedBalance = String.format("%.2f", expectedResult.getBalance());
//        assertEquals("Balance: $" + expectedBalance, actualResultMap.get("actualBalance"));
//
//
//    }
//
//    @Then("the user should see the following transaction")
//    public void the_user_should_see_the_following_transaction(List<BankTransaction> expectedTransactions) {
//        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();
//
//        BankTransaction expectedTransaction = expectedTransactions.get(0);
//
//        assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"), "transaction category mismatch");
//       // assertEquals(expectedTransaction.getDescription(), actualResultMap.get("actualDescription"), "transaction description mismatch");
//        assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "transaction amount mismatch");
//        assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "transaction balance mismatch");
//    }
//
//
//
//    @After
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(3000);
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}

package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {

    WebDriver driver = Driver.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private final ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

    @Given("The user is logged in as {string} {string}")
    public void The_user_is_logged_in_as(String username, String password) {
        loginPage.login(username, password);
    }

    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        List<NewCheckingAccountInfo> checkingAccountInfoList = dataTable.asMaps(String.class, String.class).stream()
                .map(row -> new NewCheckingAccountInfo(
                        row.get("checkingAccountType"),
                        row.get("accountOwnership"),
                        row.get("accountName"),
                        Double.parseDouble(row.get("initialDepositAmount"))
                ))
                .collect(Collectors.toList());

        createCheckingPage.createNewChecking(checkingAccountInfoList);
    }

    @Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_message(String expectedConfMessage) {
        expectedConfMessage = "Confirmation " + expectedConfMessage + "\n×";
        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualConfirmationMessage());
    }

    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();
        Map<String, String> expectedResult = dataTable.asMaps(String.class, String.class).get(0);

        assertEquals(expectedResult.get("accountName"), actualResultMap.get("actualAccountName"));
        assertEquals("Account: " + expectedResult.get("accountType"), actualResultMap.get("actualAccountType"));
        assertEquals("Ownership: " + expectedResult.get("ownership"), actualResultMap.get("actualOwnership"));
        assertEquals("Interest Rate: " + expectedResult.get("interestRate"), actualResultMap.get("actualInterestRate"));

        String expectedBalance = String.format("%.2f", Double.parseDouble(expectedResult.get("balance")));
        assertEquals("Balance: $" + expectedBalance, actualResultMap.get("actualBalance"));
    }

    @Then("the user should see the following transaction")
    public void the_user_should_see_the_following_transaction(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();
        Map<String, String> expectedTransaction = dataTable.asMaps(String.class, String.class).get(0);

        assertEquals(expectedTransaction.get("category"), actualResultMap.get("actualCategory"), "transaction category mismatch");
       // assertEquals(expectedTransaction.get("description"), actualResultMap.get("actualDescription"), "transaction description mismatch");
        assertEquals(Double.parseDouble(expectedTransaction.get("amount")), Double.parseDouble(actualResultMap.get("actualAmount")), "transaction amount mismatch");
        assertEquals(Double.parseDouble(expectedTransaction.get("balance")), Double.parseDouble(actualResultMap.get("actualBalance")), "transaction balance mismatch");
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}
