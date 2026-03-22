package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableResultComponent {
    private final SelenideElement tableResponsive = $(".table-responsive");

    public TableResultComponent checkResult(String key, String value) {
        tableResponsive.$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }
}
