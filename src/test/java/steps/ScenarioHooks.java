package steps;

import drivers.DriverSingleton;
import io.cucumber.java.After;

public class ScenarioHooks {

    @After
    public void browserTermination() {
        DriverSingleton.closeDriver();
    }
}
