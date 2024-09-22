package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;

public class CreditTest {

    @BeforeEach
    public void setUp() {
        String url = System.getProperty("sut.url");
        open(url);
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

    //Кредит по данным карты APPROVED
    @Test
    void shouldApprovedCardPaymentByCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.paymentByCardApproved());
        form.paymentApproved();
        Assertions.assertEquals("APPROVED", SQLHelper.getCreditPayment());
    }

    //Кредит по данным карты DECLINED
    @Test
    void shouldDeclinedCardPaymentByCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.paymentByCardDECLINED());
        form.paymentDeclined();
        Assertions.assertEquals("DECLINED", SQLHelper.getCreditPayment());
    }

    //Заполнение поля "Номер карты":

    //Оставить незаполненное поле
    @Test
    public void shouldCardNumberEmptyCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberEmpty());
        form.incorrectCardNumberVisible();
    }

    //Ввести 1 цифру
    @Test
    public void shouldCardNumberOneDigitCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberOneDigit());
        form.incorrectCardNumberVisible();
    }

    //Ввести 10 цифр
    @Test
    public void shouldCardNumberTenDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberTenDigits());
        form.incorrectCardNumberVisible();
    }

    //Ввести 15 цифр
    @Test
    public void shouldCardNumberFifteenDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberFifteenDigits());
        form.incorrectCardNumberVisible();
    }

    //Ввести 17 цифр
    @Test
    public void shouldCardNumberSeventeenDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberSeventeenDigits());
        form.paymentDeclined();
    }

    //Ввести незарегистрированный номер в базе данных
    @Test
    public void shouldCardNumberNotRegisteredCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberNotRegistered());
        form.paymentDeclined();
    }

    //Ввести специальные символы
    @Test
    public void shouldCardNumberSpecialSymbolsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberSpecialSymbols());
        form.incorrectCardNumberVisible();
    }

    //Ввести номер карты кириллицей
    @Test
    public void shouldCardNumberCyrillicCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberCyrillic());
        form.incorrectCardNumberVisible();
    }

    //Ввести номер карты латиницей
    @Test
    public void shouldCardNumberLatinCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberLatin());
        form.incorrectCardNumberVisible();
    }

    //Ввести иероглифы
    @Test
    public void shouldCardNumberHieroglyphsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCardNumberHieroglyphs());
        form.incorrectCardNumberVisible();
    }

//Заполнение поля "Месяц":

    //Оставить незаполненное поле
    @Test
    public void shouldMonthEmptyCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthEmpty());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести два нуля
    @Test
    public void shouldMonthTwoZeroCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthTwoZero());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести 1 цифру - ноль
    @Test
    public void shouldMonthOneDigitWithZeroCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthOneDigitWithZero());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести 1 цифру - не ноль
    @Test
    public void shouldMonthOneDigitCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthOneDigit());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести 2 цифры, невалидный месяц (граничное значение)
    @Test
    public void shouldMonthNoValidTwoDigitsLimitCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthNoValidTwoDigitsLimit());
        form.incorrectMonthVisible("Неверно указан срок действия карты");
    }

    //Ввести 2 цифры, невалидный месяц
    @Test
    public void shouldMonthNoValidTwoDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthNoValidTwoDigits());
        form.incorrectMonthVisible("Неверно указан срок действия карты");
    }

    //Ввести 3 цифры
    @Test
    public void shouldMonthThreeDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthThreeDigits());
    }

    //Ввести специальные символы
    @Test
    public void shouldMonthSpecialSymbolsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthSpecialSymbols());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Заполнить поле кириллицей
    @Test
    public void shouldMonthCyrillicCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthCyrillic());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Заполнить поле латиницей
    @Test
    public void shouldMonthLatinCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthLatin());
        form.incorrectMonthVisible("Неверный формат");
    }

    //Ввести иероглифы
    @Test
    public void shouldMonthHieroglyphsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getMonthHieroglyphs());
        form.incorrectMonthVisible("Неверный формат");
    }

