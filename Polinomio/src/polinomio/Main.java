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
       Polinomio p2 = new Polinomio(input2);
       
       if(p.RetornaPolinomio()==null)
       {
           System.err.println("No se pudo crear el polinomio , revise la estructura");
       }
       else
           p.MostrarPolinomio();
               
        
    }
    
    
    
    
}
