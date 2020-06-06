package models;

import java.util.Objects;

public class ProductWidgetModel {

    private String productType;
    private String productName;
    private String extraOption;

    public ProductWidgetModel(String productType, String productName, String extraOption) {
        this.productType = productType;
        this.productName = productName;
        this.extraOption = extraOption;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductName() {
        return productName;
    }

    public String getExtraOption() {
        return extraOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWidgetModel model = (ProductWidgetModel) o;
        return Objects.equals(productType, model.productType) &&
                Objects.equals(productName, model.productName) &&
                Objects.equals(extraOption, model.extraOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, productName, extraOption);
    }

    @Override
    public String toString() {
        return "ProductWidgetModel{" +
                "productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", extraOption='" + extraOption + '\'' +
                '}';
    }
}
