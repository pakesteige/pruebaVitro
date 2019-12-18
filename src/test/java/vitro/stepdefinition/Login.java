package vitro.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import vitro.step.LoginService;
import vitro.utilidad.UtilSelenium;

import static vitro.utilidad.UtilSelenium.getUtilSelenium;

/**
 * Clase login
 *
 * Contiene los pasos para realizar login y logout
 *
 * @author paco
 * @version 1.0
 */
public class Login {

    private LoginService loginService = new LoginService();
    private UtilSelenium utilSelenium = getUtilSelenium();

    /**
     * Realizar el login en la aplicación
     * @param app: url de la aplicación
     * @param identifier: identificador utilizado para el login
     * @param user: usuario para hacer login
     * @param pass: contraseña para hacer login
     * @throws Throwable
     */
    @Given("La aplicacion (.*) con el identificador (.*) usuario (.*) y clave (.*)$")
    public void login(String app, String identifier, String user, String pass) throws Throwable {
        loginService.login(utilSelenium, app, identifier, user, pass);
    }

    /**
     * Finaliza la sesión en la aplicación
     */
    @And("Cerrar sesion$")
    public void logout() {
        loginService.logout(utilSelenium);
    }
}
