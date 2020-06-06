package services.creators;

import models.CarryoutAddressModel;
import models.DeliveryAddressModel;

public class AddressCreator {
    private static final String DELIVERY_ADDRESS = "test.deliveryAddress";
    private static final String INVALID_DELIVERY_ADDRESS = "test.invalidDeliveryAddress";
    private static final String FLAT = "test.flat";
    private static final String FLOOR = "test.floor";
    private static final String CARRYOUT_ADDRESS = "test.carryoutAddress";
    private static final String SUBWAY = "test.subway";
    private static final String SCHEDULE = "test.schedule";
    private static final String STORE_NUMBER = "test.storeNumber";

    private AddressCreator() {
    }

    public static DeliveryAddressModel withDeliveryAddress() {
        return new DeliveryAddressModel(ConfigurationReader.getValue(DELIVERY_ADDRESS),
                ConfigurationReader.getValue(FLAT), ConfigurationReader.getValue(FLOOR));
    }

    public static CarryoutAddressModel withCarryoutAddressAndWithoutStoreNumber() {
        return new CarryoutAddressModel(ConfigurationReader.getValue(CARRYOUT_ADDRESS), ConfigurationReader.getValue(SUBWAY),
                ConfigurationReader.getValue(SCHEDULE));
    }

    public static CarryoutAddressModel withCarryoutAddress() {
        return new CarryoutAddressModel(ConfigurationReader.getValue(CARRYOUT_ADDRESS), ConfigurationReader.getValue(SUBWAY),
                ConfigurationReader.getValue(SCHEDULE), ConfigurationReader.getValue(STORE_NUMBER));
    }

    public static DeliveryAddressModel withInvalidDeliveryAddress() {
        return new DeliveryAddressModel(ConfigurationReader.getValue(INVALID_DELIVERY_ADDRESS),
                ConfigurationReader.getValue(FLAT), ConfigurationReader.getValue(FLOOR));
    }

}
