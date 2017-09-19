package Practica1;

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
public class PruebaOrdenacion {
  public static void main(String[] args) throws Exception{
	  apartado1();
	  apartado2();
  }
  
  public static void apartado1() throws Exception{
	  char repetir;
          long resultadoBurbuja = 0;
          long resultadoSelDirec = 0;
          
	  do{
              System.out.println("\n\nRealizaremos una prueba para un tama�o de vector dado");
              int[] A=crearVector();
              int[] B= new int [A.length];

              System.arraycopy(A,0,B,0,A.length);
              System.out.println("A\n"+MatricesOperaciones.mostrar(A));

              char medida=Character.toUpperCase(leer.caracter("�En qu� unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos "));
              long tb0= obtenerTiempo(medida);
              burbuja(B);
              long tb1=obtenerTiempo(medida);
              resultadoBurbuja = tb1 - tb0;

              int[] C=new int[A.length];
              System.arraycopy(A,0,C,0,A.length);
              System.out.println("  N   |	     Burbuja	   |  SeleccionDirecta  |");
              
              long ts0= obtenerTiempo(medida);
              seleccionDirecta(C);
              long ts1=obtenerTiempo(medida);
              resultadoSelDirec = ts1 - ts0;
              
              System.out.println("  " + A.length + "    |  " + resultadoBurbuja + "      |  " + resultadoSelDirec + "   |  ");
		  
              repetir=Character.toUpperCase(leer.caracter("�Quieres repetir la prueba? (S=si/N=no)"));
	  }while (repetir=='S');
  }
  
  
  public static int[] crearVector() throws Exception{
    int[] V=null;
    String direccion = "";
    char fuenteDatos=Character.toUpperCase(leer.caracter("�Desde d�nde quieres cargar los datos:\n T=teclado\n F=archivo\n A=crearlo con valores aleatorios"));
    
    while (fuenteDatos!='T' && fuenteDatos!='F' && fuenteDatos!='A'){
        System.out.println("Ha introducido una opci�n incorrecta. Vuelva a intentarlo");
        fuenteDatos=Character.toUpperCase(leer.caracter("�Desde d�nde quieres cargar los datos:\n T=teclado\n F=archivo\n A=crearlo con valores aleatorios"));
    }
    if (fuenteDatos == 'T'){ 
        V = cargarDatosTeclado();
    }else if (fuenteDatos == 'F'){ 
        int eleccion = leer.entero("Selecciona el fichero de prueba: 1 o 2");
        
        while(eleccion!=1 && eleccion!=2){
            System.out.println("Elección erronea.");
            eleccion = leer.entero("Selecciona el fichero de prueba: 1 o 2");
        }
        
        switch(eleccion){
            case 1 :
                direccion = "C:\\Users\\jmsl\\Desktop\\Ingenieria Informatica\\Workspace\\Metodologia4º\\Prueba1_1.dat";
                break;
            case 2 :
                direccion = "C:\\Users\\jmsl\\Desktop\\Ingenieria Informatica\\Workspace\\Metodologia4º\\Prueba1_2.dat";
                break;
        }
        
        V = cargarDatosArchivo(direccion);
    }else if (fuenteDatos == 'A'){
        V = cargarDatosAleatorio();
    }
    return V;
  }
  
  public static int[] cargarDatosTeclado() throws Exception{
      System.out.println("Introduce el tamaño del vector");
      
      int tamanioVector = leer.entero();
      int t [] = new int[tamanioVector];
      for (int i = 0; i < tamanioVector; i++) {
          System.out.println("Introduce el dato");
          
          t[i] = leer.entero();
      }
      return t;
  }
  
  public static int[] cargarDatosArchivo(String nombre) throws FileNotFoundException{
    File fichero = new File(nombre);
    Scanner s;
    
    System.out.println("El fichero va a ser leído");
    s = new Scanner(fichero);
    
    int lon = s.nextInt();
    int [] aux = new int[lon];
    
    for (int i = 0; i < lon; i++) {
        s.nextLine();
        aux[i] = s.nextInt();
        //System.out.println("Fichero leído");
    }
      
    return aux;
	
  }

  public static int[] cargarDatosAleatorio(){
      return cargarDatosAleatorio(leer.entero("Introduce tama�o del vector"));
  }
  
    
  public static int[] cargarDatosAleatorio(int size){
      int [] aux = new int[size];
      Random aleatorio = new Random();
      
      for (int i = 0; i < size; i++) {
          aux[i] = aleatorio.nextInt(100);
      }
      return aux;
  }
	
  public static void burbuja(int[] t){
    int aux;
    for (int i = 1; i <t.length; i++){
     for (int j = t.length-1; j>=i; j--){
       if (t[j-1] > t[j]) {
          aux=t[j-1];
          t[j-1] = t[j];
          t[j] = aux;
       }
     }
    }
  }
	
  public static void seleccionDirecta(int[] t){
    int min, pos, aux;
    for (int i=0; i<t.length-1; i++){
        min = t[i];
        pos = i;
        for (int j=i+1; j<t.length; j++){
            if (t[j] < min){
                min = t[j];
                pos = j;
            }
        }
        if(pos != i){
            aux=t[i];
            t[i]=t[pos];
            t[pos]=aux;
        }
    }
  }

  public static void apartado2(){
	  
    System.out.println("\n\nAhora realizaremos pruebas con distintos tama�os del vector");
    int[] valoresN= {100, 500, 1000, 5000, 8000, 9000, 10000, 11000, 20000, 50000};
    long resultadoBurbuja = 0;
    long resultadoSelDirec = 0;
          
    char medida=Character.toUpperCase(leer.caracter("�En qu� unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos "));
    while(medida!='M' && medida!='N'){
        System.out.println("Elección erronea.");
        medida = Character.toUpperCase(leer.caracter("�En qu� unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos "));
    }
    
    System.out.println("  N   |	Burbuja |  SeleccionDirecta  |");

    for (int i = 0; i < valoresN.length; i++) {
        int[] A = cargarDatosAleatorio(valoresN[i]);

        int[] B=new int[A.length];
        System.arraycopy(A,0,B,0,A.length);
        long tb0 = obtenerTiempo(medida);
        burbuja(B);
        long tb2 = obtenerTiempo(medida); 
        int[] C=new int[A.length];
        System.arraycopy(A,0,C,0,A.length);
        long ts0 = obtenerTiempo(medida);
        seleccionDirecta(C);
        long ts2 = obtenerTiempo(medida);
        
        resultadoBurbuja = tb2 - tb0;
        resultadoSelDirec = ts2 - ts0;
        
        System.out.println("  " + A.length + "    |  " + resultadoBurbuja + "      |  " + resultadoSelDirec + "   |  ");
    }

  	
    System.out.printf("\n\n");
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
