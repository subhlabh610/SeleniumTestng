package utils;

import factory.DriverFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonMethods{

    public static void waitForObject(WebElement obj,int timeToWait){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriverInstance(), Duration.ofSeconds(timeToWait));
        wait.until(ExpectedConditions.visibilityOf(obj));
    }

    public static void waitForObjectToLoadInFrequencyOf(WebDriver driver,int timeToWait){
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeToWait))
                .pollingEvery(Duration.ofSeconds(timeToWait));
    }

    public static void forcedWait(int timeToWait){
        try{
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void downArrowMouseAction(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.sendKeys(element, Keys.ARROW_DOWN).perform();
        forcedWait(2000); // Add a short delay to see the scrolling effect
    }

    public static List<Object[]> readDataFromExcel(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<Object[]> data = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            String postalCode = row.getCell(0).getStringCellValue();
            data.add(new Object[]{postalCode});
        }
        workbook.close();
        return data;
    }
}
