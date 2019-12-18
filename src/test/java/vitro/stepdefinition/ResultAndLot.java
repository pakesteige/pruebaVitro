package vitro.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import vitro.entidad.ManualResultInput;
import vitro.step.ResultAndLotService;
import vitro.utilidad.UtilSelenium;

import static vitro.utilidad.UtilSelenium.getUtilSelenium;

/**
 * Clase ResultAndLot
 *
 * Clase para interactuar con el menú Results And Lot
 *
 * @author paco
 * @version 1.0
 */
public class ResultAndLot {

    private ResultAndLotService resultAndLotService = new ResultAndLotService();
    private UtilSelenium utilSelenium = getUtilSelenium();

    /**
     * Buscar en formulario del submenú manual result input
     * @param combo1 campo Lab./Dep./Inst
     * @param combo2 campo Control/Level/ Vial Lot
     * @param combo3 campo Analyte
     * @throws Throwable
     */
    @And("Selecciono la opcion uno (.*) opcion dos (.*) y opcion tres (.*)$")
    public void buscarManualResultInput(String combo1, String combo2, String combo3) throws Throwable {
        ManualResultInput manualResultInput = new ManualResultInput("", "", combo1, combo2, combo3);
        resultAndLotService.buscarManualResultInput(utilSelenium, manualResultInput);
    }

    /**
     * Comprobar que se muestran los resultados esperados de la búsqueda del formulario
     * @param total resultado total de registros a mostrar
     * @throws Throwable
     */
    @Then("Verifico que se muestran los resultados (.*)$")
    public void comprobarResultado(String total) throws Throwable {
        resultAndLotService.comprobarResultado(utilSelenium, total);
    }
}
