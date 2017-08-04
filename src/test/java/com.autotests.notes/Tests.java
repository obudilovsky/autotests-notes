package com.autotests.notes;

/**
 * Created by o.budilovsky on 25.03.2017.
 */

import org.junit.*;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import static com.autotests.notes.Functions.*;
import static com.autotests.notes.Variables.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    @BeforeClass
    public static void setUp() throws InterruptedException {
        openWebApllication();
    }

        @Test //Positive
        public void checkStatusCode() throws IOException {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(driver.getCurrentUrl());
            try {
                HttpResponse response = client.execute(request);
                assertEquals(200,response.getStatusLine().getStatusCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test //Positive
        public void checkUrl() throws InterruptedException {
            assertEquals(true, driver.getCurrentUrl().contains("localhost:3000"));
        }

        @Test //Positive
        public void checkPageTitle() {
            WebDriverWait wait = new WebDriverWait(driver, 0);
            wait.until(visibilityOfElementLocated(By.xpath(TITLE_INPUT)));
            assertEquals("notes-web-application" , driver.getTitle());
        }

        @Test //Positive
        public void checkElementsOnPage() throws InterruptedException {
            assertEquals(true, driver.findElement(By.xpath(NOTE_INPUT)).isDisplayed());
            assertEquals(true, driver.findElement(By.xpath(BUTTON_DONE)).isEnabled());
        }





        @Test //Positive
        public void closeDefaultCards() throws InterruptedException{
            closeNotes();
            assertEquals(false, isElementPresent(NOTE_TITLE+"[contains(text(), 'test_title')]"));
            assertEquals(false, isElementPresent(NOTE_VALUE+"[contains(text(), 'test_value')]"));
        }

        @Test //Positive
        public void createCardValidValue() throws InterruptedException {
            createNote("1234567890","Description");
            assertEquals(true, isElementPresent(NOTE_TITLE+"[contains(text(), '1234567890')]/../div[2][contains(text(), 'Description')]"));
        }

        @Test //Positive
        public void createCardWithEmptyValue() throws InterruptedException {
            createNote("Title","");
            assertEquals(true, isElementPresent(NOTE_TITLE));
            assertEquals("", driver.findElement(By.xpath(NOTE_TITLE+"[contains(text(), 'Title')]/../div[2]")).getText().replaceAll(" ", ""));
        }

        @Test //Positive
        public void createCardWithValueContainsSpace() throws InterruptedException {
            createNote("Title"," ");
            assertEquals(true, isElementPresent(NOTE_TITLE));
            assertEquals("", driver.findElement(By.xpath(NOTE_TITLE+"[contains(text(), 'Title')]/../div[2]")).getText().replaceAll(" ", ""));
        }

        @Test //Positive
        public void createCardWithEmptyTitle() throws InterruptedException {
            createNote("","Description");
            assertEquals(true, isElementPresent(NOTE_VALUE));
            assertEquals("", driver.findElement(By.xpath(NOTE_VALUE+"[contains(text(), 'Description')]/../div[1]")).getText().replaceAll(" ", ""));
        }

        @Test //Positive
        public void createCardWithTitleContainsSpace() throws InterruptedException {
            createNote(" ","Description");
            assertEquals(true, isElementPresent(NOTE_VALUE));
            assertEquals("", driver.findElement(By.xpath(NOTE_VALUE+"[contains(text(), 'Description')]/../div[1]")).getText().replaceAll(" ", ""));
        }

        @Test
        // Select note card and click on checkbox
        public void closeCreatedCards() throws InterruptedException{
            closeNotes();
            assertEquals(false, driver.findElement(By.xpath(NOTE_TITLE+"[contains(text(), '1234567890')]")).isDisplayed());
            assertEquals(false, driver.findElement(By.xpath(NOTE_TITLE+"[contains(text(), 'Title')]")).isDisplayed());
        }

        @Test
        // Create 20 notes
        public void create20Cards() throws InterruptedException{
            createNoteMultipleTimes(20);
        }



    @AfterClass
    public static void tearDown() {
        if (driver != null) driver.quit();
    }
}