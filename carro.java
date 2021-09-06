/**
 * Clase que nos permite crear carros
 */

import java.time.LocalTime;

public class carro {
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private String horas = "0";
    private String placa;
    private String marca;
    private String modelo;

    /**
     * Constructor de carro
     */
    carro(){
        horaInicio = LocalTime.now();
        horaFinal = LocalTime.of(0, 0);
    }

    /**
     * Constructor de clases que te deja definir los valores predeterminados
     * @param placa
     * @param marca
     * @param modelo
     */
    carro(String placa, String marca, String modelo){
        horas = "0";
        horaInicio = LocalTime.now();
        horaFinal = LocalTime.of(0, 0);
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    /**
     * Constructor de clases que te deja definir los valores predeterminados
     * @param horaInicio
     * @param horaFinal
     * @param horas
     * @param placa
     * @param marca
     * @param modelo
     */
    carro(LocalTime horaInicio, LocalTime horaFinal, String horas, String placa, String marca, String modelo){
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.horas = horas;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    /**
     * Nos regresa el valor de horaInicio
     * @return
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Nos regresa el valor de horaFinal
     * @return
     */
    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    /**
     * Definimos el valor de horaFinal
     * @param horaFinal
     */
    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Nos regresa el valor de horas
     * @return
     */
    public String getHoras() {
        return horas;
    }

    /**
     * Definimos el valor de horas
     * @param horas
     */
    public void setHoras(String horas) {
        this.horas = horas;
    }

    /**
     * Nos regresa el valor de marca
     * @return
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Nos regresa el valor de modelo
     * @return
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Nos regresa el valor de placa
     * @return
     */
    public String getPlaca() {
        return placa;
    }
}
