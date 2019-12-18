package vitro.utilidad;

import ch.qos.logback.classic.Logger;

import java.io.File;

/**
 * Clase Base
 *
 * Clase principal de mi librería que ayudará a inicializar los proyectos que utilizan selenium webdriver
 *
 * @author paco
 * @version 1.0
 */
public abstract class Base {

    // Variables globales
    /**
     * Nombre del sistema operativo
     */
    private static final String NOMBRESO = "os.name";

    /**
     * Objeto de tipo Logger utilizada para escribir en log
     */
    protected Logger logger;

    /**
     * Contructor con 1 parámetro para inicializar el log
     *
     * @param nombreLog nombre del log
     */
    public Base(String nombreLog) {
        this.logger = (new UtilLog(this.rutaDirectorioLog(), nombreLog.replace("_", "-"))).createLoggerFor();
    }

    /**
     * Constructor por defecto
     */
    protected Base() {
    }

    /**
     * Método que devuelve el objeto de tipo Logger
     *
     * @return logger: objeto de tipo logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Método que devuelve el directorio donde se guardará el log
     *
     * @return ruta: ruta del directorio del log
     */
    public String rutaDirectorioLog() {
        String ruta;
        if (Boolean.TRUE.equals(estoyEnWindows())) {
            ruta = "C:" + File.separator + "Logs" + File.separator;
        } else {
            ruta = System.getProperty("user.home") + File.separator + "Logs" + File.separator;
        }
        return ruta;
    }

    /**
     * Comprueba el sistema operativo donde se ejecuta la prueba
     *
     * @return <ul>
     * <li>true: el sistema operativo es windows</li>
     * <li>false: el sistema operativo es distinto a windows</li>
     * </ul>
     */
    public Boolean estoyEnWindows() {
        String so = System.getProperty(NOMBRESO);
        return so.contains("Windows");
    }
}
