package vitro.utilidad;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

public class UtilLog {

    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(UtilLog.class.getName());

    private String pathLogs = "logs\\";
    private String fileNameLog = "ficheroLog";
    private String maskDate = "yyyyMMddHHmmss";

    public UtilLog(String pathLogParam, String ficheroLogParam) {
        this.pathLogs = pathLogParam;
        this.fileNameLog = ficheroLogParam;
    }

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
            fileAppender.setFile( file2.getAbsolutePath());
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
