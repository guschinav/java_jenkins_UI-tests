package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testsdata.TestData;

import static io.qameta.allure.Allure.step;
import static testsdata.TestData.*;

@Story("Registration form")
public class RegistrationWithPageObjectTests extends TestBase {

    TestData testData = new TestData();

    @Test
    @DisplayName("Successful Fill Practice Form")
    void successfulFillPracticeFormTest(){

        step("Open Practice Form https://demoqa.com/automation-practice-form\"", () ->
            registrationPage.openPage()
                    .clickForms()
                    .clickPracticeForm());

        step("Fill registration form https://demoqa.com/automation-practice-form\"", () -> {
            registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(userEmail)
                .setGender(genderWrapper)
                .typeUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .typeSubjectsInput("M",subjectsInput)
                .setHobbies(hobbiesWrapper)
                .selectPicture(picture)
                .setStateAndCity(state,city)
                .typeCurrentAddress(currentAddress)
                .submitForm();
        });

        step("Check registration form results\"", () -> {
            //Checkings
            registrationPage.checkCompletedForm("Thanks for submitting the form")
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", genderWrapper)
                    .checkResult("Mobile", userNumber)
                    .checkResult("Date of Birth", day + " " + month + "," + year)
                    .checkResult("Subjects", subjectsInput)
                    .checkResult("Hobbies", hobbiesWrapper)
                    .checkResult("Picture", picture)
                    .checkResult("Address", currentAddress)
                    .checkResult("State and City", state + " " + city);
        });
    }
}
