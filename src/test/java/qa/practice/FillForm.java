package qa.practice;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;

public class FillForm extends TestBase{

    @Test
    @Tag("remote")
    @Feature("Проверка заполнения формы")
    @Story("Заполнеяния формы с фейкером")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("baikenovAlisher")
    public void fillFormTest(){
        Faker faker = new Faker(new Locale("ru"));

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        String address = faker.address().streetAddress();

        SelenideLogger.addListener("allure", new AllureSelenide());

        step ("Открываем страницу формы",()-> {
            open("/automation-practice-form");
        });
        step("Удаляем рекламу и футер",()->{
            executeJavaScript("$('#fixedban').remove()"); // delete reklama
            executeJavaScript("$('footer').remove()"); // delete footer
        });
        step("Заполняем поле имя и фамилия",()->{
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
        });
        step("Заполняем поле email",()->{
            $("#userEmail").setValue(userEmail);
        });
        step("Выбираем пол",()->{
            $("[for=gender-radio-1]").click();
        });
        step("Заполняем поле мобильный номер",()->{
            $("#userNumber").setValue("87777777777");
        });
        step("Устанавливаем дату рождения",()->{
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("2008");
            $(".react-datepicker__day--029:not(.react-datepicker__day--outside-month)").click();
        });
        step("Выбираем предмет",()->{
            $("#subjectsInput").setValue("Math").pressEnter();
        });
        step("Выбираем развлечение",()->{
            $("#hobbiesWrapper").$(byText("Reading")).click();
        });
        step("Загружаем фото",()->{
            $("#uploadPicture").uploadFromClasspath("img/bmw.jpg");
        });
        step("Заполняем адрес",()->{
            $("#currentAddress").setValue(address);
        });
        step("Заполняем штат и город",()->{
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Noida")).click();
        });
        step("Нажимаем на кнопку",()->{
            $("#submit").click();
        });
        step("Проверка появления модального окна",()->{
            $(".modal-dialog").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        });
        step("Проверяем наличие фио, email, мобильного номера и адресса",()->{
            $(".table-responsive").shouldHave(text(firstName),text(lastName),
                    text(userEmail),text("8777777777"),text(address));
        });
    }

}
