package models;

import java.util.Objects;

public class PizzaModel {
    private String pizzaName;
    private String pizzaType;
    private String pizzaSize;

    public PizzaModel(String pizzaName, String pizzaType, String pizzaSize) {
        this.pizzaName = pizzaName;
        this.pizzaType = pizzaType;
        this.pizzaSize = pizzaSize;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaModel model = (PizzaModel) o;
        return Objects.equals(pizzaName, model.pizzaName) &&
                Objects.equals(pizzaType, model.pizzaType) &&
                Objects.equals(pizzaSize, model.pizzaSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaName, pizzaType, pizzaSize);
    }

    @Override
    public String toString() {
        return "PizzaModel{" +
                "pizzaName='" + pizzaName + '\'' +
                ", pizzaType='" + pizzaType + '\'' +
                ", pizzaSize='" + pizzaSize + '\'' +
                '}';
    }
}
