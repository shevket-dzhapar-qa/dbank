package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage{


    public CreateCheckingPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id="Standard Checking")
    private WebElement standardCheckingAccountTypeRadioButton;

    @FindBy(id="Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id="Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id="Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id="name")
    private WebElement accountNameTxt;

    @FindBy(id="openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id="newCheckingSubmit")
    private WebElement submitBtn;


    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {

        NewCheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);

        //the user clicks on checking button
        checkingMenu.click();

        //the user clicks on new checking button
        newCheckingButton.click();


        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"), getDriver().getCurrentUrl(), "Error message");

        //the user selects account type
        if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingAccountTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")) {
            interestCheckingAccountTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account type option. Only supports Standard Checking and Interest Checking.");
        }

        if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualOwnershipTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointOwnershipTypeRadioButton.click();

        } else {
            throw new NoSuchElementException("Invalid ownership type option.Only supports Individual Checking and Joint Checking.");
        }


        //the user gives name the account
        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());


        //the user makes the initial deposit
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));


        //the user clicks on submit
        submitBtn.click();

    }
}
