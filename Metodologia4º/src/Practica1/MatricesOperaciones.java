package Practica1;


/**
 *
 * @author Juan.Giralt
 */
public class MatricesOperaciones{
	

    public static String mostrar(int [] A){
        String s="";
        for(int n=0;n<A.length;n++) s=s+A[n]+" ";
        return s;
    }

    ///dasdfas

    public static String mostrar(int[][] A){
        String s="";
        for(int fil=0;fil<A.length;fil++) s=s+mostrar(A[fil])+"\n";
        return s;
    }
    
  
}//MatricesOperaciones
