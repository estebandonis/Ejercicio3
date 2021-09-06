/**
 * Clase que contienen todos los calculos
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Nos sirve para registrar un nuevo carro en el registro
     * @param carro
     * @return
     */
    public boolean registrarNuevoCarro(String[] carro){
        carro nuevoCarro = new carro(carro[0], carro[1], carro[2]);//Creamos un nuevo carro
        agregado = false;
        for (int a = 0; a < parking.size(); a++){//creamos un ciclo para recorrer todo el registro
            space = parking.get(a);//Extraemos el spacio
            boolean estaLleno = space.getOcupado();//Obtenemos si esta lleno
            if (estaLleno == false){//Revisamos si el espacio esta ocupado o no
                String modelo = space.getParaModelo();
                String modeloCarro = carro[2];
                if(modelo.equals(modeloCarro)){//Revisamos si se usa en el mismo espacio
                    space.setVehiculoOcupando(nuevoCarro);//Agregamos el carro al espacio y cambiamos su disponibilidad
                    space.setOcupado(true);
                    parking.set(a, space);
                    agregado = true;
                    break;
                }
            }
        }
        if (agregado == false){//Si no le decimos que fue rechazado
            carrosRechazados += 1;
        }
        return agregado;
    }

    /**
     * Sirve para crear más parqueos
     */
    public void crearParqueos(){
        for (int i = 1; i < 5; i++){//Agregamos cinco espacio más al array
            int num = rando.nextInt(2) + 0;
            parqueo nuevoParqueo = new parqueo(numParqueos, false, modelosCarros[num], true);
            parking.add(numParqueos, nuevoParqueo);
            numParqueos += 1;
        }
    }

    /**
     * Sirve para quitar un carro del registro Actual y trasladarlo al total
     * @param nuevaPlaca
     * @return
     */
    public boolean quitarCarro(String nuevaPlaca){
        for (int l = 0; l < numParqueos; l++){//Recorremos todos los espacio
            space = parking.get(l);
            boolean ocupado = space.getOcupado();
            if (ocupado == true){//Verificamos si esta ocupado
                kar = space.getVehiculoOcupando();
                String placa = kar.getPlaca();
                if (placa.equals(nuevaPlaca)){//Verificamos si cumple con la misma placa
                    int num = rando.nextInt(2) + 0;//Si es asi
                    parqueo parqueoVacio = new parqueo(space.getNumero(), false, modelosCarros[num], true);//Generamos un nuevo parqueo
                    kar.setHoraFinal(LocalTime.now());//Ponemos su hora de salida
                    kar.setHoras(calcularHoras(kar.getHoraInicio(), kar.getHoraFinal()));//Calculamos sus horas
                    space.setVehiculoOcupando(kar);
                    parking.set(l, parqueoVacio);//Definimos el espacio como vacio
                    parkingTotal.add(numParqueosTotal, space);//Agregamos el carro al registro total
                    removido = true;
                    break;
                }
            }
        }

        return removido;
    }

    /**
     * Obtenemos los datos del file
     */
    public void obtenerDatosActuales (){
        String pathActual = "actual.csv";//Establecemos la ruta
        String line = "";
        int contador = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathActual));//Leemos el documento

            while((line = br.readLine()) != null){//Recorremos todas las lineas
                String[] values = line.split(",");
                int num = Integer.parseInt(values[0]);//Asignamos todos los valores a una variable transformandolos a su valor
                boolean ocupado = Boolean.parseBoolean(values[1]);
                
                if (ocupado == true){
                    LocalTime horaInicio = LocalTime.parse(values[2]);
                    LocalTime horaFinal = LocalTime.parse(values[3]);
                    String horas = values[4];
                    String placa = values[5];
                    String marca = values[6];
                    String modelo = values[7];

                    carro car = new carro(horaInicio, horaFinal, horas, placa, marca, modelo);
                    
                    String paraModelo = values[8];
                    boolean techado = Boolean.parseBoolean(values[9]);
                    parqueo park = new parqueo(num, ocupado, car, paraModelo, techado);//Los vamos agregando a nuestra base de datos
                    parking.add(contador, park);
                }
                else{
                    String paraModelo = values[2];
                    boolean techado = Boolean.parseBoolean(values[3]);
                    parqueo park = new parqueo(num, ocupado, paraModelo, techado);
                    parking.add(contador, park);
                }
            } 
            br.close();             
            numParqueos = parking.size();//Definimos la variable
        } catch (FileNotFoundException e) {
            System.out.println("No funciono");
        } catch (IOException e) {
            System.out.println("No funciono");
        }
    }

    /**
     * Enviamos los datos al file externo
     */
    public void sendDataActual(){
        String contenido = "";//Creamos la variable que va a tener todos los datos
        for(int m = 0; m < parking.size(); m++){//Recorremos la base de datos
            parqueo park = parking.get(m);
            String num = String.valueOf(park.getNumero());
            String ocupado = String.valueOf(park.getOcupado());
            if(park.getOcupado() == false){//Evaluamls si el parque esta ocupado
                String paraModelo = String.valueOf(park.getParaModelo());
                String techado = String.valueOf(park.getTechado());
                contenido = contenido.concat(num+","+ocupado+","+paraModelo+","+techado+ "\n");//Agregamos los datos a la variable
            }
            else{
                carro car = park.getVehiculoOcupando();
                String horaIncio = String.valueOf(car.getHoraInicio());
                String horaFinal = String.valueOf(car.getHoraFinal());
                String horas = car.getHoras();
                String placa = car.getPlaca();
                String marca = car.getMarca();
                String modelo = car.getModelo();

                String paraModelo = String.valueOf(park.getParaModelo());//Hacemos lo mismo para cuando ocupado es true
                String techado = String.valueOf(park.getTechado());
                contenido = contenido.concat(num+","+ocupado+","+horaIncio+","+horaFinal+","+horas+","+placa+","+marca+","+modelo+","+paraModelo+","+techado + "\n");
            }
        }

        try {
            FileWriter writer = new FileWriter("actual.csv");//Enviamos los nuevos datos al file
            writer.write(contenido);
            writer.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
    }

    /**
     * Enviamos los datos al file total
     */
    public void sendDataTotal(){
        String contenido = "";
        for(int m = 0; m < parkingTotal.size(); m++){
            parqueo park = parkingTotal.get(m);
            String num = String.valueOf(park.getNumero());
            String ocupado = String.valueOf(park.getOcupado());
            if(park.getOcupado() == false){
                String paraModelo = String.valueOf(park.getParaModelo());
                String techado = String.valueOf(park.getTechado());
                contenido = contenido.concat(num+","+ocupado+","+paraModelo+","+techado+ "\n");
            }
            else{
                carro car = park.getVehiculoOcupando();
                String horaIncio = String.valueOf(car.getHoraInicio());
                String horaFinal = String.valueOf(car.getHoraFinal());
                String horas = car.getHoras();
                String placa = car.getPlaca();
                String marca = car.getMarca();
                String modelo = car.getModelo();

                String paraModelo = String.valueOf(park.getParaModelo());
                String techado = String.valueOf(park.getTechado());
                contenido = contenido.concat(num+","+ocupado+","+horaIncio+","+horaFinal+","+horas+","+placa+","+marca+","+modelo+","+paraModelo+","+techado + "\n");
            }
        }
        
        try {
            FileWriter writer = new FileWriter("total.csv");
            writer.write(contenido);
            writer.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
    }

    /**
     * Obtenemos los datos del file total
     */
    public void obtenerDatosTotales (){
        String pathTotal = "total.csv";
        String line = "";
        int contador = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(pathTotal));

            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                int num = Integer.parseInt(values[0]);
                boolean ocupado = Boolean.parseBoolean(values[1]);
                
                if (ocupado == true){
                    LocalTime horaInicio = LocalTime.parse(values[2]);
                    LocalTime horaFinal = LocalTime.parse(values[3]);
                    String horas = values[4];
                    String placa = values[5];
                    String marca = values[6];
                    String modelo = values[7];

                    carro car = new carro(horaInicio, horaFinal, horas, placa, marca, modelo);
                    
                    String paraModelo = values[8];
                    boolean techado = Boolean.parseBoolean(values[9]);
                    parqueo park = new parqueo(num, ocupado, car, paraModelo, techado);
                    parkingTotal.add(contador, park);
                }
                else{
                    String paraModelo = values[2];
                    boolean techado = Boolean.parseBoolean(values[3]);
                    parqueo park = new parqueo(num, ocupado, paraModelo, techado);
                    parkingTotal.add(contador, park);
                }
            } 
            br.close();            
            numParqueosTotal = parkingTotal.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtenemos la variable parking
     * @return
     */
    public ArrayList<parqueo> getParking() {
        return parking;
    }

    /**
     * Obtenemos la variable numparqueos
     * @return
     */
    public int getNumParqueos() {
        return numParqueos;
    }

    /**
     * Obtenemos la variable parkingTotal
     * @return
     */
    public ArrayList<parqueo> getParkingTotal() {
        return parkingTotal;
    }

    /**
     * Obtenemos la variable numParqueosTotal
     * @return
     */
    public int getNumParqueosTotal() {
        return numParqueosTotal;
    }

    /**
     * Calculamos el valor del tiempo promedio
     * @return
     */
    public String tiempoPromedio(){
        String promedio = "";
        try {
            int promedioHoras = 0;//Creamos dos variables para almacenar las horas y minutos
            int promedioMinutos = 0;
            for (int h = 0; h < numParqueosTotal; h++){//Recorremos todo el registro
                parqueo park = parkingTotal.get(h);
                carro car = park.getVehiculoOcupando();
                LocalTime tiempo = LocalTime.parse(car.getHoras());//Obtenemos las hora inicial y final
                int hora = tiempo.getHour();//Separamos la hora y los minutos
                int min = tiempo.getMinute();
                promedioHoras += hora;//Sacamos el promedio de horas y minutos
                promedioMinutos += min;
            }
            promedio = promedioHoras + ":" + promedioMinutos;//Juntamos el promedio
        } catch (Exception e) {
            System.out.println("No salio el calculo");

        }
        return promedio;
    }

    /**
     * Regresamos el valor de carrosRechazados
     * @return
     */
    public int getCarrosRechazados() {
        return carrosRechazados;
    }

    /**
     * Obtenemos el valor de la caracteristica más popular
     * @return
     */
    public String caracteristicasParqueo(){
        int contador0 = 0;
        int contador1 = 0;
        int contador2 = 0;
        int mayor = 3;
        String caracteristica = "";
        for(int m = 0; m < numParqueosTotal; m++){//Vamos contando cada vez que aparezca cada modelo 
            parqueo park = parkingTotal.get(m);
            String modelo = park.getParaModelo();
            if (modelo.equals(modelosCarros[0])){
                contador0 += 1;
            }
            else if (modelo == modelosCarros[1]){
                contador1 += 1;
            }
            else if (modelo == modelosCarros[2]){
                contador2 += 1;
            }
        }
        
        if (contador0 > contador1){//Comparamos los resultados para saber cual es el mas grande
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

    /**
     * Calculamos la horas que lleva
     * @param horaInicial
     * @param horaFinal
     * @return
     */
    public String calcularHoras(LocalTime horaInicial, LocalTime horaFinal){//Sacamos las horas y las separamos en horas y minutos y despues las usmamos
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
