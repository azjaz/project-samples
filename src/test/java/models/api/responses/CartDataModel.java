package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class CartDataModel {
    @JsonProperty("unauthorized_token")
    private String unauthorizedToken;
    @JsonProperty("cart_id")
    private int cartId;
    private double price;
    @JsonProperty("discount_price")
    private double discountPrice;
    @JsonProperty("cash_back")
    private double cashBack;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("sailplay_discount")
    private int sailPlayDiscount;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<CompositionModel> composition;

    public String getUnauthorizedToken() {
        return unauthorizedToken;
    }

    public int getCartId() {
        return cartId;
    }

    public List<CompositionModel> getComposition() {
        return composition;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public double getCashBack() {
        return cashBack;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getSailPlayDiscount() {
        return sailPlayDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDataModel that = (CartDataModel) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.discountPrice, discountPrice) == 0 &&
                Double.compare(that.cashBack, cashBack) == 0 &&
                stockCode == that.stockCode &&
                sailPlayDiscount == that.sailPlayDiscount &&
                Objects.equals(unauthorizedToken, that.unauthorizedToken) &&
                Objects.equals(cartId, that.cartId) &&
                Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unauthorizedToken, cartId, composition, price, discountPrice, cashBack, stockCode, sailPlayDiscount);
    }

    @Override
    public String toString() {
        return "AddToCartModel{" +
                "unauthorizedToken='" + unauthorizedToken + '\'' +
                ", cartId='" + cartId + '\'' +
                ", composition=" + composition +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", cashBack=" + cashBack +
                ", stockCode=" + stockCode +
                ", sailPlayDiscount=" + sailPlayDiscount +
                '}';
    }
}
