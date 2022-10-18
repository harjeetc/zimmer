package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class ZimmerBIoMetHomePage extends BasePage {
	
    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;

    public ZimmerBIoMetHomePage(WebDriver driver) {
        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ZimmerBIoMetHomePage load(){
        load("/");
        return this;
    }
}
