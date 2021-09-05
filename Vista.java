import java.util.ArrayList;
import java.util.Scanner;

public class Vista{
    private Scanner scan = new Scanner(System.in);

    public void bienvenida(){
        System.out.println("Bienvenid@ a nuestro programa de administracion de parqueos");
    } 

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
        while (paso == false){
            try {
                String optionString = scan.nextLine();//Recibimos el valor como String para evitar un bug con el metodo nextLine()
                option = Integer.parseInt(optionString);//Lo cambiamos a Integer
                paso = true;
            } catch (Exception e) {
                System.out.println("Ingrese un valor correcto, por favor");
            }
        }
        return option;//regresamos el valor convertido
    }

    public String[] pedirDatosNuevoCarro(){
        String[] nuevoCarro = new String[3];
        boolean paso = false;
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

    public void carroAgregado(){
        System.out.println("Su carro fue agregado con exito al sistema");
    }

    public void carroNoAgregado(){
        System.out.println("No hay un parqueo para su automovil");
    }

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

    public void carroRemovido(){
        System.out.println("Su carro fue removido exitosamente");
    }

    public void carroNoEncontrado(){
        System.out.println("Su carro no fue removido porque no fue encontrado en el registro");
    }

    public void mostrarDisponibles(ArrayList<parqueo> parking, int limite){
        try {
            for (int num = 0; num < limite; num++){
                parqueo park = parking.get(num);
                if(park.getOcupado() == false){
                    System.out.println("---------------------------------");
                    System.out.println("Numero de parqueo: " + park.getNumero());
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en la impresion de los parqueos disponibles");
        }
    }

    public void mostrarRegistro(ArrayList<parqueo> parking, int limite){
        try {
            for (int num = 0; num < parking.size(); num++){
                parqueo park = parking.get(num);
                if(park.getOcupado() == false){
                    System.out.println("---------------------------------");
                    System.out.println("Numero de parqueo: " + num);
                    System.out.println("Contenido: ");
                    System.out.println("Disponibilidad: " + park.getOcupado());//Vamos extrayendo los datos de la base de datos y los vamos imprimiendo conforme los vamos extrayendo
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
                    System.out.println("Disponibilidad: " + park.getOcupado());//Vamos extrayendo los datos de la base de datos y los vamos imprimiendo conforme los vamos extrayendo
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

    public void mostrarEstadisticas(int horario, int promedio, int parqueo, int rechazados, String marca, String caracteristicas){

    }
}