import Pages.AccountPage;
import Pages.HomePage;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

@Feature("Login Test Senaryoları")
public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String tarayici = System.getProperty("browser");
        String osName = System.getProperty("os.name");

        if (tarayici== null) {tarayici="Firefox";}

        System.out.println(tarayici);
        if (tarayici.equals("Chrome")) {
            if (osName.equals("Mac OS X")) {
                System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            }
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(co);
        }
        else {
            if (osName.equals("Mac OS X")) {
                System.setProperty("webdriver.chrome.driver", "driver/geckodriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }

        driver.get("https://parabank.parasoft.com");
        driver.manage().window().maximize();
    }

    @Test(description = "TC0001 Başarılı Login Kullanıcısı")
    public void TC0001() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);

        homePage
                .userNameDoldur("Test")
                .passwordDoldur("Test123456")
                .login();
        accountPage
                .accountSayfaKontrolu("Accounts Overview");
    }

    @Test(description = "TC0002 Username alanı boş gönderilme")
    public void TC0002() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);

        homePage
                .userNameDoldur("")
                .passwordDoldur("Test123456")
                .login()
                .errorKontrolu("Please enter a username and password.");
    }

    @Test(description = "TC0003 Password alanı boş gönderilme")
    public void TC0003() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);

        homePage
                .userNameDoldur("Test")
                .passwordDoldur("")
                .login()
                .errorKontrolu("Please enter a username and password.");
    }

    @Test(description = "TC0004 Username & Password alanı boş gönderilme Fail")
    public void TC0004() {

        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);

        homePage
                .userNameDoldur("")
                .passwordDoldur("")
                .login();
        accountPage
                .accountSayfaKontrolu("Accounts Overview");
    }

    @Test(description = "TC0005 Username & Password alanı boş gönderilme")
    public void TC0005() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);

        homePage
                .userNameDoldur("")
                .passwordDoldur("")
                .login()
                .errorKontrolu("Please enter a username and password.");

    }

    @AfterMethod
    public void afterDown() {
        driver.quit();
    }
}
