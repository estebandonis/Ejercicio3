import java.time.LocalTime;

public class carro {
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private String horas;
    private String placa;
    private String marca;
    private String modelo;

    carro(){
        horaInicio = LocalTime.now();
        horaFinal = LocalTime.of(0, 0);
    }

    carro(String placa, String marca, String modelo){
        horas = "";
        horaInicio = LocalTime.now();
        horaFinal = LocalTime.of(0, 0);
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }
}
