package vitro.pageobject;

/**
 * Clase CamposMenu
 *
 * clase de tipo enum que contiene el DOM de los elementos del menu superior que componen la aplicación
 *
 * @author paco
 * @version 1.0
 */
public enum CamposMenu {

    LI_MENU("//li[contains(@class, 'dxm-item')]//span"),
    H3_SUBMENU("//div[@class='titulo_blue']/h3/span"),
    DIV_LOADING("loadingPanel"),
    WIDGET_RESULTS("BodyContentPlaceholder_wResultsPanel_PW-1"),
    IMG_PRINCIPAL("//div[@id='body']/div/div[@id='image']/img"),

    ;

    private final String texto;

    CamposMenu(String texto) {
        this.texto = texto;
    }


    public String getTexto() {
        return texto;
    }
}
