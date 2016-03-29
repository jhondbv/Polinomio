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

    //Metodo Principal

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input, input2;
        Polinomio p1, p2, Resultado = null;
        int elec = 0;
        System.out.println("**BIENVENIDO**");
        System.out.println("Ingrese el primer polinomio. \n");
        input = scan.nextLine();
        p1 = new Polinomio(input);
        System.out.println("Ingrese el segundo polinomio.\n");
        input2 = scan.nextLine();
        p2 = new Polinomio(input2);
        //Se valida la estructura de los dos polinomios ingresados
        if (p1.RetornaPolinomio() == null || p2.RetornaPolinomio() == null) {
            System.err.println("El primer polinomio ó el segundo polinomio no posee una estructura correcta , revise la estructura");
            return;
        }
        //Navegación por las diferentes opciones
        while (elec != 5) {
            System.out.println("Ingrese el numero de la operacion que desea realizar: \n \n 1. Sumar los Polinomios \n 2. Multiplicar Polinomios\n 3. Indicar el Grado de los polinomios \n 4. Imprimir los términos independientes de los polinomios.\n 5. Salir\n");
            elec = scan.nextInt();
            switch (elec) {
                case 1:
                    System.out.println("Eligio Suma de polinomios ");
                    Resultado = new Polinomio(p1.sumar(p1.RetornaPolinomio(), p2.RetornaPolinomio()));
                    Resultado.MostrarPolinomio();
                    break;
                case 2:
                    System.out.println("Eligio Multiplicación de polinomios: \n");
                    Resultado = new Polinomio(p1.multiplicarPol(p1.RetornaPolinomio(), p2.RetornaPolinomio()));
                    Resultado.MostrarPolinomio();
                    break;
                case 3:
                    System.out.println("Grado del Polinommio 1: (" + p1.gradoPol() + ").\n\"Grado del Polinommio 2: (" + p2.gradoPol() + ").");
                    break;
                case 4:
                    System.out.println("Elijio ver los Terminos independientes: \nTermino independiente del polinomio 1: (" + p1.independiente() + ").\nTermino independiente del polinomio 2: (" + p2.independiente() + ").");
                    break;
                case 5:
                    System.err.println("Hasta la proxima.");
                    break;
            }
        }
    }
}
