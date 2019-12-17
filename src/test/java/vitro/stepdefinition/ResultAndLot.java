package vitro.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import vitro.entidad.ManualResultInput;
import vitro.step.ResultAndLotService;
import vitro.utilidad.UtilSelenium;

import static vitro.utilidad.UtilSelenium.getUtilSelenium;

public class ResultAndLot {

    private ResultAndLotService resultAndLotService = new ResultAndLotService();
    private UtilSelenium utilSelenium = getUtilSelenium();

    @And("Selecciono la opcion uno (.*) opcion dos (.*) y opcion tres (.*)$")
    public void selecciono_la_opcion_uno_opcion_dos_y_opcion_tes(String combo1, String combo2, String combo3) throws Throwable {
        ManualResultInput manualResultInput = new ManualResultInput("", "", combo1, combo2, combo3);
        resultAndLotService.buscarManualResultInput(utilSelenium, manualResultInput);
    }

    @Then("Verifico que se muestran los resultados (.*)$")
    public void verifico_que_se_muestran_los_resultado(String total) throws Throwable {
        resultAndLotService.comprobarResultado(utilSelenium, total);
    }
}
