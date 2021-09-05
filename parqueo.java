public class parqueo {
    private int numero;
    private boolean ocupado;
    private carro vehiculoOcupando;
    private String paraModelo;
    private boolean techado;

    parqueo(){
        ocupado = false;
        paraModelo = "N/A";
        techado = true;
    }

    parqueo(int num, boolean ocupado, String paraModelo, boolean techado){
        this.numero = num;
        this.ocupado = ocupado;
        this.paraModelo = paraModelo;
        this.techado = techado;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getOcupado(){
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getParaModelo() {
        return paraModelo;
    }

    public boolean getTechado(){
        return techado;
    }

    public carro getVehiculoOcupando() {
        return vehiculoOcupando;
    }

    public void setVehiculoOcupando(carro vehiculoOcupando) {
        this.vehiculoOcupando = vehiculoOcupando;
    }
}
