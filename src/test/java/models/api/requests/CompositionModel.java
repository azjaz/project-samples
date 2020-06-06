package models.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class CompositionModel {
    @JsonProperty("good_id")
    private String goodId;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> ingredients;
    private String type;

    public CompositionModel(String goodId, List<String> ingredients, String type) {
        this.goodId = goodId;
        this.ingredients = ingredients;
        this.type = type;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionModel that = (CompositionModel) o;
        return Objects.equals(goodId, that.goodId) &&
                Objects.equals(ingredients, that.ingredients) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodId, ingredients, type);
    }

    @Override
    public String toString() {
        return "CompositionModel{" +
                "goodId='" + goodId + '\'' +
                ", ingredients=" + ingredients +
                ", type='" + type + '\'' +
                '}';
    }
}
