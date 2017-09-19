package net.atos.Testing1;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import net.sourceforge.htmlunit.corejs.javascript.ast.TryStatement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoTest {

	static WebDriver driver = new FirefoxDriver();
	static WebDriverWait wait = new WebDriverWait(driver, 60);

	@Test(priority = 1)
	public static void validEmpLoggedIn() {
		System.out.println("-------------------------------------------");
		System.out.println("IN VALIDEMPLOGGED IN METHOD :: ");
		System.out.println("-------------------------------------------");
		try {
			WebElement username = driver.findElement(By
					.xpath(".//*[@id='txtUser']"));

			WebElement password = driver.findElement(By
					.xpath(".//*[@id='txtPassword']"));
			username.sendKeys("sumjaisw");
			password.sendKeys("Sandhya@1993");

			WebElement submit = driver.findElement(By
					.xpath(".//*[@id='submit']"));
			submit.click();
			System.out.println("CURRENT URL :: " + driver.getCurrentUrl());

			WebElement userText = wait
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("//*[@class='main-header-search-usermenu white bold ng-binding']")));
			String text = userText.getText();
			String afterLoggedInString = "Welcome";
			if (text.equals(afterLoggedInString)) {
				System.out.println("TEST PASSED :: USER LOGGED IN ");
			}

		} catch (Throwable e) {
			System.out.println("TEST FAILED :: NOT FOUND " + e.getMessage());
			System.exit(0);
		}
		System.out
				.println("-----------------------------------------------------------------");
	}// end of validEmployee logged in method

	@Test(priority = 2)
	public void searchCustomer() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("search")).sendKeys("Elisa Oyj");
		WebElement icon = wait.until(ExpectedConditions
				.presenceOfElementLocated(By
						.xpath("//*[@class='input-group-btn']")));
		icon.click();
	}// end of searchCustomer method

	@BeforeTest
	public void beforeTest() {
		// log.debug("In Before Test method");
		System.out.println("IN BEFORE TEST METHOD");
		System.out.println("--------------------------------------------");
		//System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		String baseUrl = "https://capmdashboard.int.net.nokia.com";
		try {
			driver.get(baseUrl);
		} catch (Throwable e) {
			System.out.println("CaPM Application DOWN : PLEASE CHECK "
					+ e.getMessage());
			System.exit(0);
		}
	}// end of before test method

	@AfterTest
	public void afterTest() {

	}// end of after test method

}