//Заполнение поля "Год":

    //Оставить незаполненное поле
    @Test
    public void shouldYearEmptyCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearEmpty());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести 1 цифру - ноль
    @Test
    public void shouldYearOneDigitZeroCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearOneDigitZero());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести 1 цифру
    @Test
    public void shouldYearOneDigitCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearOneDigit());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести 2 цифры, 2030 год
    @Test
    public void shouldYearNoValidOneCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearNoValidOne());
        form.incorrectYearVisible("Неверно указан срок действия карты");
    }

    //Ввести 2 цифры, 2023 год, прошедший год
    @Test
    public void shouldYearNoValidTwoCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearNoValidTwo());
        form.incorrectYearVisible("Истёк срок действия карты");
    }

    //Ввести 3 цифры
    @Test
    public void shouldYearThreeDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearThreeDigits());
    }

    //Ввести специальные символы
    @Test
    public void shouldYearSpecialSymbolsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearSpecialSymbols());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести год кириллицей
    @Test
    public void shouldYearCyrillicCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearCyrillic());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести год латиницей
    @Test
    public void shouldYearLatinCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearLatin());
        form.incorrectYearVisible("Неверный формат");
    }

    //Ввести иероглифы
    @Test
    public void shouldYearHieroglyphsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getYearHieroglyphs());
        form.incorrectYearVisible("Неверный формат");
    }

    //Заполнение поля "Владелец":

    //Оставить незаполненное поле
    @Test
    public void shouldHolderEmptyCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderEmpty());
        form.incorrectHolderVisible();
    }

    //Ввести 1 букву
    @Test
    public void shouldHolderOneLetterCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderOneLetter());
        form.incorrectHolderVisible();
    }

    //Ввести 36 букв (максимум 35)
    @Test
    public void shouldHolderThirtySixLetterCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderThirtySixLetter());
    }

    //Заполнить поле кирилицей
    @Test
    public void shouldHolderCyrillicCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderCyrillic());
        form.incorrectHolderVisible();
    }

    //Заполнить поле цифрами
    @Test
    public void shouldHolderDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderDigits());
        form.incorrectHolderVisible();
    }

    //Заполнить поле специальными символами
    @Test
    public void shouldHolderSpecialSymbolsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderSpecialSymbols());
        form.incorrectHolderVisible();
    }

    //Заполнить поле иероглифами
    @Test
    public void shouldHolderHieroglyphsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getHolderHieroglyphs());
        form.incorrectHolderVisible();
    }

//Заполнение поля "CVC/CVV":

    //Оставить незаполненное поле
    @Test
    public void shouldCVCEmptyCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCEmpty());
        form.incorrectCodeVisible();
    }

    //Ввести 1 цифру
    @Test
    public void shouldCVCOneDigitCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCOneDigit());
        form.incorrectCodeVisible();
    }

    //Ввести 2 цифры
    @Test
    public void shouldCVCTwoDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCTwoDigits());
        form.incorrectCodeVisible();
    }

    //Ввести 4 цифры
    @Test
    public void shouldCVCFourDigitsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCFourDigits());
    }

    //Ввести специальные символы
    @Test
    public void shouldCVCSpecialSymbolsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCSpecialSymbols());
        form.incorrectCodeVisible();
    }

    //Ввести код кириллицей
    @Test
    public void shouldCVCCyrillicCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCCyrillic());
        form.incorrectCodeVisible();
    }

    //Ввести код латиницей
    @Test
    public void shouldCVCLatinCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCLatin());
        form.incorrectCodeVisible();
    }

    //Ввести иероглифы
    @Test
    public void shouldCVCHieroglyphsCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.completedForm(DataHelper.getCVCHieroglyphs());
        form.incorrectCodeVisible();
    }

    //Оставим незаполненную форму
    @Test
    void shouldFormEmptyCredit() {
        var homepage = new HomePage();
        var form = homepage.buyByCreditCard();
        form.emptyForm();
    }
}
