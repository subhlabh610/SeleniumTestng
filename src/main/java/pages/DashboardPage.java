package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.CommonMethods;

public class DashboardPage {

    private final WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    By bankLogo = By.xpath("//li[@class='top-header-logo']//span[contains(text(),'Lloyds Bank Logo')]");
    By backToWebSite= By.xpath("//span[contains(text(),'Link to main website')]//parent::a");
    By menuIcon =By.cssSelector("span.menu-button-text");
    By findBranches = By.xpath("//span[contains(text(),'Branch Finder')]");

    public void waitForObjectToLoad(){
        try {
            CommonMethods.waitForObject(driver.findElement(bankLogo), 10);
        }catch(Exception e){
            System.out.println("Object is visible");
        }
    }

    public void clickOnBankLogo(){
        driver.findElement(backToWebSite).click();
        CommonMethods.forcedWait(5000);
        CommonMethods.waitForObject(driver.findElement(menuIcon), 10);
    }

    public void navigateToBranchFinder(){
        driver.findElement(menuIcon).click();
        CommonMethods.forcedWait(5000);
        // Use JavaScriptExecutor to scroll to the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(findBranches));
        CommonMethods.forcedWait(5000);
        driver.findElement(findBranches).click();
    }


}


