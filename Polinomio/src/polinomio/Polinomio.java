/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomio;

import java.util.regex.*;// validacion de expresiones regulares 

/**
 *
 * @author jhon
 */
public class Polinomio {
  
    private int[] polinomio;
    //Expresion regular para validar la estructura de un polinomio
    private static final String PATRON_POLINOMIO = "(-?\\b\\d+)[xX]\\^(-?\\d+\\b)";
    private Pattern patron;
    private Matcher match;

    public Polinomio(String cadenaPolinomio) {
        patron = Pattern.compile(PATRON_POLINOMIO);//se carga el patron polinomio
        polinomio = ConvertirPolinomio(cadenaPolinomio);
    }
    public Polinomio(int [] pol) //constructor para la multilpicacion
    {
        polinomio = pol;
    }

    private int[] ConvertirPolinomio(String cadena) {
        match = patron.matcher(cadena);//validacion de expresion regular , captura coeficiente y exponente
        int count = 1;
        
        if (match.lookingAt()) {
            //contador de monomios , para saber el tamaño del vector forma 2 
            while (match.find()) {
                count++;
            }
            //cantidad de monomios multiplicado por 2 , para almacenar
            //coeficiente y exponente .
            int[] tempPolinomio = new int[(count * 2) + 1];
            match.reset();//se reinicia el contador para recorrer cada termino (coeficiente y exponente)

            tempPolinomio[0] = count;//numero de terminos

            try {
                //se recorre cada monomio extrayendo el coeficiente y exponente
                for (int i = 1; i < count * 2; i = i + 2) {

                    match.find();
                    //System.out.println(match.group(1));
                    //System.out.println(match.group(2));

                    tempPolinomio[i+1] = Integer.parseInt(match.group(1));//coeficiente
                    tempPolinomio[i] = Integer.parseInt(match.group(2));//exponente
                }

            } catch (Exception ex) {
                System.err.println(ex);
                return null;
            }
            return tempPolinomio;
        } else {
            return null;
        }

    }

    public int[] RetornaPolinomio() {
        return polinomio;
    }

    public void MostrarPolinomio() {
        String output = "";
        // se valida que el polinomio no este vacio
        if (polinomio != null) {
            for (int i = 1; i < polinomio.length; i = i + 2) {
               // if (polinomio[i] > 0 && i > 1) { se cambia por el orden de los coeficientes
                if (polinomio[i+1] > 0 && i > 1) {
                    output += "+";
                }
                output = String.format("%s%sx^%s", output, polinomio[i+1], polinomio[i]);// se concatena exponente y coeficiente
            }
            System.out.println(output);
        } else {
            System.out.println("Polinomio Vacio");
        }

    }
     
     public int [] multiplicarPol(int[] pol1, int[] pol2)
        { 
            int tamAux;
            int tamReal;
            int n1;
            int n2;
            int k = 2;
            int cont = 0;
           
            tamAux = ((pol1[0] * pol2[0])*2)+1;
           int [] polAux = new int [tamAux];
           n1 = pol1.length;
           n2 = pol2.length;
           polAux[0] = pol1[0]*pol2[0];
           
             for( int i=2; i<= n1; i = i+2)//multiplicacion inicial de los polinomios
            {
            for( int j=2; j<= n2; j = j+2)
            {     
                polAux[k] = pol1[i]*pol2[j];
                polAux[k-1] = pol1[i-1] + pol2[j-1]; 
                k = k+2;
            }
            }
                    
         int aux;
         for(int i=1;i<polAux.length-1;i=i+2){ //ordenamiento del polinomio resultado
              for(int j=1;j<polAux.length-i-1;j=j+2){
                   if(polAux[j+2]>polAux[j]){
                      aux=polAux[j+2];
                      polAux[j+2]=polAux[j];
                      polAux[j]=aux;
                      
                      aux=polAux[j+3];
                      polAux[j+3]=polAux[j+1];
                      polAux[j+1]=aux;
                   }
              }
         }
            int j=3;
            for (int i=1; i<polAux.length; i=i+2) //sumando terminos semejantes
             {    j=i+2;         
               while (j<polAux.length){
                 
                   if (polAux[j] == polAux[i]&& polAux[i]!= 0)
                 {
                    
                    polAux[i+1] = polAux[i+1] + polAux[j+1];
                    polAux[j] =0;
                    polAux[j+1] =0;
                    cont++;
                 }
                   j = j+2;
               }
             }
            
           tamReal = (((pol1[0]*pol2[0])*2)+1) - (cont*2);  
          int [] multiplicados = new int[tamReal];
              multiplicados[0] = (tamReal-1)/2;
                   j=1;
               int i=1;
            while ( i<multiplicados.length) //creando el resultado en un vector forma 2
             {           
              if(polAux[j+1]==0)
                   {
                   j = j+2;
                   }
              else{
                  if(polAux[j+1]!=0)
                 {       
                     multiplicados[i] = polAux[j];
                     multiplicados[i+1] = polAux[1+j];
                     j = j+2;
                     i = i+2;
                 } 
              }  
             }
           
            return multiplicados;  
        }

    public int gradoPol() //muestra el grado de un polinomio
    {
        int grado;
        grado = polinomio[1];  
        return grado;
    }


}
