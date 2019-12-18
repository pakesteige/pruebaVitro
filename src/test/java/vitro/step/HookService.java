package vitro.step;

import cucumber.api.Scenario;
import vitro.utilidad.Base;
import vitro.utilidad.UtilSelenium;

/**
 * Clase HookService
 * Tiene la descripción detallada de los pasos que se realizan en la clase Hook
 *
 * @author paco
 * @version 1.0
 */
public class HookService extends Base {

    private UtilSelenium utilSelenium;

    /**
     * Inicializa el webdriver con el navegador que pongamos en el scenario outline de la feature
     * @param scenario nombre del escenario de la feature, de aquí se obtiene el valor del navegador
     * @return utilSelenium
     */
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

    /**
     * Cierra el webdriver
     */
    public void cerrarDriver() {
        utilSelenium.cerrarDriver();
    }
}
