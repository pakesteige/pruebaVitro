package vitro.testrunner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


/**
 * Clase que lanza la ejecución buscando la feature y las definiciones de dichas features según los parámetros feature y glue
 * configurado el framework serenity
 *
 * @author paco
 * @version 1.0
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/vitro/feature/sub001", glue = "classpath:vitro.stepdefinition")

public class Runner {

}