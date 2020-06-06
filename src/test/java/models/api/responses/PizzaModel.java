package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class PizzaModel {
    private int id;
    private String category;
    @JsonProperty("category_id")
    private int categoryId;
    private String name;
    private double price;
    @JsonProperty("is_stuffed_crust")
    private int isStuffedCrust;
    @JsonProperty("stuffed_crust")
    private String stuffedCrust;
    private String image;
    private int count;
    private String dough;
    private int diameter;
    @JsonProperty("custom_name")
    private String customName;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Object> kind;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Object> size;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("removed_ingredients")
    private List<Object> removedIngredients;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("added_ingredients")
    private List<Object> addedIngredients;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<Object> getKind() {
        return kind;
    }

    public int getIsStuffedCrust() {
        return isStuffedCrust;
    }

    public String getStuffedCrust() {
        return stuffedCrust;
    }

    public String getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public List<Object> getSize() {
        return size;
    }

    public String getDough() {
        return dough;
    }

    public int getDiameter() {
        return diameter;
    }

    public List<Object> getRemovedIngredients() {
        return removedIngredients;
    }

    public List<Object> getAddedIngredients() {
        return addedIngredients;
    }

    public String getCustomName() {
        return customName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaModel that = (PizzaModel) o;
        return id == that.id &&
                categoryId == that.categoryId &&
                Double.compare(that.price, price) == 0 &&
                isStuffedCrust == that.isStuffedCrust &&
                count == that.count &&
                diameter == that.diameter &&
                Objects.equals(category, that.category) &&
                Objects.equals(name, that.name) &&
                Objects.equals(kind, that.kind) &&
                Objects.equals(stuffedCrust, that.stuffedCrust) &&
                Objects.equals(image, that.image) &&
                Objects.equals(size, that.size) &&
                Objects.equals(dough, that.dough) &&
                Objects.equals(removedIngredients, that.removedIngredients) &&
                Objects.equals(addedIngredients, that.addedIngredients) &&
                Objects.equals(customName, that.customName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, categoryId, name, price, kind, isStuffedCrust, stuffedCrust, image, count, size, dough, diameter, removedIngredients, addedIngredients, customName);
    }

    @Override
    public String toString() {
        return "PizzaItemModel{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", kind=" + kind +
                ", isStuffedCrust=" + isStuffedCrust +
                ", stuffedCrust='" + stuffedCrust + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", size=" + size +
                ", dough='" + dough + '\'' +
                ", diameter=" + diameter +
                ", removedIngredients=" + removedIngredients +
                ", addedIngredients=" + addedIngredients +
                ", customName='" + customName + '\'' +
                '}';
    }
}

