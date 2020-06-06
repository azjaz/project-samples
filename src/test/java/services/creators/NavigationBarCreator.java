package services.creators;

import models.NavigationBarModel;

public class NavigationBarCreator {
    private static final String TEST_DATA_CONTACTS = "test.contacts";
    private static final String TEST_DATA_LANGUAGE = "test.language";

    private NavigationBarCreator() {
    }

    public static NavigationBarModel create() {
        return new NavigationBarModel(ConfigurationReader.getValue(TEST_DATA_LANGUAGE), ConfigurationReader.getValue(TEST_DATA_CONTACTS));
    }
}
