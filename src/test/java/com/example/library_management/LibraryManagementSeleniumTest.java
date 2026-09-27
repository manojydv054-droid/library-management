package com.example.library_management;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryManagementSeleniumTest {

    static WebDriver driver;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void testHomePage() {
        driver.get("http://localhost:8080");

        assertTrue(
                driver.getPageSource().contains("Library Management System")
        );
    }

    @Test
    void testBooksPage() {
        driver.get("http://localhost:8080/books");

        assertTrue(
                driver.getPageSource().contains("Available Books")
        );
    }

    @Test
    void testMembersPage() {
        driver.get("http://localhost:8080/members");

        assertTrue(
                driver.getPageSource().contains("Members")
        );
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}