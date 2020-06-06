package models;

import java.util.Objects;

public class DeliveryAddressModel {
    private String address;
    private String flat;
    private String floor;

    public DeliveryAddressModel(String address, String flat, String floor) {
        this.address = address;
        this.flat = flat;
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public String getFlat() {
        return flat;
    }

    public String getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAddressModel that = (DeliveryAddressModel) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(flat, that.flat) &&
                Objects.equals(floor, that.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, flat, floor);
    }

    @Override
    public String toString() {
        return "DeliveryAddressModel{" +
                "address='" + address + '\'' +
                ", flat='" + flat + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }

}
