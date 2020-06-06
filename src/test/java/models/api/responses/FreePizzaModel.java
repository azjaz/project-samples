package models.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class FreePizzaModel {
    private int id;
    private String category;
    @JsonProperty("category_id")
    private int categoryId;
    private String name;
    private String article;
    private String image;
    @JsonProperty("image_full")
    private String imageFull;
    @JsonProperty("short_description")
    private String shortDescription;
    @JsonProperty("short_descEn")
    private String shortDescEn;
    private String description;
    private String alias;
    private String data;
    @JsonProperty("data_en")
    private String dataEn;
    @JsonProperty("show_ingredients_button")
    private float showIngredientsButton;
    List<Object> types;
    List<Object> variations;

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

    public String getArticle() {
        return article;
    }

    public String getImage() {
        return image;
    }

    public String getImageFull() {
        return imageFull;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getShortDescEn() {
        return shortDescEn;
    }

    public String getDescription() {
        return description;
    }

    public String getAlias() {
        return alias;
    }

    public String getData() {
        return data;
    }

    public String getDataEn() {
        return dataEn;
    }

    public float getShowIngredientsButton() {
        return showIngredientsButton;
    }

    public List<Object> getTypes() {
        return types;
    }

    public List<Object> getVariations() {
        return variations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreePizzaModel that = (FreePizzaModel) o;
        return id == that.id &&
                categoryId == that.categoryId &&
                Float.compare(that.showIngredientsButton, showIngredientsButton) == 0 &&
                Objects.equals(category, that.category) &&
                Objects.equals(name, that.name) &&
                Objects.equals(article, that.article) &&
                Objects.equals(image, that.image) &&
                Objects.equals(imageFull, that.imageFull) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(shortDescEn, that.shortDescEn) &&
                Objects.equals(description, that.description) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(data, that.data) &&
                Objects.equals(dataEn, that.dataEn) &&
                Objects.equals(types, that.types) &&
                Objects.equals(variations, that.variations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, categoryId, name, article, image, imageFull, shortDescription, shortDescEn, description, alias, data, dataEn, showIngredientsButton, types, variations);
    }

    @Override
    public String toString() {
        return "PizzaModel{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", article='" + article + '\'' +
                ", image='" + image + '\'' +
                ", imageFull='" + imageFull + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", shortDescEn='" + shortDescEn + '\'' +
                ", description='" + description + '\'' +
                ", alias='" + alias + '\'' +
                ", data='" + data + '\'' +
                ", dataEn='" + dataEn + '\'' +
                ", showIngredientsButton=" + showIngredientsButton +
                ", types=" + types +
                ", variations=" + variations +
                '}';
    }
}
