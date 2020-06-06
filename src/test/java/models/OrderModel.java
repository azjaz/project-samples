package models;

import java.util.Objects;

public class OrderModel extends DeliveryAddressModel {
    private String name;
    private String email;

    public OrderModel(String name, String email, String address, String flat, String floor) {
        super(address, flat, floor);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
