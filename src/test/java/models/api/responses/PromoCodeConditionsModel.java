package models.api.responses;

import java.util.Objects;

public class PromoCodeConditionsModel {
    private String criteria;
    private double value;
    private String type;
    private boolean check;
    private String error;

    public String getCriteria() {
        return criteria;
    }

    public double getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public boolean isCheck() {
        return check;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoCodeConditionsModel that = (PromoCodeConditionsModel) o;
        return Double.compare(that.value, value) == 0 &&
                check == that.check &&
                Objects.equals(criteria, that.criteria) &&
                Objects.equals(type, that.type) &&
                Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteria, value, type, check, error);
    }

    @Override
    public String toString() {
        return "PromoCodeConditionsModel{" +
                "criteria='" + criteria + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", check=" + check +
                ", error='" + error + '\'' +
                '}';
    }
}
