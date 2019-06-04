/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import modeloqytetet.Sorpresa;
import java.util.ArrayList;

/**
 *
 * @author jonio
 */
public class Tablero {
    private static ArrayList<Casilla> casillas;
    private Casilla carcel;

    public Tablero() {
        this.inicializar();
    }

    public static ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public Casilla getCarcel() {
        return carcel;
    }
    
    private void inicializar(){
        casillas= new ArrayList();
       
        //aniadir todas las cartas
                    
        casillas.add(new OtraCasilla(0,TipoCasilla.SALIDA, 1000));
        
        
        casillas.add(new Calle(1,new TituloPropiedad("calle Cereza",500,50,10,150,250)));
        casillas.add(new Calle(2,new TituloPropiedad("calle Fresa",500,50,10,150,250)));
        
         //3 tipo sorpresa public Casilla(int numeroCasilla, int coste, TipoCasilla tipoCasilla)
        casillas.add(new OtraCasilla(3,TipoCasilla.SORPRESA, 0));
        
        casillas.add(new Calle(4,new TituloPropiedad("calle Pera",550,50,10,150,250)));
        casillas.add(new Calle(5,new TituloPropiedad("calle Platano",550,50,10,150,250)));
        casillas.add(new Calle(6,new TituloPropiedad("calle Naranja",550,50,10,150,250)));
        
          // 1 tipo carcel
        carcel = new OtraCasilla(7,TipoCasilla.CARCEL, 0);
        casillas.add(carcel);
        
        casillas.add(new Calle(8,new TituloPropiedad("calle Manzana",600,50,10,150,250)));
        casillas.add(new Calle(9,new TituloPropiedad("calle Mango",600,50,10,150,250)));
        
        //1 tipo parking
        casillas.add(new OtraCasilla(10,TipoCasilla.PARKING,0));
        
        //3 tipo sorpresa public Casilla(int numeroCasilla, int coste, TipoCasilla tipoCasilla)
        casillas.add(new OtraCasilla(11,TipoCasilla.SORPRESA, 0));
        
        casillas.add(new Calle(12,new TituloPropiedad("calle Coco",700,50,10,150,250)));
        casillas.add(new Calle(13,new TituloPropiedad("calle Melocoton",700,50,10,150,250)));
        
        //1 tipo juez
        casillas.add(new OtraCasilla(14,TipoCasilla.JUEZ,0));
        
        casillas.add(new Calle(15,new TituloPropiedad("calle Pinia",700,50,10,150,250)));
        casillas.add(new Calle(16,new TituloPropiedad("calle Melon",850,50,10,150,250)));
        
        //3 tipo sorpresa public Casilla(int numeroCasilla, int coste, TipoCasilla tipoCasilla)       
        casillas.add(new OtraCasilla(17,TipoCasilla.SORPRESA, 0));  
        
        //1 tipo impuesto
        casillas.add(new OtraCasilla(18,TipoCasilla.IMPUESTO,-1000));
        
        casillas.add(new Calle(19,new TituloPropiedad("calle Sandia",1000,50,10,150,250)));   
        
        
        /*orden de tablero
            casilla(0-salida
            casilla(1-calle
            casilla(2-calle
            casilla(3-sorpresa
            casilla(4-calle
            casilla(5-calle
            casilla(6-calle
            casilla(7-carcel
            casilla(8-calle
            casilla(9-calle
            casilla(10-parking
            casilla(11-sorpresa
            casilla(12-calle
            casilla(13-calle
            casilla(14-juez
            casilla(15-calle
            casilla(16-calle
            casilla(17-sorpresa
            casilla(18-impuesto
            casilla(19-calle  
        */
    }
    public String toString(){
        return "El Tablero de juego es:  " + casillas.toString();
        
    }
    
    //p2, p3
    public boolean esCasillaCarcel(int numeroCasilla){
        
        boolean escarcel = false;
        if( numeroCasilla == carcel.getNumeroCasilla()){
            escarcel = true;
        }
    
        return escarcel;
    }
    
    //p3
    public Casilla obtenerCasillaNumero(int numeroCasilla){
        Casilla retcas = null ;
        int numcasilla;
        //recorrer todo el array de casillas hasta encontrar el numeroCasilla
        for (int i = 0; i < casillas.size(); i++){           
           if(casillas.get(i).getNumeroCasilla() == numeroCasilla){
            if(casillas.get(i).getTitulo() != null){
               retcas = new Calle(casillas.get(i).getNumeroCasilla(),casillas.get(i).getTitulo());
            } else
               retcas = new OtraCasilla(casillas.get(i).getNumeroCasilla(),casillas.get(i).getTipo(),casillas.get(i).getCoste());
           }
        }        
        
        return retcas;
    }
    //p3
    public Casilla obtenerCasillaFinal(Casilla casilla,int desplazamiento){ //Casilla casilla es casillaactual
       
       int obtener = casilla.getNumeroCasilla() + desplazamiento;
       
       //if(obtener > 19)
           obtener = obtener % casillas.size();
       
       return casillas.get(obtener);
    }
}
