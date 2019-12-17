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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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

public class UtilSelenium extends Base {

    private static final String PATHRESOURCES = "seleniumdriver/";
    private static final String DRIVERCHROME = "driverchrome";
    private static final String DRIVERFIREFOX = "driverfirefox";
    private static final String DRIVERIE = "driverie";
    private static final String VESIONDEFAULTCHROME = "chromedriver-78.0.3904.105";
    private static final String VERSIONDEFAULTIE = "IEDriverServer";
    private static final String VERSIONDEFAULTFIREFOX = "geckodriver-v0.26.0";
    private static final String EXTENSIONWIN = ".exe";
    private static final String EXTENSIONLIN = ".linux";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IEXPLORER = "internet explorer";

    private WebDriver driver;
    private static UtilSelenium instancia = null;
    private DesiredCapabilities capability;
    private int numPaso = 0;

    private UtilSelenium(String nombreLogger, String browser, String version) {
        super(nombreLogger);

        switch (browser) {
            case CHROME:
                seleniumChrome(version);
                break;
            case IEXPLORER:
                seleniumIE(version);
                break;
            case FIREFOX:
                seleniumFirefox(version);
                break;
            default:
                seleniumChrome(null);
                break;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private void seleniumChrome(String version) {
        version = seleccionarDriverSeleniumChrome(version);

        guardarBinario(version, CHROME);

        File ficheroDriver = new File(rutaBinarioDriver(CHROME));

        if (ficheroDriver.setExecutable(Boolean.TRUE)) {
            getLogger().error("ERROR AL DAR PERMISOS");
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

    private String existeFicherDriverSeleniumChrome(String version, String extension) {
        File fichero = new File(PATHRESOURCES.concat(version.concat(extension)));
        if (!fichero.exists()) {
            version = VESIONDEFAULTCHROME;
        }
        return PATHRESOURCES.concat(version.concat(extension));
    }

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

    private String existeFicherDriverSeleniumIE(String version, String extension) {
        File fichero = new File(PATHRESOURCES.concat(version.concat(extension)));
        if (!fichero.exists()) {
            version = VERSIONDEFAULTIE;
        }
        return PATHRESOURCES.concat(version.concat(extension));
    }

    private void seleniumFirefox(String version) {
        version = seleccionarDriverSeleniumFirefox(version);
        guardarBinario(version, FIREFOX);

        File ficheroDriver = new File(rutaBinarioDriver(FIREFOX));
        if (ficheroDriver.setExecutable(Boolean.TRUE)) {
            getLogger().error("ERROR AL DAR PERMISOS");
        }
        System.setProperty("webdriver.gecko.driver", rutaBinarioDriver(FIREFOX));
        setDriver(new FirefoxDriver());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Class[] params = new Class[1];
        params[0] = WebDriver.class;
    }

    private String seleccionarDriverSeleniumFirefox(String driverSeleniumSO) {
        String aux;
        driverSeleniumSO = Objects.toString(driverSeleniumSO, "");
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            aux = existeFicherDriverSeleniumFirefox(driverSeleniumSO, EXTENSIONWIN);
        } else {
            aux = existeFicherDriverSeleniumFirefox(driverSeleniumSO, EXTENSIONLIN);
        }
        return aux;
    }

    private String existeFicherDriverSeleniumFirefox(String version, String extension) {
        File fichero = new File(PATHRESOURCES.concat(version.concat(extension)));
        if (!fichero.exists()) {
            version = VERSIONDEFAULTFIREFOX;
        }
        return PATHRESOURCES.concat(version.concat(extension));
    }

    private DesiredCapabilities getCapabilitiesByBrowser(String browser, String version) {
        DesiredCapabilities dc = null;
        switch (browser) {
            case FIREFOX:
                dc = DesiredCapabilities.firefox();
                dc.setCapability(FirefoxDriver.PROFILE, getFirefoxDriverProfile());
                System.setProperty("webdriver.gecko.driver", version);
                break;
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

    private FirefoxProfile getFirefoxDriverProfile() {

        FirefoxProfile myProfile = new FirefoxProfile();
        myProfile.setPreference("browser.download.folderList", 2);
        myProfile.setPreference("browser.download.manager.showWhenStarting", false);
        myProfile.setPreference("browser.download.dir", rutaDirectorioLog());
        myProfile.setPreference("browser.helperApps.neverAsk.openFile",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        myProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,application/pdf,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        myProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        myProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        myProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        myProfile.setPreference("browser.download.manager.useWindow", false);
        myProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        myProfile.setPreference("browser.download.manager.closeWhenDone", false);

        // Deshabilitar configuración Acrobat
        myProfile.setPreference("pdfjs.disabled", true);
        // Desactivar Acrobat plugin que previsualiza PDFs en Firefox
        myProfile.setPreference("plugin.scan.Acrobat", "99.0");
        myProfile.setPreference("plugin.scan.plid.all", false);

        return myProfile;
    }

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

    public static UtilSelenium getUtilSelenium() {
        return instancia;
    }

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

    private static void finalizaInstancia() {
        instancia = null;
    }

    private void guardarBinario(String version, String browser) {
        try {
            URL url = Resources.getResource(version);
            byte[] bytes = Resources.toByteArray(url);
            Files.write(bytes, new File(rutaBinarioDriver(browser)));
        } catch (IOException e) {
            getLogger().error(e.getMessage());
        }
    }

    private String rutaBinarioDriver(String browser) {
        String binarioBrowser = "";

        switch (browser) {
            case CHROME:
                binarioBrowser = DRIVERCHROME;
                break;
            case IEXPLORER:
                binarioBrowser = DRIVERIE;
                break;
            case FIREFOX:
                binarioBrowser = DRIVERFIREFOX;
                break;
            default:
                binarioBrowser = DRIVERCHROME;
                break;
        }
        return rutaDirectorioLog() + binarioBrowser;
    }

    public boolean isElementPresent(By by) {
        try {
            this.getDriver().findElement(by);
            return Boolean.TRUE;
        } catch (Exception var3) {
            return Boolean.FALSE;
        }
    }

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

    public void esperarHastaElementoVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(this.getDriver(), 30L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception var4) {
            this.getLogger().error("ERROR: SE HA PUESTO VISIBLE EL ELEMENTO -> " + locator + " : " + var4.getMessage());
        }
    }

    public void esperarSegundos(long segundos) {
        try {
            synchronized(this.getDriver()) {
                this.getDriver().wait(segundos * 1000L);
            }
        } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
            this.getLogger().error("- ESPERAR SEGUNDOS - " + var6.getMessage(), var6);
        }
    }
}
