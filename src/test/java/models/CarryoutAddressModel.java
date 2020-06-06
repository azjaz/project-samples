package models;

import java.util.Objects;

public class CarryoutAddressModel {
    private String address;
    private String subway;
    private String schedule;
    private String storeNumber;

    public CarryoutAddressModel(String address, String subway, String schedule) {
        this.address = address;
        this.subway = subway;
        this.schedule = schedule;
    }

    public CarryoutAddressModel(String address, String subway, String schedule, String storeNumber) {
        this.address = address;
        this.subway = subway;
        this.schedule = schedule;
        this.storeNumber = storeNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSubway() {
        return subway;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarryoutAddressModel that = (CarryoutAddressModel) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(subway, that.subway) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(storeNumber, that.storeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, subway, schedule, storeNumber);
    }

    @Override
    public String toString() {
        return "CarryoutAddressModel{" +
                "address='" + address + '\'' +
                ", subway='" + subway + '\'' +
                ", schedule='" + schedule + '\'' +
                ", storeNumber='" + storeNumber + '\'' +
                '}';
    }
}
