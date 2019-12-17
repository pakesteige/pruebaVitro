package vitro.pageobject;

public enum CamposLogin {

    INP_IDENTIFIER("txtId"),
    INP_USER("//div[contains(@class, 'login')]/input[@name='username' and contains(@placeholder, 'User')]"),
    INP_PASS("//div[@class='login-block']/input[@name='password']"),
    BTN_SIGNIN("//div/button[@type='button']"),
    DIV_LOADING("loadingPanel"),
    ;

    private final String texto;

    CamposLogin(String texto) {
        this.texto = texto;
    }


    public String getTexto() {
        return texto;
    }
}
