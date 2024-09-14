package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.page.HomePage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PaymentTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    public static void shouldCleanBase() {
        SQLHelper.cleanBase();
    }

    //Оплата по карте APPROVED
    @Test
    void shouldApprovedCardPayment() {
        var cardinfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(cardinfo);
        form.paymentApproved();
        Assertions.assertEquals("APPROVED", SQLHelper.getCardPayment());
    }

    //Оплата по карте DECLINED
    @Test
    void shouldDeclinedCardPayment() {
        var cardinfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(cardinfo);
        form.paymentDeclined();
        Assertions.assertEquals("DECLINED", SQLHelper.getCardPayment());
    }

    //Заполнение поля "Номер карты":

    //Оставить незаполненное поле
    @Test
    public void shouldCardNumberEmpty() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberEmpty());
        form.incorrectCardNumberVisible();
    }

    //Ввести 1 цифру
    @Test
    public void shouldCardNumberOneDigit() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberOneDigit());
        form.incorrectCardNumberVisible();
    }

    //Ввести 10 цифр
    @Test
    public void shouldCardNumberTenDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberTenDigits());
        form.incorrectCardNumberVisible();
    }

    //Ввести 15 цифр
    @Test
    public void shouldCardNumberFifteenDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberFifteenDigits());
        form.incorrectCardNumberVisible();
    }

    //Ввести 17 цифр
    @Test
    public void shouldCardNumberSeventeenDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberSeventeenDigits());
        form.paymentDeclined();
    }

    //Ввести незарегистрированный номер в базе данных
    @Test
    public void shouldCardNumberNotRegistered() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberNotRegistered());
        form.paymentDeclined();
    }

    //Ввести специальные символы
    @Test
    public void shouldCardNumberSpecialSymbols() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberSpecialSymbols());
        form.incorrectCardNumberVisible();
    }

    //Ввести номер карты кириллицей
    @Test
    public void shouldCardNumberCyrillic() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberCyrillic());
        form.incorrectCardNumberVisible();
    }

    //Ввести номер карты латиницей
    @Test
    public void shouldCardNumberLatin() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberLatin());
        form.incorrectCardNumberVisible();
    }

    //Ввести иероглифы
    @Test
    public void shouldCardNumberHieroglyphs() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCardNumberHieroglyphs());
        form.incorrectCardNumberVisible();
    }

//Заполнение поля "Месяц":

    //Оставить незаполненное поле
    @Test
    public void shouldMonthEmpty() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthEmpty());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести два нуля
    @Test
    public void shouldMonthTwoZero() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthTwoZero());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести 1 цифру - ноль
    @Test
    public void shouldMonthOneDigitWithZero() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthOneDigitWithZero());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести 1 цифру - не ноль
    @Test
    public void shouldMonthOneDigit() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthOneDigit());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести 2 цифры, невалидный месяц (граничное значение)
    @Test
    public void shouldMonthNoValidTwoDigitsLimit() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthNoValidTwoDigitsLimit());
        form.incorrectMonthVisible("Неверно указан срок действия карты");
    }

    //Ввести 2 цифры, невалидный месяц
    @Test
    public void shouldMonthNoValidTwoDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthNoValidTwoDigits());
        form.incorrectMonthVisible("Неверно указан срок действия карты");
    }

    //Ввести 3 цифры
    @Test
    public void shouldMonthThreeDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthThreeDigits());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести специальные символы
    @Test
    public void shouldMonthSpecialSymbols() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthSpecialSymbols());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Заполнить поле кириллицей
    @Test
    public void shouldMonthCyrillic() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthCyrillic());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Заполнить поле латиницей
    @Test
    public void shouldMonthLatin() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthLatin());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести иероглифы
    @Test
    public void shouldMonthHieroglyphs() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getMonthHieroglyphs());
        form.incorrectMonthVisible("Неверный формат");
    }

