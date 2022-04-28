package com.qa.pages;

import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;

public class ContentList extends BaseTest {

	HomePage _homePage;

	By listAllContent = By.xpath("//android.widget.ImageView[@resource-id='com.sboxnw.freeplay:id/imageViewAllItem']");

	By listNoImagesContent = By.xpath(
			"//android.widget.FrameLayout[@resource-id='com.sboxnw.freeplay:id/cardviewViewAll']/android.widget.TextView[@resource-id='com.sboxnw.freeplay:id/tvItemTitleNotFound']");
	By btnBackNavigation = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

	public void VerifyNoImageContentLink(String pageName) throws InterruptedException {
		List<MobileElement> listNoImageContent = null;
		ContentDetails _contentDetails = new ContentDetails();
		HashSet<String> _hashContentName = new HashSet<String>();
		Boolean _recordFound = false;
		int index, counter = 0;

		outer: {
			for (int i = 0; i < 5; i++) {

				String contentNameWithNoThumbNails = null;

				for (int x = 0; x < 7; x++) {
					listNoImageContent = getElementList(listNoImagesContent, "Records with no thumbnails");
					if (listNoImageContent.size() == 0) {

						Scroll(1);
						if (x == 6) {
							break outer;
						}
						continue;
					} else {
						_recordFound = true;

					}

					if (listNoImageContent.size() > 1) {
						index = listNoImageContent.size();
					} else {
						index = 1;
					}

					for (int j = 0; j < index; j++) {

						contentNameWithNoThumbNails = listNoImageContent.get(j).getText();

						if (_hashContentName.add(contentNameWithNoThumbNails)) {
							if (_recordFound) {
								System.out.println("Found Content with no Thumbnails with the name as : "
										+ contentNameWithNoThumbNails);
								Reporter.log("Found record with no Thumbnail with the name as : "
										+ contentNameWithNoThumbNails);
								click(listNoImageContent.get(j), "Content with record " + contentNameWithNoThumbNails);
								_contentDetails.NavigateBackToContentList();
								counter++;
								Scroll(1);
								listNoImageContent = getElementList(listNoImagesContent, "Records with no thumbnails");
							}
						} else {
							Scroll(x);
						}

						if (x == 6) {
							break outer;
						}
					}
				}

			}
		}

		if (!_recordFound)

		{
			Reporter.log("No record found with no thumbnail on the page " + pageName);

		} else {
			Reporter.log("Total number of records found with no thumbnail in the page " + pageName + " is: " + counter);
		}

	}

	public void NavigateBackToHomePage() throws InterruptedException {
		_homePage = new HomePage();
		MobileElement backButton = (MobileElement) driver.findElement(btnBackNavigation);
		click(backButton, "Navigation Back Button");
		_homePage.WaitForHomePage();
	}

	public void WaitForContentListPage() throws InterruptedException {
		waitforVisibility(listAllContent);
	}

}
