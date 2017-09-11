package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookLogger {
    private String username;
    private String password;

    public FacebookLogger(String username, String password, WebDriver driver) {
        this.username = username;
        this.password = password;
        try {
            driver.get("https://www.facebook.com/");
            driver.findElement(By.id("email")).sendKeys(this.username);
            driver.findElement(By.id("pass")).sendKeys(this.password);
            driver.findElement(By.id("loginbutton")).click();
            if (("https://www.facebook.com/").equals(driver.getCurrentUrl())){
                System.out.println("Successfully logged in!");
            } else {
                throw new Exception("Wrong Facebook credentials!");
            }
        } catch (Exception e) {
            e.getMessage();
            System.exit(0);
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
