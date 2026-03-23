package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {




    RegistrationPage registrationPage = new RegistrationPage();



    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @BeforeAll
    static void beforeAll() {

        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        String remote = "https://" + System.getProperty("login", "user1") + ":"
                + System.getProperty("password", "1234") + "@"
                + System.getProperty("remoteURL", "selenoid.autotests.cloud/wd/hub");
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "128.0");

        boolean videoEnabled = Boolean.parseBoolean(System.getProperty("video.enabled", "false"));

        Configuration.baseUrl = baseUrl;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", videoEnabled
        ));
        Configuration.browserCapabilities = capabilities;

        Configuration.remote = remote;
    }


    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
//        Attach.attachAsText("Some file", "Some content");
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();

    }





}
