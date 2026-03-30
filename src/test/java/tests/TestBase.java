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

    static boolean videoEnabled;
    static boolean screenshotEnabled;
    static boolean pageSourceEnabled;
    static boolean logsEnabled;

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void beforeAll() {

        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        String remote = "https://" + System.getProperty("login") + ":"
                + System.getProperty("password") + "@"
                + System.getProperty("remoteURL", "selenoid.autotests.cloud/wd/hub");
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "128.0");

        videoEnabled = Boolean.parseBoolean(System.getProperty("videoEnabled", "false"));
        screenshotEnabled = Boolean.parseBoolean(System.getProperty("screenshotEnabled", "true"));
        pageSourceEnabled = Boolean.parseBoolean(System.getProperty("pageSourceEnabled", "true"));
        logsEnabled = Boolean.parseBoolean(System.getProperty("logsEnabled", "true"));

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
        if (screenshotEnabled) {
            Attach.screenshotAs("Last screenshot");
        }

        if (pageSourceEnabled) {
            Attach.pageSource();
        }

        if (logsEnabled) {
            Attach.browserConsoleLogs();
        }

        if (videoEnabled) {
            Attach.addVideo();
        }
        closeWebDriver();

    }





}
