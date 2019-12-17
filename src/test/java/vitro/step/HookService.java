package vitro.step;

import cucumber.api.Scenario;
import vitro.utilidad.Base;
import vitro.utilidad.UtilSelenium;

public class HookService extends Base {

    private UtilSelenium utilSelenium;

    public UtilSelenium iniciarDriver(Scenario scenario) {
        if (scenario.getName().contains("firefox")) {
            utilSelenium = UtilSelenium.getInstancia(scenario.getName(), "firefox", null);
        } else if (scenario.getName().contains("internet explorer")) {
            utilSelenium = UtilSelenium.getInstancia(scenario.getName(), "internet explorer", null);
        } else {
            utilSelenium = UtilSelenium.getInstancia(scenario.getName(), "chrome", null);
        }
        return utilSelenium;
    }

    public void cerrarDriver() {
        utilSelenium.cerrarDriver();
    }
}
