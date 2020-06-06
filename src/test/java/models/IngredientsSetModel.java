package models;

import java.util.Objects;

public class IngredientsSetModel {
    private String typeCheese;
    private String typeMeat;
    private String typeVegetables;
    private String cheeseIngredientFirst;
    private String cheeseIngredientSecond;
    private String meatIngredientFirst;
    private String meatIngredientSecond;
    private String vegetablesIngredientFirst;
    private String vegetablesIngredientSecond;

    public IngredientsSetModel(String typeCheese,
                               String typeMeat,
                               String typeVegetables,
                               String cheeseIngredientFirst,
                               String cheeseIngredientSecond,
                               String meatIngredientFirst,
                               String meatIngredientSecond,
                               String vegetablesIngredientFirst,
                               String vegetablesIngredientSecond) {
        this.typeCheese = typeCheese;
        this.typeMeat = typeMeat;
        this.typeVegetables = typeVegetables;
        this.cheeseIngredientFirst = cheeseIngredientFirst;
        this.cheeseIngredientSecond = cheeseIngredientSecond;
        this.meatIngredientFirst = meatIngredientFirst;
        this.meatIngredientSecond = meatIngredientSecond;
        this.vegetablesIngredientFirst = vegetablesIngredientFirst;
        this.vegetablesIngredientSecond = vegetablesIngredientSecond;
    }

    public String getTypeCheese() {
        return typeCheese;
    }

    public String getTypeMeat() {
        return typeMeat;
    }

    public String getTypeVegetables() {
        return typeVegetables;
    }

    public String getCheeseIngredientFirst() {
        return cheeseIngredientFirst;
    }

    public String getCheeseIngredientSecond() {
        return cheeseIngredientSecond;
    }

    public String getMeatIngredientFirst() {
        return meatIngredientFirst;
    }

    public String getMeatIngredientSecond() {
        return meatIngredientSecond;
    }

    public String getVegetablesIngredientFirst() {
        return vegetablesIngredientFirst;
    }

    public String getVegetablesIngredientSecond() {
        return vegetablesIngredientSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsSetModel that = (IngredientsSetModel) o;
        return Objects.equals(typeCheese, that.typeCheese) &&
                Objects.equals(typeMeat, that.typeMeat) &&
                Objects.equals(typeVegetables, that.typeVegetables) &&
                Objects.equals(cheeseIngredientFirst, that.cheeseIngredientFirst) &&
                Objects.equals(cheeseIngredientSecond, that.cheeseIngredientSecond) &&
                Objects.equals(meatIngredientFirst, that.meatIngredientFirst) &&
                Objects.equals(meatIngredientSecond, that.meatIngredientSecond) &&
                Objects.equals(vegetablesIngredientFirst, that.vegetablesIngredientFirst) &&
                Objects.equals(vegetablesIngredientSecond, that.vegetablesIngredientSecond);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeCheese,
                typeMeat,
                typeVegetables,
                cheeseIngredientFirst,
                cheeseIngredientSecond,
                meatIngredientFirst,
                meatIngredientSecond,
                vegetablesIngredientFirst,
                vegetablesIngredientSecond);
    }

    @Override
    public String toString() {
        return "IngredientsSetModel{" +
                "typeCheese='" + typeCheese + '\'' +
                ", typeMeat='" + typeMeat + '\'' +
                ", typeVegetables='" + typeVegetables + '\'' +
                ", cheeseIngredientFirst='" + cheeseIngredientFirst + '\'' +
                ", cheeseIngredientSecond='" + cheeseIngredientSecond + '\'' +
                ", meatIngredientFirst='" + meatIngredientFirst + '\'' +
                ", meatIngredientSecond='" + meatIngredientSecond + '\'' +
                ", vegetablesIngredientFirst='" + vegetablesIngredientFirst + '\'' +
                ", vegetablesIngredientSecond='" + vegetablesIngredientSecond + '\'' +
                '}';
    }
}
