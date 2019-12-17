package vitro.pageobject;

public enum CamposResultAndLot {

    INP_LAPDEPINST("ctl09_cbpLab_cbxLab_I"),
    INP_CONTROLLEVELVIALLOT("ctl09_cbpControl_cbxMasterControl_I"),
    INP_ANALYTE("ctl09_cbpAssay_cbxAssay_I"),
    DIV_LOADINGLADDEP("ctl09_cbpLab_LPV"),
    DIV_LOADINGCONTROL("ctl09_cbpControl_LPV"),
    DIV_LOADINGANALYTE("ctl09_cbpAssay_LP"),
    DIV_LOADINGRESULT("BodyContentPlaceholder_gridNOrmal_TL"),
    BTN_APPLY("BodyContentPlaceholder_btnProcess_CD"),

    TR_RESULTADOS("//table[@class='dxgvTable dxgvRBB']/tbody/tr[contains(@class, 'dxgvDataRow')]"),
    TBL_RESULTADOS("BodyContentPlaceholder_gridNormal_DXMainTable")

    ;
    private final String texto;

    CamposResultAndLot(String texto) {
        this.texto = texto;
    }
    public String getTexto() {
        return texto;
    }
}
