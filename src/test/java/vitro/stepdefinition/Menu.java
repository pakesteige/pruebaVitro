package vitro.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import vitro.step.MenuService;
import vitro.utilidad.UtilSelenium;

import static vitro.utilidad.UtilSelenium.getUtilSelenium;

public class Menu {

    private MenuService menuService = new MenuService();
    private UtilSelenium utilSelenium = getUtilSelenium();

    @When("Navego al menu (.*) y pulso la opcion (.*)$")
    public void navego_al_menu_y_pulso_la_opcion(String menu, String option) throws Throwable {
        menuService.navegar(utilSelenium, menu, option);
    }

    @And("Selecciono el menu (.*)$")
    public void selecciono_el_menu(String menu) {
        menuService.navegar(utilSelenium, menu, "");
    }

    @And("Pongo visible el componente (.*)$")
    public void pongo_visible_el_componente(String widget) {
        menuService.navegarWidget(utilSelenium, widget);
    }
}
