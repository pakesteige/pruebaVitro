package vitro.utilidad;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Clase UtilSelenium
 *
 * Clase para manejar selenium webdriver
 * @author paco
 * @version 1.0
 */
public class UtilSelenium extends Base {

    // variables globales
    private static final String PATHRESOURCES = "seleniumdriver/";
    private static final String DRIVERCHROME = "driverchrome";
    private static final String DRIVERIE = "driverie";
    private static final String VESIONDEFAULTCHROME = "chromedriver-78.0.3904.105";
    private static final String VERSIONDEFAULTIE = "IEDriverServer";
    private static final String EXTENSIONWIN = ".exe";
    private static final String EXTENSIONLIN = ".linux";
    private static final String CHROME = "chrome";
    private static final String IEXPLORER = "internet explorer";

    /**
     * variable para manejar el selenium webdriver
     */
    private WebDriver driver;

    /**
     * variable del tipo de la clase para utilizar el patrón singleton
     */
    private static UtilSelenium instancia = null;

    /**
     * Constructor privado con 3 parámetros que según el navegador y la versión inicializará el selenium webdriver
     * @param nombreLogger nombre del log
     * @param browser navegador
     * @param version versión del selenium webdriver
     */
    private UtilSelenium(String nombreLogger, String browser, String version) {
        super(nombreLogger);

        switch (browser) {
            case CHROME:
                seleniumChrome(version);
                break;
            case IEXPLORER:
                seleniumIE(version);
                break;
            default:
                seleniumChrome(null);
                break;
        }
    }

    /**
     * Devuelve el objeto webdriver
     * @return driver: objeto de tipo webdriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Modifica el valor del webdriver
     * @param driver: objeto de tipo webdriver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Inicializa el webdriver para el navegador chrome
     * @param version: versión del webdriver a utilizar
     */
    private void seleniumChrome(String version) {
        version = seleccionarDriverSeleniumChrome(version);

        guardarBinario(version, CHROME);

        File ficheroDriver = new File(rutaBinarioDriver(CHROME));

        if (ficheroDriver.setExecutable(Boolean.TRUE)) {
            getLogger().debug("no hace falta darle permisos");
        }
        DesiredCapabilities desiredCapabilities = getCapabilitiesByBrowser(CHROME, rutaBinarioDriver(CHROME));

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(ficheroDriver)
                .usingAnyFreePort()
                .build();

        ChromeOptions options = new ChromeOptions();
        options.merge(desiredCapabilities);
        options.addArguments("--start-maximized");
        setDriver(new ChromeDriver(service, options));
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Class[] params = new Class[1];
        params[0] = WebDriver.class;
    }

