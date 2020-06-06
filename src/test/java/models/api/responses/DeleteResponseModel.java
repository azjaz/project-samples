package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteResponseModel {
    @JsonProperty("unauthorized_token")
    private String unauthorizedToken;
    @JsonProperty("cart_id")
    private Integer cartId;
    private List<Object> composition = null;
    private Integer price;
    @JsonProperty("discount_price")
    private Integer discountPrice;
    @JsonProperty("cash_back")
    private Integer cashBack;
    @JsonProperty("stock_code")
    private Boolean stockCode;
    @JsonProperty("sailplay_discount")
    private Integer sailplayDiscount;

    public String getUnauthorizedToken() {
        return unauthorizedToken;
    }

    public Integer getCartId() {
        return cartId;
    }
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Object> getComposition() {
        return composition;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public Integer getCashBack() {
        return cashBack;
    }

    public Boolean getStockCode() {
        return stockCode;
    }

    public Integer getSailplayDiscount() {
        return sailplayDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteResponseModel that = (DeleteResponseModel) o;
        return Objects.equals(unauthorizedToken, that.unauthorizedToken) &&
                Objects.equals(cartId, that.cartId) &&
                Objects.equals(composition, that.composition) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discountPrice, that.discountPrice) &&
                Objects.equals(cashBack, that.cashBack) &&
                Objects.equals(stockCode, that.stockCode) &&
                Objects.equals(sailplayDiscount, that.sailplayDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unauthorizedToken, cartId, composition, price, discountPrice, cashBack, stockCode, sailplayDiscount);
    }

    @Override
    public String toString() {
        return "DeleteResponseModel{" +
                "unauthorizedToken='" + unauthorizedToken + '\'' +
                ", cartId=" + cartId +
                ", composition=" + composition +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", cashBack=" + cashBack +
                ", stockCode=" + stockCode +
                ", sailplayDiscount=" + sailplayDiscount +
                '}';
    }
}
