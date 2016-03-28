/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomio;

import java.util.Scanner;
import polinomio.Polinomio;

/**
 *
 * @author jhon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el polinomio 1 .\n");
        String input = scan.nextLine();
        Polinomio p = new Polinomio(input);
        System.out.println("Ingrese el polinomio 2 .\n");
        String input2 = scan.nextLine();

        int elec = 0;      //variable de elecion para el menu    --arley
        Polinomio multi = null;     // polinomio para la multiplicacion --arley

        Polinomio p2 = new Polinomio(input2);

        if (p.RetornaPolinomio() == null || p2.RetornaPolinomio() == null) {
            System.err.println("No se pudo crear el polinomio , revise la estructura");
            return;
        }

        while (elec != 5) {     // MENU del programa  --arley
            System.out.println("Escriba el numero de la operacion que desea realizar: \n \n 1. Sumar los Polinomios \n 2. Multiplicar Polinomios\n 3. Indicar el Grado del polinomio \n 4. Imprimir los términos independientes de los polinomios.\n 5. Salir\n");

            elec = scan.nextInt();
            switch (elec) {

                case 1:
                    System.out.println("Suma de polinomios ");
                    break;

                case 2:
                    System.out.println("Resultado de la Multiplicacion de polinomios: \n");

                    multi = new Polinomio(p.multiplicarPol(p.RetornaPolinomio(), p2.RetornaPolinomio()));
                    multi.MostrarPolinomio();

                    break;

                case 3:
                    System.out.println("El grado del Polinommio 1 es: " + p.gradoPol());
                    System.out.println("El grado del Polinommio 2 es: " + p2.gradoPol());
                    if (multi!=null)
                    {
                        System.out.println("El grado de la multiplicacion de los 2 polinomios: " + multi.gradoPol());
                    }

                    break;
                case 4:

                    break;
                case 5:
                    System.err.println("Hasta la proxima.");
                    break;

            }

        }

    }

}
