package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.CreditPage;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    //Кредит по данным карты APPROVED
    @Test
    void shouldApprovedCardPaymentByCredit() {
        var cardinfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(cardinfo);
        form.paymentApproved();
    }

    //Кредит по данным карты DECLINED
    @Test
    void shouldDeclinedCardPaymentByCredit() {
        var cardinfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(cardinfo);
        form.paymentDeclined();
    }

    //Заполнение поля "Номер карты":

    //Оставим незаполненное поле
    @Test
    public void shouldCardNumberEmptyCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberEmpty());
        form.incorrectCardNumberVisible();
    }

    //Введем 1 цифру
    @Test
    public void shouldCardNumberOneDigitCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberOneDigit());
        form.incorrectCardNumberVisible();
    }

    //Введем 10 цифр
    @Test
    public void shouldCardNumberTenDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberTenDigits());
        form.incorrectCardNumberVisible();
    }

    //Введем 15 цифр
    @Test
    public void shouldCardNumberFifteenDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberFifteenDigits());
        form.incorrectCardNumberVisible();
    }

    //Введем 17 цифр
    @Test
    public void shouldCardNumberSeventeenDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberSeventeenDigits());
        form.paymentDeclined();
    }

    //Введем незарегистрированный номер в базе данных
    @Test
    public void shouldCardNumberNotRegisteredCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberNotRegistered());
        form.paymentDeclined();
    }

    //Введем специальные символы
    @Test
    public void shouldCardNumberSpecialSymbolsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberSpecialSymbols());
        form.incorrectCardNumberVisible();
    }

    //Введем номер карты кириллицей
    @Test
    public void shouldCardNumberCyrillicCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberCyrillic());
        form.incorrectCardNumberVisible();
    }

    //Введем номер карты латиницей
    @Test
    public void shouldCardNumberLatinCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberLatin());
        form.incorrectCardNumberVisible();
    }

    //Введем иероглифы
    @Test
    public void shouldCardNumberHieroglyphsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCardNumberHieroglyphs());
        form.incorrectCardNumberVisible();
    }

//Заполнение поля "Месяц":

    //Оставим незаполненное поле
    @Test
    public void shouldMonthEmptyCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthEmpty());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Введем два нуля
    @Test
    public void shouldMonthTwoZeroCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthTwoZero());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Введем 1 цифру - ноль
    @Test
    public void shouldMonthOneDigitWithZeroCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthOneDigitWithZero());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Введем 1 цифру - не ноль
    @Test
    public void shouldMonthOneDigitCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthOneDigit());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Введем 2 цифры, невалидный месяц (граничное значение)
    @Test
    public void shouldMonthNoValidTwoDigitsLimitCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthNoValidTwoDigitsLimit());
        form.incorrectMonthVisible("Неверно указан срок действия карты");
    }

    //Введем 2 цифры, невалидный месяц
    @Test
    public void shouldMonthNoValidTwoDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthNoValidTwoDigits());
        form.incorrectMonthVisible("Неверно указан срок действия карты");
    }

    //Введем 3 цифры
    @Test
    public void shouldMonthThreeDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthThreeDigits());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Введем специальные символы
    @Test
    public void shouldMonthSpecialSymbolsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthSpecialSymbols());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Заполним поле кириллицей
    @Test
    public void shouldMonthCyrillicCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthCyrillic());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Заполним поле латиницей
    @Test
    public void shouldMonthLatinCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthLatin());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Введем иероглифы
    @Test
    public void shouldMonthHieroglyphsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getMonthHieroglyphs());
        form.incorrectMonthVisible("Неверный формат");
    }

//Заполнение поля "Год":

    //Оставим незаполненное поле
    @Test
    public void shouldYearEmptyCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearEmpty());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем 1 цифру - ноль
    @Test
    public void shouldYearOneDigitZeroCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearOneDigitZero());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем 1 цифру
    @Test
    public void shouldYearOneDigitCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearOneDigit());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем 2 цифры, 2030 год
    @Test
    public void shouldYearNoValidOneCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearNoValidOne());
        form.incorrectYearVisible("Неверно указан срок действия карты");
    }

    //Введем 2 цифры, 2023 год, прошедший год
    @Test
    public void shouldYearNoValidTwoCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearNoValidTwo());
        form.incorrectYearVisible("Истёк срок действия карты");
    }

    //Введем 3 цифры
    @Test
    public void shouldYearThreeDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearThreeDigits());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем специальные символы
    @Test
    public void shouldYearSpecialSymbolsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearSpecialSymbols());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем год кириллицей
    @Test
    public void shouldYearCyrillicCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearCyrillic());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем год латиницей
    @Test
    public void shouldYearLatinCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearLatin());
        form.incorrectYearVisible("Неверный формат");
    }

    //Введем иероглифы
    @Test
    public void shouldYearHieroglyphsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getYearHieroglyphs());
        form.incorrectYearVisible("Неверный формат");
    }

    //Заполнение поля "Владелец":

    //Оставим незаполненное поле
    @Test
    public void shouldHolderEmptyCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderEmpty());
        form.incorrectHolderVisible();
    }

    //Введем 1 букву
    @Test
    public void shouldHolderOneLetterCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderOneLetter());
        form.incorrectHolderVisible();
    }

    //Введем 36 букв (максимум 35)
    @Test
    public void shouldHolderThirtySixLetterCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderThirtySixLetter());
        form.incorrectHolderVisible();
    }

    //Заполним поле кирилицей
    @Test
    public void shouldHolderCyrillicCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderCyrillic());
        form.incorrectHolderVisible();
    }

    //Заполним поле цифрами
    @Test
    public void shouldHolderDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderDigits());
        form.incorrectHolderVisible();
    }

    //Заполним поле специальными символами
    @Test
    public void shouldHolderSpecialSymbolsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderSpecialSymbols());
        form.incorrectHolderVisible();
    }

    //Заполним поле иероглифами
    @Test
    public void shouldHolderHieroglyphsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getHolderHieroglyphs());
        form.incorrectHolderVisible();
    }

//Заполнение поля "CVC/CVV":

    //Оставим незаполненное поле
    @Test
    public void shouldCVCEmptyCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCEmpty());
        form.incorrectCodeVisible();
    }

    //Введем 1 цифру
    @Test
    public void shouldCVCOneDigitCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCOneDigit());
        form.incorrectCodeVisible();
    }

    //Введем 2 цифры
    @Test
    public void shouldCVCTwoDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCTwoDigits());
        form.incorrectCodeVisible();
    }

    //Введем 4 цифры
    @Test
    public void shouldCVCFourDigitsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCFourDigits());
        form.incorrectCodeVisible();
    }

    //Введем специальные символы
    @Test
    public void shouldCVCSpecialSymbolsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCSpecialSymbols());
        form.incorrectCodeVisible();
    }

    //Введем код кириллицей
    @Test
    public void shouldCVCCyrillicCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCCyrillic());
        form.incorrectCodeVisible();
    }

    //Введем код латиницей
    @Test
    public void shouldCVCLatinCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCLatin());
        form.incorrectCodeVisible();
    }

    //Введем иероглифы
    @Test
    public void shouldCVCHieroglyphsCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.completedForm(DataHelper.getCVCHieroglyphs());
        form.incorrectCodeVisible();
    }

    //Оставим незаполненную форму
    @Test
    void shouldFormEmptyCredit() {
        var homepage = new HomePage();
        homepage.buyByCreditCard();
        var form = new CreditPage();
        form.emptyForm();
    }
}
