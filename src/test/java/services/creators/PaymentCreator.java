package services.creators;

import models.PaymentModel;

public class PaymentCreator {
    private static final String CHANGE = "test.change";
    private static final String CARD_NUMBER = "test.cardNumber";
    private static final String EXPIRATION_DATA = "test.expirationData";
    private static final String CODE_CVV = "test.codeCVV";
    private static final String CARDHOLDER_NAME = "test.cardholderName";

    private PaymentCreator() {
    }

    public static PaymentModel createFromCash() {
        return new PaymentModel(ConfigurationReader.getValue(CHANGE));
    }

    public static PaymentModel createFromOnlinePayment() {
        return new PaymentModel(ConfigurationReader.getValue(CARD_NUMBER), ConfigurationReader.getValue(EXPIRATION_DATA),
                ConfigurationReader.getValue(CODE_CVV), ConfigurationReader.getValue(CARDHOLDER_NAME));
    }
}
