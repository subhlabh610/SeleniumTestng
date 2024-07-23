package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonMethods;

public class FindBranchesPage {
    private final WebDriver driver;

    public FindBranchesPage(WebDriver driver) {
        this.driver = driver;
    }

    By findBranchHeading = By.xpath("//h1[@class='Locator-title' and contains(text(),'Find a Branch')]");
    By searchBranch=By.xpath("//input[@placeholder='Enter your search']");
    By searchIcon= By.xpath("//div[@class='Locator-buttons']");
    By firstSearchedBranch =By.xpath("//ol[@class='ResultList']/li//a[@class='Teaser-titleLink']");
    By branchName=By.xpath("//span[@class='LocationName-geo']");
    By branchAddress=By.xpath("//div[@class='Core-desktopAddress']//span[@class='c-address-street-1']");

    public void waitForBranchPageToLoad(){
        try {
            CommonMethods.waitForObject(driver.findElement(findBranchHeading), 10);
        }catch(Exception e){
            System.out.println("Object is visible");
        }
    }

    public void enterPostalCode(String postalCode){
        driver.findElement(searchBranch).click();
        CommonMethods.forcedWait(1000);
        driver.findElement(searchBranch).sendKeys(postalCode);
        CommonMethods.downArrowMouseAction(driver, driver.findElement(searchBranch));
    }

    public void clickOnSearch(){
        driver.findElement(searchIcon).click();
        CommonMethods.forcedWait(5000);
    }

    public void clickOnFirstSearch(){
        CommonMethods.waitForObject(driver.findElement(firstSearchedBranch),5000);
        driver.findElement(firstSearchedBranch).click();
    }

    public String fetchBranchName(){
        return driver.findElement(branchName).getText();
    }

    public String fetchBranchAddressLine1(){
        return driver.findElement(branchAddress).getText();
    }

}
