package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;


    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
     * 
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }






    /**
     * Descripcion: El metodo permite guardar los datos que el usuario ingresa para luego ser calculados
     * pre:  El arreglo esta inicializado y vacio
     * pos: El arreglo adquiere el valor de precio que se ingresa
     * @param precios double[] El precio de la referencia
     * @param unidades int[] La cantidad de unidades de la referencia
     */
    public static void solicitarDatos(){
        for(int i=0;i<unidades.length; i++){
            System.out.println("Digite el precio de la referencia");
            double precio = reader.nextDouble();
            System.out.println("Digite la cantidad de unidades de esa referencia");
            int unidad = reader.nextInt();
            precios[i] = precio;
            unidades[i] = unidad;
        }  
     
    }



    /**
     * Descripcion: El metodo permite calcular la cantidad total de unidades vendidas
     * pre: El arreglo esta inicializado y contiene la cantidad de unidades ingresadas hasta el momento
     * pos: El arreglo queda con la cantidad total de unidades
     * @param unidades int[] La cantidad de unidades de la referencia
     * @param suma int suma todos las unidades (elementos del arreglo)
     */
    public static int calcularTotalUnidadesVendidas(){
        int suma = 0;
        for(int i = 0; i<unidades.length; i++){
            suma += unidades[i];
        }
        
        return suma;
    }




    /**
     * Descripcion: El metodo permite calcular el precio promedio de todas las referencias
     * pre: El arreglo esta inicializado y contiene todos los precios ingresados
     * pos: El arreglo queda con el promedio del precio
     * @param precios double[] los precios de cada unidad
     * @param promedio double resultado de la suma y division de los precios
     */
    public static double calcularPrecioPromedio(){
        double suma = 0;
        double promedio = 0; 
        for(int i = 0; i<precios.length; i++){
            suma += precios[i];
        }
        promedio = suma / precios.length;
        
        return promedio;
    }




/**
     * Descripcion: El metodo permite calcular las ventas totales
     * pre: El arreglo esta inicializado y contiene todos los precios ingresados
     * pos: El arreglo se multiplica por la cantidad total de unidades
     * @param precios double[] los precios de cada unidad
     * @param promedio double resultado de la suma y division de los precios
     */
    public static double calcularVentasTotales(){
        double totalVentas = 0;
        for(int i = 0; i<unidades.length; i++){
            totalVentas = unidades[i] * precios[i];
        }
        
        return totalVentas;
    }






    public static int consultarReferenciasSobreLimite(double limite){
        int contador = 0;
        int i;
        for(i=0; i<unidades.length; i++){
            double totalVenta = precios[i] * unidades[i];
            if(totalVenta>=limite ){
                contador++;
            }

        }

        return contador;
    }

}
