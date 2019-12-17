package vitro.step;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.springframework.util.Assert;
import vitro.pageobject.CamposMenu;
import vitro.utilidad.Base;
import vitro.utilidad.UtilSelenium;

public class MenuService extends Base {

    public void navegar(UtilSelenium utilSelenium, String menu, String opcion) {
        utilSelenium.getLogger().info("-- NAVEGAR AL MENÚ " + menu + " Y ELEGIR LA OPCIÓN " + opcion + " - INICIO");
        try {
            if (!utilSelenium.buscarElementoPorTexto(By.xpath(CamposMenu.H3_SUBMENU.getTexto()), opcion) || opcion.equals("")) {
                utilSelenium.click(By.xpath(CamposMenu.LI_MENU.getTexto()), menu);
                if (!opcion.equals("")) {
                    utilSelenium.click(By.xpath(CamposMenu.LI_MENU.getTexto()), opcion);
                    utilSelenium.getLogger().info("Menú " + menu + " -> Submenú " + opcion);
                    utilSelenium.esperarHastaElementoInvisible(By.id(CamposMenu.DIV_LOADING.getTexto()));
                    Assert.isTrue(utilSelenium.buscarElementoPorTexto(By.xpath(CamposMenu.H3_SUBMENU.getTexto()), opcion.toUpperCase()), "Error al navegar a la opción " + opcion);
                } else {
                    utilSelenium.getLogger().info("Menú " + menu);
                    if (menu.equals("Home")) {
                        menu = "dashboard";
                    }
                    Assert.isTrue(utilSelenium.buscarElementoPorTexto(By.xpath(CamposMenu.H3_SUBMENU.getTexto()), menu.toUpperCase()), "Error al navegar a la opción " + menu);
                }
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- NAVEGAR AL MENÚ " + menu + " Y ELEGIR LA OPCIÓN " + opcion + " - " + e.getMessage() + " - ERROR", e);
        } finally {
            utilSelenium.getLogger().info("-- NAVEGAR AL MENÚ " + menu + " Y ELEGIR LA OPCIÓN " + opcion + " - FIN");
        }
    }

    public void navegarWidget(UtilSelenium utilSelenium, String widget) {
        utilSelenium.getLogger().info("-- NAVEGAR A WIDGET " + widget + " - INICIO");
        try {
            utilSelenium.getLogger().info("Click en el widget " + widget);
            utilSelenium.getDriver().findElement(By.xpath("//img[@title='" + widget + "']")).click();
            utilSelenium.esperarSegundos(3);
            Assert.isTrue(utilSelenium.isElementPresent(By.id(seMuestraWidget(widget))), "Error al navegar al widget " + widget);
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- NAVEGAR A WIDGET " + widget + " - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- NAVEGAR A WIDGET " + widget + " - " + e.getMessage() + " - ERROR");
        } finally {
            utilSelenium.getLogger().info("-- NAVEGAR A WIDGET " + widget + " - FIN");
        }
    }

    private String seMuestraWidget(String widget) {
        String campo = "";
        switch (widget) {
            case "Results":
                campo = CamposMenu.WIDGET_RESULTS.getTexto();
                break;
        }
        return campo;
    }

}
