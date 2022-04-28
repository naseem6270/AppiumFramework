package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;

public class ContentDetails extends BaseTest {

	By btnBackNavigation = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
	ContentList _contentList;

	public void NavigateBackToContentList() throws InterruptedException {
		_contentList = new ContentList();
		MobileElement backButton = (MobileElement) driver.findElement(btnBackNavigation);
		click(backButton, "Navigation Back Button");		
		_contentList.WaitForContentListPage();

	}

}
