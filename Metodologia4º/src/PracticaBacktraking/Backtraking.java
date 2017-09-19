package PracticaBacktraking;

import java.util.Scanner;

public class Backtraking {
	
	public static void backtracking(int sol[][], int solOpt[][], int nbaldosas, int nbaldosasopt[], int[] tipo) {
        int casilla[] = new int[2];
        if (esSolucion(sol, tipo)) {
            if (esMejor(sol, solOpt, nbaldosas, nbaldosasopt[0])) {
                actualiza(sol, solOpt);
                nbaldosasopt[0] = nbaldosas;
            }
        } else {
            for (int i = 0; i < tipo.length; i++) {
                if (comprobarSicabe(sol, tipo[i], casilla)) {
                    colocarBaldosa(sol, tipo[i], casilla[0], casilla[1]);// coloca la baldosa a partir de esa celda
                    nbaldosas++;// Se incrementa el numero de baldosas para tener el control
                    backtracking(sol, solOpt, nbaldosas, nbaldosasopt, tipo);
                    poneAcero(sol, tipo[i], casilla[0], casilla[1]);//Para vover atras en el arbol
                    nbaldosas--;
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
	 public static boolean cabe(int[][] s, int tipo, int x, int y) {
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
	 public static boolean esSolucion(int sol[][], int[] tipo) {
	        boolean completa = true;
	        for (int i = 0; i < sol.length && completa; i++) {
	            for (int j = 0; j < sol[i].length && completa; j++) {
	                if (sol[i][j] == 0) {
	                    for (int k = 0; k < tipo.length && completa; k++) {
	                        if (cabe(sol, tipo[k], i, j)) {
	                            completa = false;
	                        }
	                    }
	                }
	            }
	        }
	        return completa;
	    }
	 public static boolean comprobarSicabe(int sol[][], int tipo, int posicion[]) {
	        boolean found = false;
	        for (int i = 0; i < sol.length && !found; i++) {
	            for (int j = 0; j < sol[i].length && !found; j++) {
	                if (sol[i][j] == 0) {
	                    if (cabe(sol, tipo, i, j)) {
	                        found = true;
	                        posicion[0] = i;
	                        posicion[1] = j;
	                    }
	                }
	            }
	        }
	        return found;
	    }
	 public static void colocarBaldosa(int[][] s,int tipo,int x, int y){
		 for(int i =x;i<x+tipo; i++){
			 for(int j =y;j<y+tipo; j++){
				 s[i][j]=tipo;
				 
			 }
		 }
	 }
	 
	 public static void poneAcero(int sol[][], int dimensiontile, int x, int y) {
	        for (int i = x; i < x + dimensiontile; i++) {
	            for (int j = y; j < y + dimensiontile; j++) {
	                sol[i][j] = 0;
	            }
	        }
	    }
	 public static boolean esMejor(int sol[][], int solOpt[][], int nbaldosas, int nBaldosasOpt) {
	        boolean result = false;
	        if (vacio(solOpt) > vacio(sol)) {
	            result = true;
	        } else {
	            if ((vacio(solOpt) == vacio(sol)) && nbaldosas < nBaldosasOpt) {
	                result = true;
	            }
	        }
	        return result;
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
	 public static void actualiza(int[][] solucion, int[][] nueva) {
	        for (int i = 0; i < solucion.length; i++) {
	            for (int j = 0; j < solucion[i].length; j++) {
	                nueva[i][j] = solucion[i][j];
	            }
	        }
	    }
	public static void main(String [] args){
		
		int [][]solar;
		Scanner leer=new Scanner(System.in);
		System.out.println("Escribe el tama�o del solar en metros cuadrados");
		int tamano = leer.nextInt();
		solar = new int[tamano][tamano];
		rellenarSolar(solar);
		imprimirSolar(solar);
		System.out.println("Escribe cuantos tipos de baldosas quiere");
		int extension=leer.nextInt();
		int baldosas[]=new int [extension];
		for (int i=0; i<baldosas.length;i++){
			System.out.println("Escribe el tamano de la baldosa");
			baldosas[i]=leer.nextInt();
		}
		int nbaldosas=0;
		int[][] solOpt = new int[solar.length][solar.length];
		int baldosasUsadas[]=new int [1];
		baldosasUsadas[0] = (solar.length * solar.length) + 1; //para que la primera sol sea mejor con este numero de baldosas
		backtracking(solar, solOpt, nbaldosas, baldosasUsadas, baldosas);
        imprimirSolar(solOpt);
        System.out.println("Con " + baldosasUsadas[0] + " baldosas y " + vacio(solOpt) + " huecos vacios");

	}
}
