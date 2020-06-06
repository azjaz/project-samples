package services.creators;

import utils.UTF8Control;
import java.util.ResourceBundle;

public class ConfigurationReader {
    private ConfigurationReader() {
    }

    private static ResourceBundle resourceBundle = ResourceBundle
            .getBundle(System.getProperty("environment", "qa"), new UTF8Control());

    public static String getValue(String key) {
        return resourceBundle.getString(key);
    }

}
