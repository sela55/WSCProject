package pageObjects;

import AbsractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractComponent {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    @FindBy(id = "atcBtn_btn_1")
    @FindBy(linkText = "Add to cart")
    private WebElement addToCartBtn;

    @FindBy(id = "gh-minicart-hover")
    private WebElement miniCartHover;

    @FindBy(xpath = "//div[@id='mainContent']//h1//span[@class='ux-textspans ux-textspans--BOLD']")
    private WebElement productInfo;

    public void addToCartAndGoToCartPage(){
        switchToLatestTab();
        waitForWebElementToAppear(addToCartBtn).click();
    }
}
