package vitro.step;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;
import vitro.entidad.ManualResultInput;
import vitro.pageobject.CamposResultAndLot;
import vitro.utilidad.Base;
import vitro.utilidad.UtilSelenium;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clase ResultAndLotService
 * Describe los pasos detallados de la clase ResultAndLot
 *
 * @author paco
 * @version 1.0
 */
public class ResultAndLotService extends Base {

    /**
     * Rellenar el formulario del submenú Manual Result Input
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param manualResultInput objeto con los atributos del formulario de búsqueda del submenú Manual Result Input
     */
    public void buscarManualResultInput(UtilSelenium utilSelenium, ManualResultInput manualResultInput) {
        utilSelenium.getLogger().info("-- BUSCAR COMPONENTES EN MANUAL RESULT INPUT - INICIO");
        try {
            utilSelenium.getLogger().info("Cumplimentar formulario de búsqueda");
            cumplimentarFromDate(utilSelenium, manualResultInput.getFromDate());
            cumplimentarToDate(utilSelenium, manualResultInput.getToDate());
            cumplimentarLabDepInst(utilSelenium, manualResultInput.getLabDepInst());
            cumplimentarControlLevelVialLot(utilSelenium, manualResultInput.getControlLevelVialLot());
            cumplimentarAnalyte(utilSelenium, manualResultInput.getAnalyte());
            utilSelenium.getLogger().info("Pulsar en el botón Apply");
            utilSelenium.esperarSegundos(3);
            utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.BTN_APPLY.getTexto())).click();
            utilSelenium.esperarHastaElementoVisible(By.id(CamposResultAndLot.TBL_RESULTADOS.getTexto()));
            utilSelenium.esperarSegundos(20);
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- BUSCAR COMPONENTES EN MANUAL RESULT INPUT - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- BUSCAR COMPONENTES EN MANUAL RESULT INPUT - " + e.getMessage() + " - ERROR");
        } finally {
            utilSelenium.getLogger().info("-- BUSCAR COMPONENTES EN MANUAL RESULT INPUT - FIN");
            Assert.isTrue(utilSelenium.isElementPresent(By.id(CamposResultAndLot.TBL_RESULTADOS.getTexto())), "Error al buscar");
        }
    }

    /**
     * Rellena el campo From Date del formulario
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param fromDate campo From Date del formulario de búsqueda
     */
    private void cumplimentarFromDate(UtilSelenium utilSelenium, String fromDate) {
        try {
            if (fromDate != null && !fromDate.equals("")) {
                utilSelenium.getLogger().info("Rellenar el campo from Date: " + fromDate);
                // rellenar el campo from date
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- CUMPLIMENTAR CAMPO FROM DATE - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- CUMPLIMENTAR CAMPO FROM DATE - " + e.getMessage() + " - ERROR");
        }
    }

    /**
     * Rellena el campo To Date del formulario
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param toDate campo To Date del formulario de búsqueda
     */
    private void cumplimentarToDate(UtilSelenium utilSelenium, String toDate) {
        try {
            if (toDate != null && !toDate.equals("")) {
                utilSelenium.getLogger().info("Rellenar el campo to Date: " + toDate);
                // rellenar el campo to date
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- CUMPLIMENTAR CAMPO TO DATE - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- CUMPLIMENTAR CAMPO TO DATE - " + e.getMessage() + " - ERROR");
        }
    }

    /**
     * Rellena el campo Lab./Dep./Inst del formulario
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param labDepInst campo Lab./Dep./Inst del formulario de búsqueda
     */
    private void cumplimentarLabDepInst(UtilSelenium utilSelenium, String labDepInst) {
        try {
            if (labDepInst != null && !labDepInst.equals("")) {
                utilSelenium.getLogger().info("Rellenar el campo lab/Dep/Inst: " + labDepInst);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGLADDEP.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_LAPDEPINST.getTexto())).click();
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGLADDEP.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_LAPDEPINST.getTexto())).clear();
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGLADDEP.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_LAPDEPINST.getTexto())).sendKeys(labDepInst);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGLADDEP.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_LAPDEPINST.getTexto())).sendKeys(Keys.ENTER);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGLADDEP.getTexto()));
                utilSelenium.esperarSegundos(15);
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- CUMPLIMENTAR CAMPO LAB/DEP/INST - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- CUMPLIMENTAR CAMPO LAB/DEP/INST - " + e.getMessage() + " - ERROR");
        } catch (NoSuchElementException e) {
            utilSelenium.getLogger().error("-- CUMPLIMENTAR CAMPO LAB/DEP/INST - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- CUMPLIMENTAR CAMPO LAB/DEP/INST - " + e.getMessage() + " - ERROR");
        }
    }

    /**
     * Rellena el campo Control/Level/Vial Lot del formulario
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param controlLevelVialLot campo Control/Level/Vial Lot del formulario de búsqueda
     */
    private void cumplimentarControlLevelVialLot(UtilSelenium utilSelenium, String controlLevelVialLot) {
        try {
            if (controlLevelVialLot != null && !controlLevelVialLot.equals("")) {
                utilSelenium.getLogger().info("Rellenar el campo control/Level/Vial Lot: " + controlLevelVialLot);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGCONTROL.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_CONTROLLEVELVIALLOT.getTexto())).click();
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGCONTROL.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_CONTROLLEVELVIALLOT.getTexto())).clear();
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGCONTROL.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_CONTROLLEVELVIALLOT.getTexto())).sendKeys(controlLevelVialLot);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGCONTROL.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_CONTROLLEVELVIALLOT.getTexto())).sendKeys(Keys.ENTER);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGCONTROL.getTexto()));
                utilSelenium.esperarSegundos(30);
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- CUMPLIMENTAR CAMPO CONTROL/LEVEL/VIAL LOT - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- CUMPLIMENTAR CAMPO CONTROL/LEVEL/VIAL LOT - " + e.getMessage() + " - ERROR");
        }
    }

    /**
     * Rellena el campo Analyte del formulario
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param analyte campo Analyte del formulario de búsqueda
     */
    private void cumplimentarAnalyte(UtilSelenium utilSelenium, String analyte) {
        try {
            if (analyte != null && !analyte.equals("")) {
                utilSelenium.getLogger().info("Rellenar el campo analyte: " + analyte);
                utilSelenium.esperarSegundos(10);
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_ANALYTE.getTexto())).click();
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGANALYTE.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_ANALYTE.getTexto())).clear();
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGANALYTE.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_ANALYTE.getTexto())).sendKeys(analyte);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGANALYTE.getTexto()));
                utilSelenium.getDriver().findElement(By.id(CamposResultAndLot.INP_ANALYTE.getTexto())).sendKeys(Keys.ENTER);
                utilSelenium.esperarHastaElementoInvisible(By.id(CamposResultAndLot.DIV_LOADINGANALYTE.getTexto()));
                utilSelenium.esperarSegundos(15);
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- CUMPLIMENTAR CAMPO ANALYTE - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- CUMPLIMENTAR CAMPO ANALYTE - " + e.getMessage() + " - ERROR");
        }
    }

    /**
     *  Comprobar que el total de registros se corresponde con el total esperado
     * @param utilSelenium variable para interactuar con el webdrive y el log
     * @param total número total de registros que tiene que mostrar la búsqueda
     */
    public void comprobarResultado(UtilSelenium utilSelenium, String total) {
        utilSelenium.getLogger().info("-- COMPROBAR RESULTADO - INICIO");
        boolean resultado = Boolean.FALSE;
        List<WebElement> lista = null;
        try {
            lista = utilSelenium.getDriver().findElements(By.xpath(CamposResultAndLot.TR_RESULTADOS.getTexto()));
            for (int i = 0; i < lista.size(); i++) {
                utilSelenium.getLogger().info("Resultado " + i + ": " + lista.get(i).getText());
            }
        } catch (ElementNotVisibleException e) {
            utilSelenium.getLogger().error("-- COMPROBAR RESULTADO - " + e.getMessage() + " - ERROR", e);
            Assert.isTrue(Boolean.FALSE, "-- COMPROBAR RESULTADO - " + e.getMessage() + " - ERROR");
        } finally {
            utilSelenium.getLogger().info("-- COMPROBAR RESULTADO - FIN");
            Assert.isTrue(lista.size() == Integer.parseInt(total), "El total de resultado mostrados no es el esperado");
        }
    }
}
