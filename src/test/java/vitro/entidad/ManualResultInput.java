package vitro.entidad;

/**
 * Clase ManualResultInput
 *
 * Clase de tipo objeto del formulario de búsqueda del sub menu Manual Result Input
 *
 * @author paco
 * @version 1.0
 */
public class ManualResultInput {

    // Atributos del objeto
    private String fromDate;
    private String toDate;
    private String labDepInst;
    private String controlLevelVialLot;
    private String analyte;

    /**
     * Constructor con 5 parámetros
     * @param fromDate campo del formulario
     * @param toDate campo del formulario
     * @param labDepInst campo del formulario
     * @param controlLevelVialLot campo del formulario
     * @param analyte campo del formulario
     */
    public ManualResultInput(String fromDate, String toDate, String labDepInst, String controlLevelVialLot, String analyte) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.labDepInst = labDepInst;
        this.controlLevelVialLot = controlLevelVialLot;
        this.analyte = analyte;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getLabDepInst() {
        return labDepInst;
    }

    public void setLabDepInst(String labDepInst) {
        this.labDepInst = labDepInst;
    }

    public String getControlLevelVialLot() {
        return controlLevelVialLot;
    }

    public void setControlLevelVialLot(String controlLevelVialLot) {
        this.controlLevelVialLot = controlLevelVialLot;
    }

    public String getAnalyte() {
        return analyte;
    }

    public void setAnalyte(String analyte) {
        this.analyte = analyte;
    }
}
