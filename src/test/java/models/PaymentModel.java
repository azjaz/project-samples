package models;

import java.util.Objects;

public class PaymentModel {
    private String change;
    private String cardNumber;
    private String expirationDate;
    private String codeCVV;
    private String cardholderName;

    public PaymentModel(String change) {
        this.change = change;
    }

    public PaymentModel(String cardNumber, String expirationDate, String codeCVV, String cardholderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.codeCVV = codeCVV;
        this.cardholderName = cardholderName;
    }

    public String getChange() {
        return change;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCodeCVV() {
        return codeCVV;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentModel that = (PaymentModel) o;
        return Objects.equals(change, that.change) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(codeCVV, that.codeCVV) &&
                Objects.equals(cardholderName, that.cardholderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(change, cardNumber, expirationDate, codeCVV, cardholderName);
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "change='" + change + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", codeCVV='" + codeCVV + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                '}';
    }
}
