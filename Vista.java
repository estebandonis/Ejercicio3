/**
 * Clase que es la responsable de interactuar con el usuario
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Vista{
    private Scanner scan = new Scanner(System.in);

    /**
     * Clase que le da la bienvenida al usuario
     */
    public void bienvenida(){
        System.out.println("Bienvenid@ a nuestro programa de administracion de parqueos");
    } 

    /**
     * Despliega el menu al usuario
     * @return
     */
    public int menu(){
        System.out.println("Elija una opción:");
		System.out.println("1. Registrar un nuevo carro");//Le damos todas las opciones disponibles
		System.out.println("2. Eliminar un carro del registro");
		System.out.println("3. Mostrar espacios disponibles");
        System.out.println("4. Mostrar el registro completo");
        System.out.println("5. Mostrar las estadisticas del parqueo");
        System.out.println("6. Agregar mas espacios de parqueo");
        System.out.println("7. Salir/n/n");

        boolean paso = false;
        int option = 0;
        while (paso == false){//Aplicamos un metodo para que escriba el 
            try {
                String optionString = scan.nextLine();//Recibimos el valor como String para evitar un bug con el metodo nextLine()
                option = Integer.parseInt(optionString);//Lo cambiamos a int
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingrese un valor correcto, por favor");
            }
        }
        return option;//regresamos el valor convertido
    }

    /**
     * Le pide los datos nuevos para agregar un carro
     * @return
     */
    public String[] pedirDatosNuevoCarro(){
        String[] nuevoCarro = new String[3];
        boolean paso = false;//Pedimos los datos de un carro, con su verificacion y luego se la enviamos en una String
        while (paso == false){
            try {
                System.out.println("Ingrese el numero de placa");
                nuevoCarro[0] = scan.nextLine();
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingrese un valor correcto, por favor");
            }
        }
        paso = false;
        while (paso == false){
            try {
                System.out.println("Ingrese la marca de su vehiculo");
                nuevoCarro[1] = scan.nextLine();
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingrese un valor correcto, por favor");
            }
        }
        paso = false;
        while (paso == false){
            try {
                System.out.println("Ingrese el modelo de su auto, elija el numero antes del modelo ([1 'European|U.S. Compact'], [2 'U.S. Standard'], [3 'U.S. Standard Large'])");
                String valorString = scan.nextLine();
                int valor = Integer.parseInt(valorString);
                if (valor == 1){
                    nuevoCarro[2] = "European|U.S. Compact";
                }
                else if (valor == 2){
                    nuevoCarro[2] = "U.S. Standard";
                }
                else if (valor == 3){
                    nuevoCarro[2] = "U.S. Standard Large";
                }
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingrese un numero de 1 a 3, por favor");
            }
        }

        return nuevoCarro;
    }

    /**
     * Metodo que le avisa al usuario que su carro fue agregado exitosamente
     */
    public void carroAgregado(){
        System.out.println("Su carro fue agregado con exito al sistema");
    }

    /**
     * Metodo que le avisa al usuario que su carro no fue agregado exitosamente
     */
    public void carroNoAgregado(){
        System.out.println("No hay un parqueo para su automovil");
    }

    /**
     * Metodo que pide la placa al usuario para eliminar un carro del registro
     * @return
     */
    public String pedirPlaca(){
        System.out.println("Ingrese el numero de placa del carro que quiere eliminar del registro");
        boolean paso = false;
        String placa = "";
        while (paso == false){
            try {
                placa = scan.nextLine();
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingrese un valor correcto, por favor");
            }
        }

        return placa;
    }

    /**
     * Metodo que le anuncia al usuario que el carro fue removido
     */
    public void carroRemovido(){
        System.out.println("Su carro fue removido exitosamente");
    }

    /**
     * Le anuncia al usuario que su carro no fue encontrado
     */
    public void carroNoEncontrado(){
        System.out.println("Su carro no fue removido porque no fue encontrado en el registro");
    }

    /**
     * Muestra los parqueos disponibles
     * @param parking
     */
    public void mostrarDisponibles(ArrayList<parqueo> parking){
        try {
            System.out.println("Espacios Disponibles: ");//Recorremos la base de datos y vamos imprimiendo solo los que esten disponibles
            for (int num = 0; num < parking.size(); num++){
                parqueo park = parking.get(num);
                if(park.getOcupado() == false){
                    System.out.println("---------------------------------");
                    System.out.println("Numero de parqueo: " + park.getNumero());
                }
            }
            System.out.println("---------------------------------");
        } catch (Exception e) {
            System.out.println("Ocurrio un error en la impresion de los parqueos disponibles");
        }
    }

    /**
     * Muestra el registro completo de carros
     * @param parking
     */
    public void mostrarRegistro(ArrayList<parqueo> parking){
        try {
            for (int num = 0; num < parking.size(); num++){//Recorremos la base de datos y vamos imprimiendo los datos de cada interación
                parqueo park = parking.get(num);
                if(park.getOcupado() == false){
                    System.out.println("---------------------------------");
                    System.out.println("Numero de parqueo: " + park.getNumero());
                    System.out.println("Contenido: ");
                    System.out.println("Disponibilidad: Disponible");//Vamos extrayendo los datos de la base de datos y los vamos imprimiendo conforme los vamos extrayendo
                    System.out.println("Para modelo: " + park.getParaModelo());
                    if (park.getTechado() == true){
                        System.out.println("Techado: Si esta techado");
                    }
                    else{
                        System.out.println("Techado: No esta techado");
                    }
                    System.out.println("---------------------------------");
                }
                else{
                    carro car = park.getVehiculoOcupando();
                    System.out.println("---------------------------------");
                    System.out.println("Numero de parqueo utilizado: " + park.getNumero());
                    System.out.println("Contenido: ");
                    System.out.println("Disponibilidad: Ocupado");//Vamos extrayendo los datos de la base de datos y los vamos imprimiendo conforme los vamos extrayendo
                    System.out.println("Para modelo: " + park.getParaModelo());
                    if (park.getTechado() == true){
                        System.out.println("Techado: Si esta techado");
                    }
                    else{
                        System.out.println("Techado: No esta techado");
                    }
                    System.out.println("---------------------------------");
                    System.out.println("Carro que ocupo el espacio: ");
                    System.out.println("Hora en el que ingreso: " + car.getHoraInicio().toString());
                    System.out.println("Hora en el que se fue: " + car.getHoraFinal().toString());
                    System.out.println("Horas que estuvo en el parqueo: " + car.getHoras());
                    System.out.println("Placa: " + car.getPlaca());
                    System.out.println("Marca: " + car.getMarca());
                    System.out.println("Modelo: " + car.getModelo());
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado");
        }
    }

    /**
     * Nor sirve para mostrar las estadisticas que se nos pidieron
     * @param promedio
     * @param rechazados
     * @param caracteristicas
     */
    public void mostrarEstadisticas(String promedio, int rechazados, String caracteristicas){
        System.out.println("El tiempo promedio es de: " + promedio);
        System.out.println("La cantidad de carros rechazados es de: " + rechazados);
        System.out.println("El modelo mas usado en parqueos es: " + caracteristicas);
    }
}