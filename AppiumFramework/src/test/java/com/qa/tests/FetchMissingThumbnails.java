package com.qa.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.ContentDetails;
import com.qa.pages.ContentList;
import com.qa.pages.HomePage;

public class FetchMissingThumbnails extends BaseTest {

	HomePage _homePage;

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("\n" + "****Starting Test: " + m.getName() + "****" + "\n");
	}

	@Test(priority = 1)
	public void FindingAllContentsWithNoThumbNail() throws InterruptedException {

		_homePage = new HomePage();
		_homePage.VerifyContentsWithNoThumbNails();

	}
	


}
