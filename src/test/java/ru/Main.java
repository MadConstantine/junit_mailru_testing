package ru;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Main {
	
	public WebDriver browser;

	@Before
	public void setUp() {
		browser = new FirefoxDriver();
		browser.get("https://mail.ru");
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void test_login() {
		//filling in 'login' field
		browser.findElement(By.id("mailbox:login")).sendKeys("surnametest");
				
		//filling in 'password' field
		browser.findElement(By.id("mailbox:password")).sendKeys("testpass789");
				
		//a click on button
		browser.findElement(By.cssSelector(".o-control:nth-child(1)")).click();
				
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PH_user-email")));
				
		// Assert successful login
		WebElement usr_email = browser.findElement(By.id("PH_user-email"));
		String mail_name = usr_email.getText();
		Assert.assertTrue(mail_name.equalsIgnoreCase("surnametest@mail.ru"));
	}
	
	@Test
	public void test_logout() {
		//filling in 'login' field
		browser.findElement(By.id("mailbox:login")).sendKeys("surnametest");
						
		//filling in 'password' field
		browser.findElement(By.id("mailbox:password")).sendKeys("testpass789");
						
		//a click on button
		browser.findElement(By.cssSelector(".o-control:nth-child(1)")).click();
		
		//wait until loaded
		WebDriverWait wait = new WebDriverWait(browser, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PH_user-email")));
		
		/*//click on a compose button  maybe later...
		WebElement write_btn = browser.findElement(By.className("compose-button__wrapper"));
		Actions action = new Actions(browser);
		action.moveToElement(write_btn).click(write_btn).perform();
		
		WebElement recv_field = browser.findElement(By.className("inputContainer--nsqFu"));
		recv_field.click();
		recv_field.sendKeys("surnametest@mail.ru");*/
		
		//click on logout link
		browser.findElement(By.id("PH_logoutLink")).click();
		
		//assert logout
		WebElement auth_link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PH_authLink")));
		Assert.assertTrue(auth_link.isDisplayed());
	}

}
