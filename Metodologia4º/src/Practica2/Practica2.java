package Practica2;

import Practica1.MatricesOperaciones;
import Practica1.leer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Carmen.Lacave
 */
public class Practica2 {
  public static void main(String []args) throws Exception{
	  apartado1();
	  apartado2();
	  
  }
  
  public static void apartado1() throws Exception{
	  char repetir;
          long resultadoIterativo = 0;
          long resultadoRecursivo = 0;
          
	  do{
              System.out.println("\n\nRealizaremos una prueba para un entero N");
              
              int n = leer.entero();

              char medida=Character.toUpperCase(leer.caracter("�En qu� unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos "));
              long tb0= obtenerTiempo(medida);
              iterativo(n);
              long tb1=obtenerTiempo(medida);
              resultadoIterativo = tb1 - tb0;
              System.out.println("  N   |	     Iterativa	   |  Recursiva  |");
              long ts0= obtenerTiempo(medida);
              recursivo(n);
              long ts1=obtenerTiempo(medida);
              resultadoRecursivo = ts1 - ts0;
              
              System.out.println("  " + n + "    |  " + resultadoIterativo + "      |  " + resultadoRecursivo + "   |  ");
		  
              repetir=Character.toUpperCase(leer.caracter("�Quieres repetir la prueba? (S=si/N=no)"));
	  }while (repetir=='S');
  }
  
  
 
  

  public static void apartado2(){
	  
    System.out.println("\n\nAhora realizaremos pruebas con distintos valores del vector");
    int[] valoresN= {1,5,6,7,8,9,10,11,12,13};// ESTOS VALORES LOS HE PUESTO YO PARA PROBRAR QUE FUNCIONA
    //100, 500, 1000, 5000, 8000, 9000, 10000, 11000, 20000, 50000}; He cambiado los valores del vector porq 
    		// la complejidad es de 2^n y con esos valores la complejidad es altisima y se va de tiempo.
    long resultadoIterativo = 0;
    long resultadoRecursivo = 0;
          
    char medida=Character.toUpperCase(leer.caracter("�En qu� unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos "));
    while(medida!='M' && medida!='N'){
        System.out.println("Elección erronea.");
        medida = Character.toUpperCase(leer.caracter("�En qu� unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos "));
    }
    
    System.out.println("  N   |	Iterativa |  Recursiva  |");

    for (int i = 0; i < valoresN.length; i++) {
       
        long tb0 = obtenerTiempo(medida);
        iterativo(valoresN[i]);
        long tb2 = obtenerTiempo(medida); 
        long ts0 = obtenerTiempo(medida);
        recursivo(valoresN[i]);
        long ts2 = obtenerTiempo(medida);
        
        resultadoIterativo = tb2 - tb0;
        resultadoRecursivo = ts2 - ts0;
        
        System.out.println("  " + valoresN[i] + "    |     "  + resultadoIterativo + "      |  " + resultadoRecursivo + "   |  ");
    }

  	
    System.out.printf("\n\n");
  }
  public static int iterativo(int n){ /// complejidad lineal n
	  int  fibonaci[]=new int [n+1];
	  int suma=0;
	  
	  for(int i =0;i<=n;i++){
		  if(i==0 || i==1){
			  suma=i;
			  fibonaci[i]=suma;
		  }else{
			  suma=fibonaci[i-1]+fibonaci[i-2];
			  fibonaci[i]=suma;
		  }
	  }
	  return suma;
  }
  public static int recursivo(int n){
	  int fibonacciR;
	  if (n==0 || n==1){
		fibonacciR =n;
	  }else{
		  fibonacciR=recursivo(n-2)+recursivo(n-1);
	  }
	return fibonacciR;
  }
	
  public static long obtenerTiempo(char medida){
      long resultado = 0;
      
      switch(medida){
          case 'M':
              resultado = System.currentTimeMillis();
              break;
          case 'N':
              resultado = System.nanoTime();
              break;    
      }
      return resultado;
  }
          

}


