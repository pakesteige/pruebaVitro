package vitro.utilidad;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase UtilLog
 * <p>
 * Clase para inicializar el objeto Logger con una determinada configuración y poder escribir en el log, sobreescribe la clase Logger de la librería logback
 *
 * @author paco
 * @version 1.0
 */
public class UtilLog {

    // Variables globales
    /**
     * Variable LOG en caso de que no podamos inicializar nuestra  variable logger y poder escribir el mensaje de error en log
     */
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(UtilLog.class.getName());

    /**
     * ruta de log
     */
    private String pathLogs;

    /**
     * nombre del fichero del log
     */
    private String fileNameLog;

    /**
     * para complentar el nombre del log y así tener uno por cada ejecución
     */
    private String maskDate = "yyyyMMddHHmmss";

    /**
     * Constructor con 2 parámetros que asignan los valores del nombre del fichero y la ruta del log
     * @param pathLogParam
     * @param ficheroLogParam
     */
    public UtilLog(String pathLogParam, String ficheroLogParam) {
        this.pathLogs = pathLogParam;
        this.fileNameLog = ficheroLogParam;
    }

    /**
     * Configuración del log
     * @return logger: devuelve el objeto logger con la configuración
     */
    public Logger createLoggerFor() {
        try {
            Date date = new Date();

            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            PatternLayoutEncoder ple = new PatternLayoutEncoder();

            ple.setPattern("%date %level [%thread] [%class{99}:%line.%M]  %msg%n");
            ple.setContext(lc);
            ple.start();
            FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();

            String path1 = this.pathLogs;
            String path2 = this.fileNameLog + "_" + new SimpleDateFormat((this.maskDate)).format(date) + ".log";

            //concadenar ruta con nombre del fichero de log
            File file1 = new File(path1);
            File file2 = new File(file1, path2);

            //crear appender para el fichero log
            fileAppender.setName("ficheroLOG");
            //quitar para que no agrege en el fichero
            fileAppender.setFile(file2.getAbsolutePath());
            fileAppender.setEncoder(ple);
            fileAppender.setContext(lc);
            fileAppender.start();

            Logger logger = (Logger) LoggerFactory.getLogger(this.fileNameLog + "_" + new SimpleDateFormat((this.maskDate)).format(date));
            logger.addAppender(fileAppender);
            logger.setLevel(Level.DEBUG);
            logger.setAdditive(true); /* set to true if root should log too */
            logger.info("logger Iniciado >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<");
            return logger;
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return null;
        }
    }
}
