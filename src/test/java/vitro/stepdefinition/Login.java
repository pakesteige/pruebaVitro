package vitro.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import vitro.step.LoginService;
import vitro.utilidad.UtilSelenium;

import static vitro.utilidad.UtilSelenium.getUtilSelenium;

public class Login {

    private LoginService loginService = new LoginService();
    private UtilSelenium utilSelenium = getUtilSelenium();

    @Given("La aplicacion (.*) con el identificador (.*) usuario (.*) y clave (.*)$")
    public void la_aplicacion_con_el_identificador_usuario_y_clave(String app, String identifier, String user, String pass) throws Throwable {
        loginService.login(utilSelenium, app, identifier, user, pass);
    }

    @And("Cerrar sesion$")
    public void cerrar_sesion() {
        loginService.logout(utilSelenium);
    }
}
