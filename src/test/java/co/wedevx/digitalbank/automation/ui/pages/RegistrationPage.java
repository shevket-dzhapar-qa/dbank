package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage{

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    MockData mockData = new MockData();

    @FindBy(id = "title")
    private WebElement titleDropDown;

    @FindBy(id = "firstName")
    private WebElement firstNameTbx;

    @FindBy(id = "lastName")
    private WebElement lastNameTbx;

    @FindBy(xpath = "//label[@for='male']//input")
    private WebElement genderMRadioBtn;

    @FindBy(xpath = "//label[@for='female']//input")
    private WebElement genderFRadioBtn;

    @FindBy(id = "dob")
    private WebElement dobTbx;

    @FindBy(id = "ssn")
    private WebElement ssnTbx;

    @FindBy(id = "emailAddress")
    private WebElement emailAddressTbx;

    @FindBy(id = "password")
    private WebElement passwordTbx;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordTbx;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']")
    private WebElement registerBtn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextBtn;

    @FindBy(id = "address")
    private WebElement addressTbx;

    @FindBy(id = "locality")
    private WebElement localityTbx;

    @FindBy(id = "region")
    private WebElement regionTbx;

    @FindBy(id = "postalCode")
    private WebElement postalCodeTbx;

    @FindBy(id = "country")
    private WebElement countryTbx;

    @FindBy(id = "homePhone")
    private WebElement homePhoneTbx;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTbx;

    @FindBy(id = "workPhone")
    private WebElement workPhoneTbx;

    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckBox;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement messageLabel;
    public void fillOutRegistrationForm(List<Map<String, String>> registrationPageTestDataListOfMap) {

        Select titleSelect = new Select(titleDropDown);
        Map<String, String> firstRow = registrationPageTestDataListOfMap.get(0);

        if(firstRow.get("title") != null) {
            titleSelect.selectByVisibleText(firstRow.get("title"));
        }

        if(firstRow.get("firstName") != null) {
            firstNameTbx.sendKeys(firstRow.get("firstName"));
        }

        if(firstRow.get("lastName") != null) {
            lastNameTbx.sendKeys(firstRow.get("lastName"));
        }


        if(firstRow.get("gender") != null) {
            if (firstRow.get("gender").equalsIgnoreCase("M")) {
                genderMRadioBtn.click();
            } else if (firstRow.get("gender").equalsIgnoreCase("F")) {
                genderFRadioBtn.click();
            } else {
                System.out.println("Wrong Gender");
            }
        }

        if(firstRow.get("dob") != null) {
            dobTbx.sendKeys(firstRow.get("dob"));
        }

        if(firstRow.get("ssn") != null) {
            ssnTbx.sendKeys(firstRow.get("ssn"));
        }

        if (firstRow.get("email") != null) {

            emailAddressTbx.sendKeys(firstRow.get("email"));
        }

        if(firstRow.get("password") != null) {
            passwordTbx.sendKeys(firstRow.get("password"));
            confirmPasswordTbx.sendKeys(firstRow.get("password"));
        }
        nextBtn.click();

        if (addressTbx.isDisplayed()) {

            if (firstRow.get("password") != null) {
                addressTbx.sendKeys(firstRow.get("address"));
            }

            if (firstRow.get("locality") != null) {
                localityTbx.sendKeys(firstRow.get("locality"));
            }

            if (firstRow.get("region") != null) {
                regionTbx.sendKeys(firstRow.get("region"));
            }

            if (firstRow.get("postalCode") != null) {
                postalCodeTbx.sendKeys(firstRow.get("postalCode"));
            }

            if (firstRow.get("country") != null) {
                countryTbx.sendKeys(firstRow.get("country"));
            }

            if (firstRow.get("homePhone") != null) {
                homePhoneTbx.sendKeys(firstRow.get("homePhone"));
            }

            if (firstRow.get("mobilePhone") != null) {
                mobilePhoneTbx.sendKeys(firstRow.get("mobilePhone"));
            }

            if (firstRow.get("workPhone") != null) {
                workPhoneTbx.sendKeys(firstRow.get("workPhone"));
            }

            if (firstRow.get("termsCheckMark") != null) {
                if (firstRow.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsCheckBox.click();
                }
            }

            registerBtn.click();
        }
    }

    public  String getMessage() {
        return messageLabel.getText().substring(0, messageLabel.getText().lastIndexOf("."));
    }

    public String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "title":
                return titleDropDown.getAttribute("validationMessage");
            case "firstname":
                return firstNameTbx.getAttribute("validationMessage");
            case "lastname":
                return lastNameTbx.getAttribute("validationMessage");
            case "gender":
                return genderMRadioBtn.getAttribute("validationMessage");
            case "dob":
                return dobTbx.getAttribute("validationMessage");
            case "ssn":
                return ssnTbx.getAttribute("validationMessage");
            case "password":
                return passwordTbx.getAttribute("validationMessage");
            case "address":
                return addressTbx.getAttribute("validationMessage");
            case "locality":
                return localityTbx.getAttribute("validationMessage");
            case "region":
                return regionTbx.getAttribute("validationMessage");
            case "postalcode":
                return postalCodeTbx.getAttribute("validationMessage");
            case "country":
                return countryTbx.getAttribute("validationMessage");
            case "homephone":
                return homePhoneTbx.getAttribute("validationMessage");
            case "mobilephone":
                return mobilePhoneTbx.getAttribute("validationMessage");
            case "workphone":
                return workPhoneTbx.getAttribute("validationMessage");
            case "termscheckmark":
                return agreeTermsCheckBox.getAttribute("validationMessage");
            default:
                return null;


        }
    }
}
