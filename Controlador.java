/**
* Es donde se ejecuta todo el programa
* @author: Esteban Donis hiosu
* @version: 1.00
*/

public class Controlador {
    /**
     * Es el metodo que ejecuta todo el programa
     * @param args
     */
    public static void main(String[] args) {
        try {   
            Modelo model = new Modelo();
            Vista vis = new Vista();
            int opcion = 0;

            vis.bienvenida();
            model.obtenerDatosActuales();
            model.obtenerDatosTotales();
            while (opcion != 7){
                opcion = vis.menu();
                if (opcion == 1){
                    boolean agregado = model.registrarNuevoCarro(vis.pedirDatosNuevoCarro());
                    if (agregado == true){
                        vis.carroAgregado();
                    }
                    else{
                        vis.carroNoAgregado();
                    }
                }
                else if (opcion == 2){
                    boolean removido = model.quitarCarro(vis.pedirPlaca());
                    if (removido == true){
                        vis.carroRemovido();
                    }
                    else{
                        vis.carroNoEncontrado();
                    }
                }
                else if (opcion == 3){
                    vis.mostrarDisponibles(model.getParking());
                }
                else if (opcion == 4){
                    vis.mostrarRegistro(model.getParkingTotal());
                }
                else if (opcion == 5){
                    vis.mostrarEstadisticas(model.tiempoPromedio(), model.getCarrosRechazados(), model.caracteristicasParqueo());
                }
                else if (opcion == 6){
                    model.crearParqueos();
                }
                else if (opcion == 7){
                    model.sendDataActual();
                    model.sendDataTotal();
                    System.exit(0);//Se sale del programa
                }
                else{
                    System.out.println("Elija una opcion valida");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en el programa");
        } 
    }
}