    /**
     * Busca en el paquete src/main/resources/seleniumdriver el webdriver según la versión en el navegador chrome
     * @param driverSeleniumSO: versión a buscar
     * @return devuelve la ruta del webdriver
     */
    private String seleccionarDriverSeleniumChrome(String driverSeleniumSO) {
        String aux;
        driverSeleniumSO = Objects.toString(driverSeleniumSO, "");
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            aux = existeFicherDriverSeleniumChrome(driverSeleniumSO, EXTENSIONWIN);
        } else {
            aux = existeFicherDriverSeleniumChrome(driverSeleniumSO, EXTENSIONLIN);
        }
        return aux;
    }

    /**
     * Comnprueba si existe la versión del selenium webdriver en el navegador chrome
     * @param version: versión a buscar del selenium webdriver
     * @param extension: extensión utilizada según el sistema operativo
     * @return devuelve la ruta del fichero
     */
    private String existeFicherDriverSeleniumChrome(String version, String extension) {
        File fichero = new File(PATHRESOURCES.concat(version.concat(extension)));
        if (!fichero.exists()) {
            version = VESIONDEFAULTCHROME;
        }
        return PATHRESOURCES.concat(version.concat(extension));
    }

    /**
     * Para inicializar el webdriver para internet explorer
     * @param version: versión del selenium webdriver
     */
    private void seleniumIE(String version) {
        version = seleccionarDriverSeleniumIE(version);

        guardarBinario(version, IEXPLORER);

        DesiredCapabilities capabilities = getCapabilitiesByBrowser(IEXPLORER, rutaBinarioDriver(IEXPLORER));
        InternetExplorerDriverService serviceIE = new InternetExplorerDriverService.Builder()
                .usingDriverExecutable(new File(rutaBinarioDriver(IEXPLORER)))
                .usingAnyFreePort()
                .build();
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.merge(capabilities);
        setDriver(new InternetExplorerDriver(serviceIE, optionsIE));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Class[] params = new Class[1];
        params[0] = WebDriver.class;
    }

    /**
     * Busca en el paquete src/main/resources/seleniumdriver el webdriver según la versión en el navegador internet explorer
     * @param driverSeleniumSO: versión a buscar
     * @return devuelve la ruta del webdriver
     */
    private String seleccionarDriverSeleniumIE(String driverSeleniumSO) {
        String aux;
        driverSeleniumSO = Objects.toString(driverSeleniumSO, "");
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            aux = existeFicherDriverSeleniumIE(driverSeleniumSO, EXTENSIONWIN);
        } else {
            aux = existeFicherDriverSeleniumIE(driverSeleniumSO, EXTENSIONLIN);
        }
        return aux;
    }

    /**
     * Comnprueba si existe la versión del selenium webdriver en el navegador internet explorer
     * @param version: versión a buscar del selenium webdriver
     * @param extension: extensión utilizada según el sistema operativo
     * @return devuelve la ruta del fichero
     */
    private String existeFicherDriverSeleniumIE(String version, String extension) {
        File fichero = new File(PATHRESOURCES.concat(version.concat(extension)));
        if (!fichero.exists()) {
            version = VERSIONDEFAULTIE;
        }
        return PATHRESOURCES.concat(version.concat(extension));
    }

    /**
     * Configuración del webdriver
     * @param browser: navegador
     * @param version: version del webdriver
     * @return dc: configuración
     */
    private DesiredCapabilities getCapabilitiesByBrowser(String browser, String version) {
        DesiredCapabilities dc = null;
        switch (browser) {
            case CHROME:
                dc = DesiredCapabilities.chrome();
                dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", rutaDirectorioLog());
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--test-type");
                dc.setCapability(ChromeOptions.CAPABILITY, options);
                System.setProperty("webdriver.chrome.driver", version);
                break;
            case IEXPLORER:
                dc = DesiredCapabilities.internetExplorer();
                dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
                dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
                dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                dc.setJavascriptEnabled(true);
                System.setProperty("webdriver.ie.driver", version);
                break;
            default:
                getLogger().error("browser : {} no es correcto, Se lanza Firefox como browser por defecto..", browser);
                dc = DesiredCapabilities.firefox();
        }
        return dc;
    }

    /**
     * Patrón singleton, si no hay instancia del objeto (UtilSelenium) instancia la crea
     * @param nombreLogger: nombre del log
     * @param browser: navegador
     * @param version: versión del webdriver
     * @return instancia: objeto del tipo UtilSelenium
     */
    public static UtilSelenium getInstancia(String nombreLogger, String browser, String version) {
        try {
            if (instancia == null) {
                instancia = new UtilSelenium(nombreLogger, browser, version);
            }
            return instancia;
        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * Constructor por defecto
     * @return
     */
    public static UtilSelenium getUtilSelenium() {
        return instancia;
    }

    /**
     * Cerrar el driver
     */
    public void cerrarDriver() {
        try {
            if (getDriver() != null && StringUtils.isNotBlank(getDriver().getWindowHandle())) {
                getDriver().quit();
                finalizaInstancia();
            }
        } catch (ElementNotVisibleException e) {
            getLogger().error("-- CERRAR DRIVER - " + e.getMessage() + "- ERROR", e);
        }
    }

    /**
     * Finalizar la instancia del objeto UtilSelenium
     */
    private static void finalizaInstancia() {
        instancia = null;
    }

    /**
     * para crear el fichero webdriver en nuestra máquina
     * @param version
     * @param browser
     */
    private void guardarBinario(String version, String browser) {
        try {
            URL url = Resources.getResource(version);
            byte[] bytes = Resources.toByteArray(url);
            Files.write(bytes, new File(rutaBinarioDriver(browser)));
        } catch (IOException e) {
            getLogger().error(e.getMessage());
        }
    }

    /**
     * Devuelve la ruta del fichero según el navegador
     * @param browser: navegador
     * @return ruta del webdriver
     */
    private String rutaBinarioDriver(String browser) {
        String binarioBrowser = "";

        switch (browser) {
            case CHROME:
                binarioBrowser = DRIVERCHROME;
                break;
            case IEXPLORER:
                binarioBrowser = DRIVERIE;
                break;
            default:
                binarioBrowser = DRIVERCHROME;
                break;
        }
        return rutaDirectorioLog() + binarioBrowser;
    }

    /**
     * Pregunta si existe el elemento
     * @param by: DOM del elemento a buscar
     * @return <ul>
     *     <li>true: el elemento existe</li>
     *     <li>false: el elemento no existe</li>
     * </ul>
     */
    public boolean isElementPresent(By by) {
        try {
            this.getDriver().findElement(by);
            return Boolean.TRUE;
        } catch (Exception var3) {
            return Boolean.FALSE;
        }
    }

    /**
     * Busca el elemento según el texto que tenga entre el elemento buscado
     * @param by: DOM del elemento
     * @param texto: texto contenido entre el elemento buscado
     * @return <ul>
     *     <li>true: existe el elemento buscado</li>
     *     <li>false: no existe el elemento buscado</li>
     * </ul>
     */
    public boolean buscarElementoPorTexto(By by, String texto) {
        Boolean encontrado = Boolean.FALSE;
        try {
            List<WebElement> lista = this.getDriver().findElements(by);
            Iterator var5 = lista.iterator();

            while (var5.hasNext()) {
                WebElement we = (WebElement) var5.next();
                if (we.getText().contains(texto)) {
                    encontrado = Boolean.TRUE;
                    break;
                }
            }
        } catch (ElementNotVisibleException var7) {
            this.getLogger().error("-- BUSCAR ELEMENTO POR TEXTO - " + var7.getMessage() + " - ERROR", var7);
        }
        return encontrado;
    }

    /**
     * Hacer click en un elemento buscado por el texto contenido entre dicho elemento
     * @param by: DOM del elemento
     * @param texto: texto contenido por el elemento buscado
     */
    public void click(By by, String texto) {
        Boolean encontrado = Boolean.FALSE;
        try {
            List<WebElement> lista = this.getDriver().findElements(by);
            Iterator var5 = lista.iterator();

            while (var5.hasNext()) {
                WebElement we = (WebElement) var5.next();
                if (we.getText().contains(texto)) {
                    we.click();
                    encontrado = Boolean.TRUE;
                    break;
                }
            }
        } catch (ElementNotVisibleException var10) {
            this.getLogger().error("-- CLICK - " + var10.getMessage() + " - ERROR", var10);
        } finally {
            Assert.isTrue(encontrado, "No se ha podido hacer click en el elemento " + by);
        }
    }

    /**
     * Esperar hasta que un elemento sea invisible
     * @param locator: DOM del elemento
     */
    public void esperarHastaElementoInvisible(By locator) {
        try {
            if (this.isElementPresent(locator)) {
                WebDriverWait wait = new WebDriverWait(this.getDriver(), 40L);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            }
        } catch (Exception var4) {
            this.getLogger().error("ERROR: NO SE HA PUESTO INVISIBLE EL ELEMENTO -> " + locator + " ::: " + var4.getMessage());
        }
    }

    /**
     * Esperar hasta que un elemento sea visible
     * @param locator: DOM del elemento
     */
    public void esperarHastaElementoVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(this.getDriver(), 30L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception var4) {
            this.getLogger().error("ERROR: SE HA PUESTO VISIBLE EL ELEMENTO -> " + locator + " : " + var4.getMessage());
        }
    }

    /**
     * Esperar un cierto tiempo
     * @param segundos: tiempo de espera
     */
    public void esperarSegundos(long segundos) {
        try {
            synchronized (this.getDriver()) {
                this.getDriver().wait(segundos * 1000L);
            }
        } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
            this.getLogger().error("- ESPERAR SEGUNDOS - " + var6.getMessage(), var6);
        }
    }
}
