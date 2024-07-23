import factory.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.PropReader;

import java.util.Properties;

public class hooks {

    private  WebDriver driver;
    Properties prop;


    @BeforeClass
    public void launchBrowser(){
        prop= PropReader.initProp();
        driver=DriverFactory.getDriverInstance();
        driver.get(prop.getProperty("test.url"));
    }

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }

}