//Заполнение поля "Год":

    //Оставить незаполненное поле
    @Test
    public void shouldYearEmpty() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearEmpty());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести 1 цифру - ноль
    @Test
    public void shouldYearOneDigitZero() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearOneDigitZero());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести 1 цифру
    @Test
    public void shouldYearOneDigit() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearOneDigit());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести 2 цифры, 2030 год
    @Test
    public void shouldYearNoValidOne() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearNoValidOne());
        form.incorrectYearVisible("Неверно указан срок действия карты");
    }

    //Ввести 2 цифры, 2023 год, прошедший год
    @Test
    public void shouldYearNoValidTwo() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearNoValidTwo());
        form.incorrectYearVisible("Истёк срок действия карты");
    }

    //Ввести 3 цифры
    @Test
    public void shouldYearThreeDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearThreeDigits());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести специальные символы
    @Test
    public void shouldYearSpecialSymbols() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearSpecialSymbols());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести год кириллицей
    @Test
    public void shouldYearCyrillic() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearCyrillic());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести год латиницей
    @Test
    public void shouldYearLatin() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearLatin());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести иероглифы
    @Test
    public void shouldYearHieroglyphs() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getYearHieroglyphs());
        form.incorrectYearVisible("Неверный формат");
    }

    //Заполнение поля "Владелец":

    //Оставить незаполненное поле
    @Test
    public void shouldHolderEmpty() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderEmpty());
        form.incorrectHolderVisible();
    }

    //Ввести 1 букву
    @Test
    public void shouldHolderOneLetter() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderOneLetter());
        form.incorrectHolderVisible();
    }

    //Ввести 36 букв (максимум 35)
    @Test
    public void shouldHolderThirtySixLetter() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderThirtySixLetter());
        form.incorrectHolderVisible();
    }

    //Заполнить поле кирилицей
    @Test
    public void shouldHolderCyrillic() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderCyrillic());
        form.incorrectHolderVisible();
    }

    //Заполнить поле цифрами
    @Test
    public void shouldHolderDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderDigits());
        form.incorrectHolderVisible();
    }

    //Заполнить поле специальными символами
    @Test
    public void shouldHolderSpecialSymbols() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderSpecialSymbols());
        form.incorrectHolderVisible();
    }

    //Заполнить поле иероглифами
    @Test
    public void shouldHolderHieroglyphs() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getHolderHieroglyphs());
        form.incorrectHolderVisible();
    }

//Заполнение поля "CVC/CVV":

    //Оставить незаполненное поле
    @Test
    public void shouldCVCEmpty() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCEmpty());
        form.incorrectCodeVisible();
    }

    //Ввести 1 цифру
    @Test
    public void shouldCVCOneDigit() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCOneDigit());
        form.incorrectCodeVisible();
    }

    //Ввести 2 цифры
    @Test
    public void shouldCVCTwoDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCTwoDigits());
        form.incorrectCodeVisible();
    }

    //Ввести 4 цифры
    @Test
    public void shouldCVCFourDigits() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCFourDigits());
        form.incorrectCodeVisible();
    }

    //Ввести специальные символы
    @Test
    public void shouldCVCSpecialSymbols() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCSpecialSymbols());
        form.incorrectCodeVisible();
    }

    //Ввести код кириллицей
    @Test
    public void shouldCVCCyrillic() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCCyrillic());
        form.incorrectCodeVisible();
    }

    //Ввести код латиницей
    @Test
    public void shouldCVCLatin() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCLatin());
        form.incorrectCodeVisible();
    }

    //Ввести иероглифы
    @Test
    public void shouldCVCHieroglyphs() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.completedForm(DataHelper.getCVCHieroglyphs());
        form.incorrectCodeVisible();
    }

    //Оставить незаполненную форму
    @Test
    void shouldFormEmpty() {
        var homepage = new HomePage();
        homepage.buyByPaymentCard();
        var form = new PaymentPage();
        form.emptyForm();
    }
}

