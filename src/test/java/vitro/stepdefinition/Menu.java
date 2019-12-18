package vitro.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import vitro.step.MenuService;
import vitro.utilidad.UtilSelenium;

import static vitro.utilidad.UtilSelenium.getUtilSelenium;

/**
 * Clase Menu
 *
 * Navega por los diferentes menu y widget de la aplicación
 *
 * @author paco
 * @version 1.0
 */
public class Menu {

    private MenuService menuService = new MenuService();
    private UtilSelenium utilSelenium = getUtilSelenium();

    /**
     * Navega al submenú del menú que pasemos por parámetros
     * @param menu: menú
     * @param option: submenú
     * @throws Throwable
     */
    @When("Navego al menu (.*) y pulso la opcion (.*)$")
    public void navegarSubmenu(String menu, String option) throws Throwable {
        menuService.navegar(utilSelenium, menu, option);
    }

    /**
     * Navega al menú que le pasemos por parámetros
     * @param menu: menú
     */
    @And("Selecciono el menu (.*)$")
    public void navegarMenu(String menu) {
        menuService.navegar(utilSelenium, menu, "");
    }

    /**
     * Activar el widget que le pasemos por parámetro
     * @param widget
     */
    @And("Pongo visible el componente (.*)$")
    public void navegarWidget(String widget) {
        menuService.navegarWidget(utilSelenium, widget);
    }
}
