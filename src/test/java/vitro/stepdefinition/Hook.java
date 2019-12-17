package vitro.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import vitro.step.HookService;

public class Hook {

    private HookService hookService = new HookService();

    @Before
    public void iniciarDriver(Scenario scenario) {
        hookService.iniciarDriver(scenario);
    }

    @After()
    public void cerrarDriver() {
        hookService.cerrarDriver();
    }

}
