package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.restassured.response.Response;

import java.util.List;
import java.util.Objects;

public class ApplyPromoModel {
    private String code;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<PromoCodeDetailsModel> details;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<CartDataModel> cart;

    public String getCode() {
        return code;
    }

    public List<PromoCodeDetailsModel> getDetails() {
        return details;
    }

    public List<CartDataModel> getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplyPromoModel that = (ApplyPromoModel) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(details, that.details) &&
                Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, details, cart);
    }

    @Override
    public String toString() {
        return "ApplyPromoModel{" +
                "code='" + code + '\'' +
                ", details=" + details +
                ", cart=" + cart +
                '}';
    }

    public int getFreePizzaId(int item, Response response) {
        ApplyPromoModel applyPromoModel = response.as(ApplyPromoModel.class);
        return applyPromoModel.getDetails().get(0).getRewards().get(0).getGoods().get(item).getId();
    }
}
