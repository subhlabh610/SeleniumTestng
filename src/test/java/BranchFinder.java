import factory.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.FindBranchesPage;
import utils.CommonMethods;

import java.io.IOException;
import java.util.List;

@Slf4j
public class BranchFinder extends hooks{

    @DataProvider(name = "postalCodes")
    public Object[][] postalCodesProvider() throws IOException {
//        Change the below as per your system
        List<Object[]> data = CommonMethods.readDataFromExcel("C:\\Users\\Sonam\\Desktop\\Workspace\\SeleniumTestng\\src\\test\\resources\\TestData\\postalCode.xlsx");
        return data.toArray(new Object[0][]);
    }

    @Test(dataProvider = "postalCodes")
    public void testBranchFinderFunctionality(String postalCode){
        DashboardPage dashboardPage=new DashboardPage(DriverFactory.getDriverInstance());
        FindBranchesPage findBranch = new FindBranchesPage(DriverFactory.getDriverInstance());
        dashboardPage.waitForObjectToLoad();
        dashboardPage.navigateToBranchFinder();
        findBranch.waitForBranchPageToLoad();
        findBranch.enterPostalCode(postalCode);
        findBranch.clickOnSearch();
        findBranch.clickOnFirstSearch();
        System.out.println("BranchName is: "+findBranch.fetchBranchName());
        log.info("Branch Name: "+findBranch.fetchBranchName());
        log.info("Branch Address1: "+findBranch.fetchBranchAddressLine1());
        dashboardPage.clickOnBankLogo();
    }
}
