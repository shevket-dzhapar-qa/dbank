//package co.wedevx.digitalbank.automation.ui.steps;

//public class Hooks {
//    public Hooks() {
//
//    }
//
//    WebDriver driver = Driver.getDriver();
//
//    @Before("not @Registration")
//    public void the_user_on_dbank_homepage() {
//
//        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
//    }
//
//    @BeforeAll()
//    public static void establishConnectionToDB() {
//        DBUtils.establishConnection();
//
//    }
//
//
//    @After()
//    public void afterEachScenario(Scenario scenario) {
//        Driver.takesScreenShot(scenario);
//        Driver.closeDriver();
//    }
//
//    @AfterAll()
//    public static void closeConnectionToDB() {
//        DBUtils.closeConnection();
//    }
//}

package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @BeforeAll()
    public static void establishConnectionToDB() {
        DBUtils.establishConnection();
    }

    @Before("not @Registration")
    public void the_user_on_dbank_homepage() {
        WebDriver driver = Driver.getDriver(); // Получаем драйвер внутри метода
        driver.get("https://dbank-qa.wedevx.co/bank/login");
    }

    @After()
    public void afterEachScenario(Scenario scenario) {
        Driver.takesScreenShot(scenario);
        Driver.closeDriver();
    }

    @AfterAll()
    public static void closeConnectionToDB() {
        DBUtils.closeConnection();
    }
}


//
//import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
//import co.wedevx.digitalbank.automation.ui.utils.Driver;
//import io.cucumber.java.*;
//import org.openqa.selenium.WebDriver;
//
//public class Hooks {
//    public Hooks() {
//    }
//
//    WebDriver driver = Driver.getDriver();
//
//    @Before("not @Registration")
//    public void the_user_is_on_dbank_homepage() {
//        driver.get("https://dbank-qa.wedevx.co/bank/login");
//    }
//
//    @BeforeAll()
//    public static void establishConnectionToDB() {
//        DBUtils.establishConnection();
//    }
//
//    @After()
//    public void afterEachScenario(Scenario scenario) {
//        Driver.takesScreenShot(scenario);
//        Driver.closeDriver();
//    }
//
//    @AfterAll()
//    public static void closeConnectionToDB() {
//        DBUtils.closeConnection();
//    }
//}




//MY ORIGINAL
//package co.wedevx.digitalbank.automation.ui.steps;
//
//        import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
//        import co.wedevx.digitalbank.automation.ui.utils.Driver;
//        import io.cucumber.java.*;
//
//        import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
//
//public class Hooks {
//
//    @Before("@Registration")
//    public void establishConnectionToDB() {
//        DBUtils.establishConnection();
//
//    }
//
//    @Before() //"not @Registration"
//    public void the_user_on_dbank_homepage() {
//
//        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
//    }
//
//    @After() //"not @NegativeRegistrationCases"
//    public void afterEachScenario(Scenario scenario) {
//        Driver.takesScreenShot(scenario);
//        Driver.closeDriver();
//    }
//
//    @After()
//    public  void closeConnectionToDB() {
//        DBUtils.closeConnection();
//    }
//}