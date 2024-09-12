package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final SelenideElement buy = $(byText("Купить"));
    private final SelenideElement buyOnCredit = $(byText("Купить в кредит"));

    public PaymentPage buyByPaymentCard() {
        buy.click();
        return new PaymentPage();
    }

    public CreditPage buyByCreditCard() {
        buyOnCredit.click();
        return new CreditPage();
    }
}