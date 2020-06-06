package services.creators;

import models.UserModel;

public class UserCreator {
    private static final String TEST_NAME = "test.name";
    private static final String TEST_EMAIL = "test.email";
    private static final String TEST_PHONE = "test.phone";
    private static final String TEST_PASSWORD = "test.password";

    private UserCreator() {
    }

    public static UserModel withValidUserDataAndWithoutPassword() {
        return new UserModel(ConfigurationReader.getValue(TEST_NAME), ConfigurationReader.getValue(TEST_EMAIL),
                ConfigurationReader.getValue(TEST_PHONE));
    }

    public static UserModel withValidUserData() {
        return new UserModel(ConfigurationReader.getValue(TEST_NAME), ConfigurationReader.getValue(TEST_EMAIL),
                ConfigurationReader.getValue(TEST_PHONE), ConfigurationReader.getValue(TEST_PASSWORD));
    }

    public static UserModel withEmptyUserData() {
        return new UserModel("", "", "");
    }

}
