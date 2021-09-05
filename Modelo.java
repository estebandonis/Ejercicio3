import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Random;


public class Modelo {
    private ArrayList<parqueo> parkingTotal = new ArrayList<parqueo>(10);
    private ArrayList<parqueo> parking = new ArrayList<parqueo>(10);
    private carro kar = new carro();
    private parqueo space = new parqueo();
    private int carrosRechazados;
    public String[] modelosCarros = {"European|U.S. Compact", "U.S. Standard", "U.S. Standard Large"};
    public Random rando = new Random();
    private int numParqueos = 0;
    private int numParqueosTotal = 0;
    private boolean agregado = false;
    private boolean removido = false;

    public boolean registrarNuevoCarro(String[] carro){
        carro nuevoCarro = new carro(carro[0], carro[1], carro[2]);
        for (int a = 0; a < parking.size(); a++){
            space = parking.get(a);
            boolean estaVacio = space.getOcupado();
            if (estaVacio == false){
                String modelo = space.getParaModelo();
                System.out.println(carro[2]);
                if(modelo == carro[2]){
                    space.setVehiculoOcupando(nuevoCarro);
                    space.setOcupado(true);
                    parking.set(a, space);
                    agregado = true;
                    break;
                }
            }
        }
        if (agregado == false){
            carrosRechazados += 1;
        }
        return agregado;
    }

    public void crearParqueos(){
        for (int i = 1; i < 5; i++){
            int num = rando.nextInt(2) + 0;
            parqueo nuevoParqueo = new parqueo(numParqueos, false, modelosCarros[num], true);
            parking.add(numParqueos, nuevoParqueo);
            numParqueos += 1;
        }
    }

    public boolean quitarCarro(String nuevaPlaca){
        for (int l = 0; l < numParqueos; l++){
            space = parking.get(l);
            boolean ocupado = space.getOcupado();
            if (ocupado == true){
                kar = space.getVehiculoOcupando();
                String placa = kar.getPlaca();
                if (placa.equals(nuevaPlaca)){
                    int num = rando.nextInt(2) + 0;
                    parqueo parqueoVacio = new parqueo(space.getNumero(), false, modelosCarros[num], true);
                    kar.setHoraFinal(LocalTime.now());
                    kar.setHoras(calcularHoras(kar.getHoraInicio(), kar.getHoraFinal()));
                    space.setVehiculoOcupando(kar);
                    parking.set(l, parqueoVacio);
                    parkingTotal.add(numParqueosTotal, space);
                    removido = true;
                    break;
                }
            }
        }

        return removido;
    }

    public ArrayList<parqueo> getParking() {
        return parking;
    }

    public int getNumParqueos() {
        return numParqueos;
    }

    public ArrayList<parqueo> getParkingTotal() {
        return parkingTotal;
    }

    public int getNumParqueosTotal() {
        return numParqueosTotal;
    }

    public LocalTime tiempoPromedio(){
        int promedioHoras = 0;
        int promedioMinutos = 0;
        for (int h = 0; h < numParqueosTotal; h++){
            parqueo park = parkingTotal.get(h);
            carro car = park.getVehiculoOcupando();
            LocalTime tiempo = LocalTime.parse(car.getHoras());
            int hora = tiempo.getHour();
            int min = tiempo.getMinute();
            promedioHoras += hora;
            promedioMinutos += min;
        }

        LocalTime promedioTiempo = LocalTime.parse(promedioHoras + ":" + promedioMinutos);
        return promedioTiempo;
    }

    public int getCarrosRechazados() {
        return carrosRechazados;
    }

    public String caracteristicasParqueo(){
        int contador0 = 0;
        int contador1 = 0;
        int contador2 = 0;
        int mayor = 3;
        String caracteristica = "";
        for(int m = 0; m < numParqueosTotal; m++){
            parqueo park = parkingTotal.get(m);
            String modelo = park.getParaModelo();
            if (modelo == modelosCarros[0]){
                contador0 += 1;
            }
            else if (modelo == modelosCarros[1]){
                contador1 += 1;
            }
            else if (modelo == modelosCarros[2]){
                contador2 += 1;
            }
        }
        
        if (contador0 > contador1){
            if (contador0 > contador2){
                mayor = 0;
            }
        }
        else{
            if (contador1 > contador2){
                mayor = 1;
            }
            else{
                mayor = 2;
            }
        }

        if (mayor == 0){
            caracteristica = modelosCarros[0];
        }
        else if (mayor == 1){
            caracteristica = modelosCarros[1];
        }
        else if (mayor == 2){
            caracteristica = modelosCarros[2];
        }

        return caracteristica;
    }

    public String calcularHoras(LocalTime horaInicial, LocalTime horaFinal){
        int horaInicio = horaInicial.getHour();
        int minInicio = horaInicial.getMinute();
        int horaFin = horaFinal.getHour();
        int minFin = horaFinal.getMinute();

        int horaTotal = horaFin - horaInicio;
        int minTotal = minFin - minInicio;
        String hora = String.valueOf(horaTotal)  + ":" + String.valueOf(minTotal);

        return hora;
    }
}
