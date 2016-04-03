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

    /**
     *Valida la estructura del texto ingresado para convertir en polinomio 
     * @param cadenaPolinomio  cadena de caracteres que representan al polinomio 
     */
    public Polinomio(String cadenaPolinomio) {
        patron = Pattern.compile(PATRON_POLINOMIO);//se carga el patron polinomio
        polinomio = ConvertirPolinomio(cadenaPolinomio);
    }

    //constructor para la multilpicacion

    /**
     *Constructor 
     * @param pol arreglo de entero representado como vector forma 2
     */

    public Polinomio(int[] pol) {
        polinomio = pol;
    }

    
   
/**
 * Metodo para convertir los polinomios ingresados
 * @param cadena polinomio en una cadena de texto
 * @return  vector en forma 2 como representacion del polinomio ingresado por parametro
 */
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
                    tempPolinomio[i + 1] = Integer.parseInt(match.group(1));//coeficiente
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

    //Método que devuelve un polinomio cuando sea requerido

    /**
     *Retorna el polinomio correspondiente
     * @return Vector en fomra 2 correspondiente al polinomio actual
     */

    public int[] RetornaPolinomio() {
        return polinomio;
    }

    //

    /**
     * Método para mostrar un polinomio con toda su estructura.
     */

    public void MostrarPolinomio() {
        String output = "";
        // se valida que el polinomio no este vacio
        if (polinomio != null) {
            for (int i = 1; i < polinomio.length; i = i + 2) {
                if (polinomio[i + 1] > 0 && i > 1) {
                    output += "+";
                }
                // se concatena exponente y coeficiente
                output = String.format("%s%sx^%s", output, polinomio[i + 1], polinomio[i]);
            }
            System.out.println(output);
        } else {
            System.out.println("Polinomio Vacio");
        }
    }

    

    /**
     * Método para multiplicar polinomios 
     * @param pol1 polinomio 1 en vector forma 2 
     * @param pol2 polinomio 2 en vector forma 2 
     * @return arreglo vector forma 2 resultado de la multiplicacion
     */

    public int[] multiplicarPol(int[] pol1, int[] pol2) {
        int tamAux;
        int tamReal;
        int n1;
        int n2;
        int k = 2;
        int cont = 0;
        tamAux = ((pol1[0] * pol2[0]) * 2) + 1;
        int[] polAux = new int[tamAux];
        n1 = pol1.length;
        n2 = pol2.length;
        polAux[0] = pol1[0] * pol2[0];
        //multiplicacion inicial de los polinomios
        for (int i = 2; i <= n1; i = i + 2) {
            for (int j = 2; j <= n2; j = j + 2) {
                polAux[k] = pol1[i] * pol2[j];
                polAux[k - 1] = pol1[i - 1] + pol2[j - 1];
                k = k + 2;
            }
        }
        int aux;
        //ordenamiento del polinomio resultado
        for (int i = 1; i < polAux.length - 1; i = i + 2) {
            for (int j = 1; j < polAux.length - i - 1; j = j + 2) {
                if (polAux[j + 2] > polAux[j]) {
                    aux = polAux[j + 2];
                    polAux[j + 2] = polAux[j];
                    polAux[j] = aux;
                    aux = polAux[j + 3];
                    polAux[j + 3] = polAux[j + 1];
                    polAux[j + 1] = aux;
                }
            }
        }
        int j = 3;
        //sumando terminos semejantes
        for (int i = 1; i < polAux.length; i = i + 2) {
            j = i + 2;
            while (j < polAux.length) {

                if (polAux[j] == polAux[i] && polAux[i] != 0) {
                    polAux[i + 1] = polAux[i + 1] + polAux[j + 1];
                    polAux[j] = 0;
                    polAux[j + 1] = 0;
                    cont++;
                }
                j = j + 2;
            }
        }
        tamReal = (((pol1[0] * pol2[0]) * 2) + 1) - (cont * 2);
        int[] multiplicados = new int[tamReal];
        multiplicados[0] = (tamReal - 1) / 2;
        j = 1;
        int i = 1;
        //creando el resultado en un vector forma 2
        while (i < multiplicados.length) {
            if (polAux[j + 1] == 0) {
                j = j + 2;
            } else {
                if (polAux[j + 1] != 0) {
                    multiplicados[i] = polAux[j];
                    multiplicados[i + 1] = polAux[1 + j];
                    j = j + 2;
                    i = i + 2;
                }
            }
        }
        return multiplicados;
    }

    

    /**
     *Suma dos polinomios
     * @param a polinomio a representado en vector forma 2 
     * @param b polinomio b representado en vector forma 2 
     * @return arreglo en vector forma 2 resultado de la suma de a+b
     */

    public int[] sumar(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int i = 1;
        int j = 1;
        int k = 1;
        char opc = ' ';
        int tam = ((a[0] + b[0]) * 2) + 1;
        int[] h = new int[tam];
        while (i < m && j < n) {
            if (a[i] == b[j]) {
                opc = 'a';
            } else if (a[i] < b[j]) {
                opc = 'b';
            } else if (a[i] > b[j]) {
                opc = 'c';
            }
            switch (opc) {
                case 'a':
                    h[k + 1] = a[i + 1] + b[j + 1];
                    if (h[k + 1] != 0) {
                        h[k] = a[i];
                        k += 2;
                    }
                    i += 2;
                    j += 2;
                    break;
                case 'b':
                    h[k] = b[j];
                    h[k + 1] = b[j + 1];
                    j += 2;
                    k += 2;
                    break;
                case 'c':
                    h[k] = a[i];
                    h[k + 1] = a[i + 1];
                    i += 2;
                    k += 2;
                    break;
            }
        }
        while (i < m) {
            h[k] = a[i];
            h[k + 1] = a[i + 1];
            i += 2;
            k += 2;
        }
        while (j < n) {
            h[k] = b[j];
            h[k + 1] = b[j + 1];
            j += 2;
            k += 2;
        }
        h[0] = (k - 1) / 2;
        //para ajustar el vector h, si no existe el peor de los casos(todos los exponentes son diferentes),
        //entonces la matriz h quedaria mas grande de lo que se necesita y en los ultimos elementos imprimiria 0(que es donde no hay nada)
        int[] x = new int[k];
        for (int t = 0; t < k; t++) {
            x[t] = h[t];
        }
        return x; // Devuelve el resultado de la suma de dos polinomios
    }


    /**
     *Método para mostrar el grado de un polinomio
     * @return entero correspondiente al grado del polinomio
     */

    public int gradoPol() {
        int grado;
        grado = polinomio[1];
        return grado;
    }

   

    /**
     *Método para mostrar el termino independiente de un polinomio
     * @return Coeficiente correspondiente al termino independiente
     */

    public int independiente() {
        int b = 0;
        int a = polinomio.length;
        if(polinomio[a-2]==0){
        b = polinomio[a-1];
      
        }
        return b;
    }
}
