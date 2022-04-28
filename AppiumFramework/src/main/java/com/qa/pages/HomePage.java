package com.qa.pages;

import java.util.HashSet;
import java.util.List;

import javax.swing.Scrollable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.qa.BaseTest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class HomePage extends BaseTest {

	ContentList _contentList;

	By listViewAllButton = By.xpath("//android.widget.TextView[@resource-id='com.sboxnw.freeplay:id/tvViewAll']");
	By listAllImages = By.xpath("//android.widget.ImageView[@resource-id='com.sboxnw.freeplay:id/imageViewAllItem']");
	By headerMovieSection = By.xpath(
			"//android.widget.TextView[@resource-id='com.sboxnw.freeplay:id/tvViewAll']/preceding-sibling::android.widget.TextView[@resource-id='com.sboxnw.freeplay:id/tvWidgetTitle']");
	By sectionPartnerZone = By
			.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.sboxnw.freeplay:id/rvItems']");

	public void VerifyContentsWithNoThumbNails() throws InterruptedException {
		_contentList = new ContentList();
		int j = 0;

		Scroll(1);

		List<MobileElement> listViewAll = getElementList(listViewAllButton, "View All button");
		List<MobileElement> listHeaderMovies = getElementList(headerMovieSection, "Header Movies");
		HashSet<String> _hashSectionName = new HashSet<String>();

		if (listViewAll.size() == 0) {
			Scroll(1);
			WaitForHomePage();
		}
		for (int i = 0; i < 5; i++) {

			if (i >= 2) {
				j = 1;
			}

			listViewAll = getElementList(listViewAllButton, "View All button");
			listHeaderMovies = getElementList(headerMovieSection, "Header Movies");

			String movieSection = getText(listHeaderMovies.get(j), "Header Movie Section");
			if (_hashSectionName.add(movieSection)) {
				System.out.println("Clicking on View All button against the section " + movieSection);
				Reporter.log("Clicking on View All button against the section " + movieSection);
				click(listViewAll.get(j), "View All Button");
				_contentList.WaitForContentListPage();
				_contentList.VerifyNoImageContentLink(movieSection);
				_contentList.NavigateBackToHomePage();
				Scroll(i + 1);

			} else {
				Scroll(1);
			}
			j++;

		}

	}

	public void WaitForHomePage() throws InterruptedException {
		// TODO Auto-generated method stub
		WaitFor(2000);
		waitforVisibility(sectionPartnerZone);

	}

}
