package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static java.lang.String.format;

public class DataHelper {
    public static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return ("1111 2222 3333 4444");
    }

    public static String getDeclinedCardNumber() {
        return ("5555 6666 7777 8888");
    }

    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return format("%02d", localDate.getMonthValue());
    }

    public static String getValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidHolder() {
        return DataHelper.faker.name().firstName();
    }

    public static String getValidCVC() {
        int result = (int) (Math.random() * 999) + 1;
        return String.format("%03d", result);
    }

    //Оплата по карте APPROVED
    public static CardInfo paymentByCardApproved() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Оплата по карте DECLINED
    public static CardInfo paymentByCardDECLINED() {
        return new CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Заполнение поля "Номер карты":

    //Остаить незаполненное поле
    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 1 цифру
    public static CardInfo getCardNumberOneDigit() {
        return new CardInfo("4", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 10 цифр
    public static CardInfo getCardNumberTenDigits() {
        return new CardInfo("1111222233", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 15 цифр
    public static CardInfo getCardNumberFifteenDigits() {
        return new CardInfo("111122223333444", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 17 цифр
    public static CardInfo getCardNumberSeventeenDigits() {
        return new CardInfo("11112222333344441", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести незарегистрированный номер в базе данных
    public static CardInfo getCardNumberNotRegistered() {
        return new CardInfo("9999888899998888", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести специальные символы
    public static CardInfo getCardNumberSpecialSymbols() {
        return new CardInfo("@#$%^", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести номер карты кириллицей
    public static CardInfo getCardNumberCyrillic() {
        return new CardInfo("сентябрь", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести номер карты латиницей
    public static CardInfo getCardNumberLatin() {
        return new CardInfo("september", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести иероглифы
    public static CardInfo getCardNumberHieroglyphs() {
        return new CardInfo("九月", getValidMonth(), getValidYear(), getValidHolder(), getValidCVC());
    }

    //Заполнение поля "Месяц":

    //Оставить незаполненное поле
    public static CardInfo getMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), "", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести два нуля
    public static CardInfo getMonthTwoZero() {
        return new CardInfo(getApprovedCardNumber(), "00", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 1 цифру - ноль
    public static CardInfo getMonthOneDigitWithZero() {
        return new CardInfo(getApprovedCardNumber(), "0", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 1 цифру - не ноль
    public static CardInfo getMonthOneDigit() {
        return new CardInfo(getApprovedCardNumber(), "9", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 2 цифры, невалидный месяц (граничное значение)
    public static CardInfo getMonthNoValidTwoDigitsLimit() {
        return new CardInfo(getApprovedCardNumber(), "13", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 2 цифры, невалидный месяц
    public static CardInfo getMonthNoValidTwoDigits() {
        return new CardInfo(getApprovedCardNumber(), "15", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести 3 цифры
    public static CardInfo getMonthThreeDigits() {
        return new CardInfo(getApprovedCardNumber(), "111", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести специальные символы
    public static CardInfo getMonthSpecialSymbols() {
        return new CardInfo(getApprovedCardNumber(), "%^", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Заполнить поле кириллицей
    public static CardInfo getMonthCyrillic() {
        return new CardInfo(getApprovedCardNumber(), "сентябрь", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Заполнить поле латиницей
    public static CardInfo getMonthLatin() {
        return new CardInfo(getApprovedCardNumber(), "september", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Ввести иероглифы
    public static CardInfo getMonthHieroglyphs() {
        return new CardInfo(getApprovedCardNumber(), "九月", getValidYear(), getValidHolder(), getValidCVC());
    }

    //Заполнение поля "Год":

    //Оставить незаполненное поле
    public static CardInfo getYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "", getValidHolder(), getValidCVC());
    }

    //Ввести 1 цифру - ноль
    public static CardInfo getYearOneDigitZero() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "0", getValidHolder(), getValidCVC());
    }

    //Ввести 1 цифру
    public static CardInfo getYearOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "2", getValidHolder(), getValidCVC());
    }

    //Ввести 2 цифры, 2030 год
    public static CardInfo getYearNoValidOne() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "30", getValidHolder(), getValidCVC());
    }

    //Ввести 2 цифры, 2023 год
    public static CardInfo getYearNoValidTwo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "23", getValidHolder(), getValidCVC());
    }

    //Ввести 3 цифры
    public static CardInfo getYearThreeDigits() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "224", getValidHolder(), getValidCVC());
    }

    //Ввести специальные символы
    public static CardInfo getYearSpecialSymbols() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "%^", getValidHolder(), getValidCVC());
    }

    //Ввести год кириллицей
    public static CardInfo getYearCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "сентябрь", getValidHolder(), getValidCVC());
    }

    //Ввести год латиницей
    public static CardInfo getYearLatin() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "september", getValidHolder(), getValidCVC());
    }

    //Ввести иероглифы
    public static CardInfo getYearHieroglyphs() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "九月", getValidHolder(), getValidCVC());
    }

    //Заполнение поля "Владелец":

    //Оставить незаполненное поле
    public static CardInfo getHolderEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "", getValidCVC());
    }

    //Ввести 1 букву
    public static CardInfo getHolderOneLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "V", getValidCVC());
    }

    //Ввести 36 букв (максимум 35)
    public static CardInfo getHolderThirtySixLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", getValidCVC());
    }

    //Заполнить поле кирилицей
    public static CardInfo getHolderCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Виктория Иванова", getValidCVC());
    }

    //Заполнить поле цифрами
    public static CardInfo getHolderDigits() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "123456789", getValidCVC());
    }

    //Заполнить поле специальными символами
    public static CardInfo getHolderSpecialSymbols() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "@#$%^", getValidCVC());
    }

    //Заполнить поле иероглифами
    public static CardInfo getHolderHieroglyphs() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "九月", getValidCVC());
    }

    //Заполнение поля "CVC/CVV":

    //Оставить незаполненное поле
    public static CardInfo getCVCEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "");
    }

    //Ввести 1 цифру
    public static CardInfo getCVCOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "1");
    }

    //Ввести 2 цифры
    public static CardInfo getCVCTwoDigits() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "12");
    }

    //Ввести 4 цифры
    public static CardInfo getCVCFourDigits() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "1234");
    }

    //Ввести специальные символы
    public static CardInfo getCVCSpecialSymbols() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "%^%");
    }

    //Ввести код кириллицей
    public static CardInfo getCVCCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "сентябрь");
    }

    //Ввести код латиницей
    public static CardInfo getCVCLatin() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "september");
    }

    //Ввести иероглифы
    public static CardInfo getCVCHieroglyphs() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), "九月");
    }


    //Реквизиты карты:
    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String holder;
        String CVC;
    }
}