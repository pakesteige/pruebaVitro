package vitro.utilidad;

import ch.qos.logback.classic.Logger;

import java.io.File;

public abstract class Base {

    private static final String NOMBRESO = "os.name";
    private static final String PATH_RESOURCES = "src/test/resources/";

    protected Logger logger;

    public Base(String nombreLog) {
        this.logger = (new UtilLog(this.rutaDirectorioLog(), nombreLog.replace("_", "-"))).createLoggerFor();
    }

    protected Base() {

    }

    public Logger getLogger() {
        return logger;
    }

    public String rutaDirectorioLog() {
        String ruta;
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            ruta = "C:" + File.separator + "OCA" + File.separator + "Logs" + File.separator;
        } else {
            ruta = System.getProperty("user.home") + File.separator + "OCA" + File.separator + "Logs" + File.separator;
        }
        return ruta;
    }

    public Boolean estoyEnWindows() {
        String so = System.getProperty(NOMBRESO);
        return so.contains("Windows");
    }
}
