package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {
    String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void shouldTestV1() {
        $("[data-test-id='city'] input").setValue("Краснодар");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ефимов Александр");
        $("[data-test-id='phone'] input").setValue("+79256678765");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));
    }

    @Test
    void shouldTestV2() {
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ефимов Александр");
        $("[data-test-id='phone'] input").setValue("+79256678765");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestV3() {
        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='name'] input").setValue("Ефимов Александр");
        $("[data-test-id='phone'] input").setValue("+79256678765");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Неверно введена дата"));
    }

    @Test
    void shouldTestV4() {
        $("[data-test-id='city'] input").setValue("Новосибирск");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='phone'] input").setValue("+79256678765");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestV5() {
        $("[data-test-id='city'] input").setValue("Псков");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ефимов Александр");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestV6() {
        $("[data-test-id='city'] input").setValue("Краснодар");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Ефимов Александр");
        $("[data-test-id='phone'] input").setValue("+79256678765");
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}


