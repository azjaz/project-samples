package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class CompositionModel {
    private String hash;
    private String type;
    private int count;
    private String status;
    private double price;
    private double discountPrice;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<PizzaModel> item;

    public String getHash() {
        return hash;
    }

    public List<PizzaModel> getItem() {
        return item;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    @JsonProperty("discount_price")
    public double getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionModel that = (CompositionModel) o;
        return count == that.count &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.discountPrice, discountPrice) == 0 &&
                Objects.equals(hash, that.hash) &&
                Objects.equals(item, that.item) &&
                Objects.equals(type, that.type) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, item, type, count, status, price, discountPrice);
    }

    @Override
    public String toString() {
        return "CompositionModel{" +
                "hash='" + hash + '\'' +
                ", item=" + item +
                ", type='" + type + '\'' +
                ", count=" + count +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
