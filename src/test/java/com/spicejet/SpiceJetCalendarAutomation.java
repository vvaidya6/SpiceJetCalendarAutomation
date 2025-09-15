package com.spicejet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class SpiceJetCalendarAutomation {

    public static void main(String[] args)  {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.spicejet.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By fromCityTextBoxLocator = By.xpath("//div[text()='From']/following-sibling::div/input");
        WebElement fromCityTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(fromCityTextBoxLocator));
        fromCityTextBox.sendKeys("Mum");

        By toCityTextBoxLocator = By.xpath("//div[text()='To']/following-sibling::div/input");
        WebElement toCityTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(toCityTextBoxLocator));
        toCityTextBox.sendKeys("Pun");


        By calendarPickerLocator = By.xpath("//div[@data-testid='undefined-calendar-picker']");
        WebElement calendarPicker = wait.until(ExpectedConditions.visibilityOfElementLocated(calendarPickerLocator));

        By nextButtonLocator = By.xpath(".//*[local-name()='svg' and @data-testid='svg-img']");
        WebElement nextButton = calendarPicker.findElement(nextButtonLocator);
        nextButton.click();


        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusMonths(2);
        String month = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = localDate.getYear();
        System.out.println(month);
        System.out.println(year);

        //sleepFor(2);

        By monthCalendarLocator = By.xpath("//div[@data-testid='undefined-month-"+ month +"-"+ year +"']");
        WebElement monthCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(monthCalendarLocator));

        By dataLocator = By.xpath(".//div[contains(text(),'9')]");
        monthCalendar.findElement(dataLocator).click();







    }

    public static void sleepFor(int timeInSeconds){
        try{
            Thread.sleep(timeInSeconds*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
