package Pracica3;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class LadrillosDD {
    
   
    private static int [] Divide(int pisos, int li, int ls, int dureza,int ladrillos ){
        boolean fin=false;
        int resultado[]=new int[2];
        while(fin==false){
        	if(ls==dureza || li== dureza){
        		resultado[0]=dureza;
        		resultado[1]=ladrillos;
        		fin=true;
        	}else if (ladrillos==1){
            //pruebas secuenciales desde el piso 1 hasta n o el ladrillo se rompe.
           
            for (int i = 0; i <= pisos; i++) {
                if (i==dureza) {
                    resultado[0]=i;
                    resultado[1]=0;
                    fin=true;
                }
            }
        }else{
            //a partir de k => 2 , piso n/2 si rompe secuencial abajo n/2-1(en este caso si no
            //rompe forma secuencial hacia arriba n/2+1 hasta n o romper)
            if((ls+li)/2 >= dureza){
            	ladrillos--;
                ls=(((ls+li)/2)-1);
                Divide(pisos, li, ls, dureza, ladrillos);
                //}
            }else{ 
                li=((ls+li)/2)+1;
                //System.out.println(li);
                //System.out.println(ls);
                Divide(pisos, li, ls, dureza, ladrillos);
            }
         
          }
       } 
        return resultado;
    }
  
    public static long obtenerTiempo(){
        long resultado = 0;
                resultado = System.nanoTime();  
        return resultado;
    
    }
    public static void main(String[] args) {
        int ladrillos [] = {1, 2, 10, 100, 1000};
        int pisos [] ={10, 100, 1000, 10000, 100000, 1000000};
        
        for(int i=0; i<pisos.length;i++){
        	int dureza = (int)(Math.random() * pisos[i]);
        	for(int j=0; j<ladrillos.length;j++){
        		System.out.println("dureza :"+dureza);
        		long tb0 = obtenerTiempo();
        		int resultado[]= Divide(pisos[i], 1, pisos[i], dureza , ladrillos[j]);
        		long tb1 = obtenerTiempo();
        		long tiempoEjecucion=tb1-tb0;
        		System.out.println("Para "+pisos[i]+" pisos: piso "+resultado[0]+" ladrillos restantes " +resultado[1]);
        		System.out.println("Tiempo: "+tiempoEjecucion);
        	}
        }
        
    }
    
}