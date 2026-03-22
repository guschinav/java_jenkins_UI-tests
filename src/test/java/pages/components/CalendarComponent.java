package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement monthSelect = $(".react-datepicker__month-select");
    private final SelenideElement yearSelect = $(".react-datepicker__year-select");
    private SelenideElement daySelect(String day) {
        return $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)");
    }

    public void setDate (String day, String month, String year){
        monthSelect.$(byText(month)).click();
        yearSelect.$(byText(year)).click();
        daySelect(day).click();
    }
}
