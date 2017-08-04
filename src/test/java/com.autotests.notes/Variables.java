package com.autotests.notes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by o.budilovsky on 25.03.2017.
 */

public class Variables {

    public static final String BASE_URL = "http://localhost:3000";
    //@FindBy(xpath = "//input[@placeholder='Title']")
    //public WebElement TITLE_INPUT;
    public static final String TITLE_INPUT = "//input[@placeholder='Title']";
    public static final String NOTE_INPUT = "//input[@placeholder='Take a note']";
    public static final String BUTTON_DONE = "//button[@type='submit']";
    public static final String NOTE_TITLE = "//div[@class='col-xs-12 title']";
    public static final String NOTE_VALUE = "//div[@class='col-xs-12 value']";
    public static final String NOTE_CARD = "//note-card[@class='col-xs-4']";
    public static final String NOTE_ICON = "//div[@class='icon']";

}