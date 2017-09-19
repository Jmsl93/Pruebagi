package PracticaVoraz;

import java.util.Scanner;

public class Voraz {
	
	public static int[] algoritmo(int[][] solar,int[] baldosas){
		int baldosasUsadas[]=new int [baldosas.length];
		for(int i =0;i<baldosas.length; i++){
			for(int j =0;j<solar.length; j++){
				for(int k =0;k<solar.length; k++){
					if(solar[j][k]==0){
						if(comprobarSiCabe(solar,baldosas[i],j,k)){
							colocarBaldosa(solar,baldosas[i],j,k);
							baldosasUsadas[i]++;
						}
					}
				}
			}
		}

		return baldosasUsadas;
	}
	 public static void burbuja(int[] t){
		    int aux;
		    for (int i = 1; i <t.length; i++){
		     for (int j = t.length-1; j>=i; j--){
		       if (t[j-1] < t[j]) {
		          aux=t[j-1];
		          t[j-1] = t[j];
		          t[j] = aux;
		       }
		     }
		    }
		  }
	 
	 public static void rellenarSolar(int [][] solar){
		 for(int i =0;i<solar.length; i++){
			 for(int j =0;j<solar.length; j++){
				 solar[i][j]=0;
			 }
		 }
	 }
	 public static void imprimirSolar(int [][] solar){
		 for(int i =0;i<solar.length; i++){
			 for(int j =0;j<solar.length; j++){
				 System.out.print(solar[i][j]+ " ");
			 }
			 System.out.println();
		 }
	 }
	 public static boolean comprobarSiCabe(int[][] s, int tipo, int x, int y) {
	        int fila = 0, columna = 0;
	        for (int i = x; i < s.length; i++) {
	            if (s[i][y] == 0) {
	                fila++;
	            }
	        }
	        for (int j = y; j < s[x].length; j++) {
	            if (s[x][j] == 0) {
	                columna++;
	            }
	        }
	        return (fila >= tipo && columna >= tipo);
	    }
	 public static void colocarBaldosa(int[][] s,int tipo,int x, int y){
		 for(int i =x;i<x+tipo; i++){
			 for(int j =y;j<y+tipo; j++){
				 s[i][j]=tipo;
				 
			 }
		 }
	 }
	 public static int vacio(int[][] sol) {
	        int libres = 0;
	        for (int i = 0; i < sol.length; i++) {
	            for (int j = 0; j < sol[i].length; j++) {
	                if (sol[i][j] == 0) {
	                    libres++;
	                }
	            }
	        }
	        return libres;
	    }
	public static void main(String [] args){
		int [][]solar;
		Scanner leer=new Scanner(System.in);
		System.out.println("Escribe el tamaño del solar en metros cuadrados");
		int tamaño = leer.nextInt();
		solar = new int[tamaño][tamaño];
		rellenarSolar(solar);
		imprimirSolar(solar);
		System.out.println("Escribe cuantos tipos de baldosas quiere");
		int extension=leer.nextInt();
		int baldosas[]=new int [extension];
		for (int i=0; i<baldosas.length;i++){
			System.out.println("Escribe el tamaño de la baldosa");
			baldosas[i]=leer.nextInt();
		}
		burbuja(baldosas);
		int[] baldosasUsadas=algoritmo(solar,baldosas);
		imprimirSolar(solar);
		for (int i=0;i<baldosas.length; i++){
			System.out.println("Se han usado "+ baldosasUsadas[i]+ " baldosas de " + baldosas[i] + " metros");
		}
		System.out.println("Quedan " + vacio(solar) + " huecos de 1 metro libres");
	}

}
