package models;

import java.util.Objects;

public class BonusPizzaModel {
    private String pizzaName;
    private String pizzaSize;

    public BonusPizzaModel(String pizzaName, String pizzaSize) {
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusPizzaModel that = (BonusPizzaModel) o;
        return Objects.equals(pizzaName, that.pizzaName) &&
                Objects.equals(pizzaSize, that.pizzaSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaName, pizzaSize);
    }

    @Override
    public String toString() {
        return "BonusPizzaModel{" +
                "pizzaName='" + pizzaName + '\'' +
                ", pizzaSize='" + pizzaSize + '\'' +
                '}';
    }
}
