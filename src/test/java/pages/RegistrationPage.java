package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.And;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.TableResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    CalendarComponent calendar = new CalendarComponent();
    TableResultComponent tableResult = new TableResultComponent();
    // Elements
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement userNumberInput = $(("#userNumber"));
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private final SelenideElement pictureUpload = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateAndCityContainer= $("#stateCity-wrapper");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement compleatedForm = $(".modal-header");




    @Step("Open registration page https://demoqa.com")
    // Actions
    public RegistrationPage openPage(){
        open("");
        return this;
    }
    @Step("Open Forms https://demoqa.com/forms")
    public RegistrationPage clickForms(){
        $$(".card-body").findBy(text("Forms")).click();
        return this;
    }

    @Step("Open Practice Form https://demoqa.com/automation-practice-form")
    public RegistrationPage clickPracticeForm(){
        $$(".router-link").findBy(text("Practice Form")).click();
        return this;
    }


    @Step("Type First Name \"{value}\"")
    public RegistrationPage typeFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Type Last Name \"{value}\"")
    public RegistrationPage typeLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Type Email \"{value}\"")
    public RegistrationPage typeEmail(String value){
        emailInput.setValue(value);
        return this;
    }

    @Step("Set Gender \"{value}\"")
    public RegistrationPage setGender(String value){
        genderContainer.$(byText(value)).click();
        return this;
    }

    @Step("Type User Number \"{value}\"")
    public RegistrationPage typeUserNumber(String value){
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Set Date of birth \"{value}\"")
    public RegistrationPage setDateOfBirth(String day, String month, String year){
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Type Subjects \"{value}\"")
    public RegistrationPage typeSubjectsInput(String type, String value){
        subjectsInput.setValue(type);
        $(byText(value)).click();
        return this;
    }

    @Step("Set Hobbies \"{value}\"")
    public RegistrationPage setHobbies(String value){
        hobbiesContainer.$(byText(value)).click();
        return this;
    }

    @Step("Add Picture \"{value}\"")
    public RegistrationPage selectPicture(String value){
        pictureUpload.uploadFromClasspath(value);
        return this;
    }

    @Step("Type Current Address \"{value}\"")
    public RegistrationPage typeCurrentAddress(String value){
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Set State\"{state}\" And City \"{city}\"")
    public RegistrationPage setStateAndCity(String state, String city){
        $("#state").click();
        stateAndCityContainer.$(byText(state)).click();
        $("#city").click();
        stateAndCityContainer.$(byText(city)).click();
        return this;
    }

    @Step("Click Submit Button")
    public RegistrationPage submitForm(){
        submitButton.click();
        return this;
    }

    @Step("Check Completed Form")
    public RegistrationPage checkCompletedForm(String value){
        compleatedForm.shouldBe(text(value));
        return this;
    }

    @Step("Check Result \"{key}\" has \"{value}\"")
    public  RegistrationPage checkResult (String key, String value){
        tableResult.checkResult(key, value);

        return this;

    }
}






