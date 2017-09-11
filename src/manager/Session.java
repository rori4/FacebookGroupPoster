package manager;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Session {
    int counter;
    WebDriver driver;

    public void invokeFacebookSession() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter commands below. Read more in the read me file how to present the commands! Enter below: ");
            String username = reader.readLine();
            String password = reader.readLine();
            List<String> post = new ArrayList<String>();
            for (String line = reader.readLine(); !"END".equals(line); line = reader.readLine()) {
                post.add(line);
                post.add("\n");
            }
            String image = reader.readLine();
            List<String> groups = new ArrayList<String>();
            for (String line = reader.readLine(); !"END".equals(line); line = reader.readLine()) {
                groups.add(line);
            }
            System.setProperty("webdriver.gecko.driver", "D:\\OneDrive\\Softuni\\Selenium\\geckodriver-v0.17.0-win64\\geckodriver.exe");
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("dom.file.createInChild", true);
            FirefoxDriver driver = new FirefoxDriver(profile);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);


            FacebookLogger facebookLogger = new FacebookLogger(username,password,driver);
            for (String group : groups) {
                if (counter%3==0){
                    Thread.sleep(10000);
                }
                FacebookPoster facebookPoster = new FacebookPoster(StringUtils.join(post, ""),image,group,driver);
                counter++;
            }

//             driver.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
