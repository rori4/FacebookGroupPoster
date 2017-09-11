package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookPoster {
    private static final String SUBMIT_BUTTON_XPATH = ".//*[@data-testid='react-composer-post-button']";
    private String post;
    private String image;

    public FacebookPoster(String post, String image, String group, WebDriver driver) throws InterruptedException {
        this.post = post;
        try {
            driver.get(group);
            driver.findElement(By.name("xhpc_message_text")).sendKeys(post);
            if (!"".equals(image)){
                driver.findElement(By.xpath(".//*[@label='Add Photo/Video']")).click();
                driver.findElement(By.xpath("js_qj")).sendKeys(image);
            }
            Thread.sleep(5000);
            WebElement element = driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH));
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUBMIT_BUTTON_XPATH)));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH)).click();
            Thread.sleep(3000);
        } catch (NoSuchElementException e) {
            System.out.println("Not a good group to post: " + group);
        }
    }
}
