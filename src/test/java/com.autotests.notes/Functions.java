package com.autotests.notes;

/**
 * Created by o.budilovsky on 25.03.2017.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.autotests.notes.Variables.*;

public class Functions {

    public static WebDriver driver;

    public static void openWebApllication() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    public static void createNote(String title, String value) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 0);
        wait.until(visibilityOfElementLocated(By.xpath(TITLE_INPUT)));
        driver.findElement(By.xpath(TITLE_INPUT)).clear();
        driver.findElement(By.xpath(NOTE_INPUT)).clear();
        driver.findElement(By.xpath(TITLE_INPUT)).sendKeys(title);
        driver.findElement(By.xpath(NOTE_INPUT)).sendKeys(value);
        driver.findElement(By.xpath(BUTTON_DONE)).click();
    }

    public static void createNoteMultipleTimes(int repeat) throws InterruptedException {
        for (int i=0; i<repeat; i++) {
            WebDriverWait wait = new WebDriverWait(driver, 0);
            String uniqueTitle = "Created Title " + System.currentTimeMillis();
            driver.findElement(By.xpath(TITLE_INPUT)).sendKeys(uniqueTitle);
            String uniqueNote = "Description " + System.currentTimeMillis();
            driver.findElement(By.xpath(NOTE_INPUT)).sendKeys(uniqueNote);
            createNote(uniqueTitle, uniqueNote);
            wait.until(visibilityOfElementLocated(By.xpath(NOTE_TITLE + "[contains(text(), '" + uniqueTitle + "')]")));
            wait.until(visibilityOfElementLocated(By.xpath(NOTE_VALUE + "[contains(text(), '" + uniqueNote + "')]")));
        }
    }

    public static void closeNotes() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 0);
        wait.until(visibilityOfElementLocated(By.xpath(TITLE_INPUT)));
        while (isElementPresent(NOTE_CARD)) {
            Actions builder = new Actions(driver);
            builder.moveToElement(driver.findElement(By.xpath(NOTE_CARD))).build().perform();
            driver.findElement(By.xpath(NOTE_ICON)).click();
        }
    }

    public static boolean isElementPresent(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath)).getCssValue("") != null;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}