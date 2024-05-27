package pageObjects;

import AbsractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(linkText = "Electronics")
    WebElement electronicsTab;

    @FindBy(linkText = "Computers, Tablets & Network Hardware")
    WebElement computersTabletsNetworkHardware;

    @FindBy(linkText = "MacBooks")
    WebElement macBookBtn;

    @FindBy(linkText = "Buy It Now")
    WebElement buyItNowBtn;

    @FindBy(xpath = "//ul[@class='b-list__items_nofooter']//li[1]//div[@class='s-item__info clearfix']//h3")
    WebElement firstMacBookFromList;

    public MainPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String clickOnMacbookPage(){
        waitForWebElementToAppear(electronicsTab).click();
        waitForWebElementToAppear(computersTabletsNetworkHardware).click();
        waitForWebElementToAppear(macBookBtn).click();
        scrollToWebElement(buyItNowBtn);
        buyItNowBtn.click();
        scrollToWebElement(firstMacBookFromList);
        String productText = firstMacBookFromList.getText();
        firstMacBookFromList.click();
        return productText;
    }

}
