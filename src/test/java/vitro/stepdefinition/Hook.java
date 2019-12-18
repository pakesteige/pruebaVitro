package vitro.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import vitro.step.HookService;

/**
 * Clase Hook
 *
 * Contiene los métodos que se tienen que ejecutar siempre por cada feature
 *
 * @author paco
 * @version 1.0
 */
public class Hook {

    private HookService hookService = new HookService();

    /**
     * Inicializa el webdriver y el log, se ejecuta primero
     * @param scenario: nombre del escenario del feature
     */
    @Before
    public void iniciarDriver(Scenario scenario) {
        hookService.iniciarDriver(scenario);
    }

    /**
     * Cierra el webdriver, se ejecuta al final
     */
    @After()
    public void cerrarDriver() {
        hookService.cerrarDriver();
    }

}
