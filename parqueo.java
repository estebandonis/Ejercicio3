/**
 * Clase que nos permite crear parqueos
 */

public class parqueo {
    private int numero;
    private boolean ocupado;
    private carro vehiculoOcupando;
    private String paraModelo;
    private boolean techado;

    /**
     * Constructor default de la clase
     */
    parqueo(){
        ocupado = false;
        paraModelo = "N/A";
        techado = true;
    }

    /**
     * Constructor que nos deja crear un parqueo con valores predeterminados que escojamos
     * @param num
     * @param ocupado
     * @param paraModelo
     * @param techado
     */
    parqueo(int num, boolean ocupado, String paraModelo, boolean techado){
        this.numero = num;
        this.ocupado = ocupado;
        this.paraModelo = paraModelo;
        this.techado = techado;
    }

    /**
     * Constructor que nos deja crear un parqueo con valores predeterminados que escojamos
     * @param numero
     * @param ocupado
     * @param car
     * @param paraModelo
     * @param techado
     */
    parqueo(int numero, boolean ocupado, carro car, String paraModelo, boolean techado){
        this.numero = numero;
        this.ocupado = ocupado;
        vehiculoOcupando = car;
        this.paraModelo = paraModelo;
        this.techado = techado;
    }

    /**
     * Regresa el valor numero
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Regresa el valor de ocupado
     * @return
     */
    public boolean getOcupado(){
        return ocupado;
    }

    /**
     * Usamos para determinar el valor de ocupado
     * @param ocupado
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Nos regresa el valor de paraModelo
     * @return
     */
    public String getParaModelo() {
        return paraModelo;
    }

    /**
     * Nos regresa el valor de techado
     * @return
     */
    public boolean getTechado(){
        return techado;
    }

    /**
     * Nos regresa el valor de vehiculoOcupado
     * @return
     */
    public carro getVehiculoOcupando() {
        return vehiculoOcupando;
    }

    /**
     * Definimos el valor de vehiculoOcupando
     * @param vehiculoOcupando
     */
    public void setVehiculoOcupando(carro vehiculoOcupando) {
        this.vehiculoOcupando = vehiculoOcupando;
    }
}
