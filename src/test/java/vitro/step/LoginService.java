package vitro.step;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.springframework.util.Assert;
import vitro.pageobject.CamposLogin;
import vitro.pageobject.CamposMenu;
import vitro.utilidad.Base;
import vitro.utilidad.UtilSelenium;

public class LoginService extends Base {

    public void login(UtilSelenium utilSelenium, String app, String identifier, String user, String pass) {
        utilSelenium.getLogger().info("-- LOGIN - INICIO");
        try {
            utilSelenium.getLogger().info("Navegar a la aplicación ".concat(app));
            utilSelenium.getDriver().get(app);
            utilSelenium.getLogger().info("Introducir identificador ".concat(identifier));
            utilSelenium.getDriver().findElement(By.id(CamposLogin.INP_IDENTIFIER.getTexto())).sendKeys(identifier);
            utilSelenium.getLogger().info("Introducir usuario ".concat(user));
            utilSelenium.getDriver().findElement(By.xpath(CamposLogin.INP_USER.getTexto())).sendKeys(user);
            utilSelenium.getLogger().info("Introducir contraseña ".concat(pass));
            utilSelenium.getDriver().findElement(By.xpath(CamposLogin.INP_PASS.getTexto())).sendKeys(pass);
            utilSelenium.getLogger().info("Pulsar en el botón LogIn");
            utilSelenium.click(By.xpath(CamposLogin.BTN_SIGNIN.getTexto()), "SIGN IN");
            utilSelenium.esperarHastaElementoInvisible(By.id(CamposLogin.DIV_LOADING.getTexto()));
            Assert.isTrue(utilSelenium.isElementPresent(By.xpath(CamposMenu.IMG_PRINCIPAL.getTexto())), "Error al acceder a la aplicación");
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- LOGIN - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- LOGIN - " + e.getMessage() + " - ERROR");
        } finally {
            utilSelenium.getLogger().info("-- LOGIN - FIN");
        }
    }

    public void logout(UtilSelenium utilSelenium) {
        boolean correcto = Boolean.FALSE;
        try {
            utilSelenium.getLogger().info("Cerrar sesión");
            utilSelenium.getDriver().findElement(By.id("HeaderControl_hlQuit")).click();
            correcto = Boolean.TRUE;
        } catch (ElementNotVisibleException e) {
            Assert.isTrue(Boolean.FALSE, "Error al cerrar sesión");
        } finally {
            Assert.isTrue(correcto, "Imposible cerrar sesión");
        }
    }
}