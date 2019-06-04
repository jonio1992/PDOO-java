/*throw
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author jonio
 */
public abstract class Casilla {
    
    private int numeroCasilla;//indicar el número de la casilla
    private int coste;//indicar el coste de esa casilla
      
    public Casilla(int numeroCasilla, int coste) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste ;
        
    }
    
    public int getNumeroCasilla(){
        return numeroCasilla;
    }
    
    int getCoste() {
        return coste;
    }
    
    public void setCoste(int coste){
        this.coste = coste;
    }
    
    protected abstract TipoCasilla getTipo();
    
    protected abstract TituloPropiedad getTitulo();
    
    protected abstract boolean soyEdificable();
    
    protected abstract boolean tengoPropietario();
    
    @Override
    public String toString() {
        return "Numero de Casilla: " + numeroCasilla + " - Coste: " + coste;
    }
    
/*
    //constructor 2 no tipo calle
    public Casilla(int numeroCasilla, TipoCasilla tipoCasilla, int coste ) {
        this.coste = coste;
        this.numeroCasilla = numeroCasilla;
        this.tipoCasilla = tipoCasilla;        
    }

    //gets
    public int getNumeroCasilla() {//implementar p2
        return numeroCasilla;
    }

    public int getCoste() {//implementar p2
        return coste;
    }

    protected TipoCasilla getTipo() {//implementar p2
        return tipoCasilla;
    }

    protected TituloPropiedad getTitulo() {//implementar p2
        return titulo;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }    
    
    //usar solo en casilla tipo calle
    private void setTitulo(TituloPropiedad nuevoTitulo){//implementar p2
        titulo=nuevoTitulo;
    }
    
     public String toString() {
         if(titulo != null){
        return "\nCasilla {" + 
               "Numero de la Casilla = " + numeroCasilla + 
               ",\n coste=" +  Integer.toString(coste) + 
               ",\n Tipo de la casilla = " + tipoCasilla + 
               "titulo: "+ titulo.getNombre() + "}\n";
     }else{
            return "\n Casilla {Numero de la Casilla = " + Integer.toString(numeroCasilla) +
                    ",\n coste=" +  Integer.toString(coste) +
                   "\n Tipo de la casilla = " + tipoCasilla + "}\n";
                }
        }
     
    
    int pagarAlquiler(){
        //pract3
        int costeAlquiler = this.titulo.pagarAlquiler();
        return costeAlquiler;
    } 
    
    boolean propietarioEncarcelado() {
       //pract3
       boolean propietarioencar = false;
       if(titulo.propietarioEncarcelado()){
           propietarioencar = true;
       }
       return propietarioencar;
    }
     
    protected boolean soyEdificable() {
       //prac3
       boolean soyedi = false;
       if(tipoCasilla == TipoCasilla.CALLE){
           soyedi = true;
       }

       return soyedi;
    }
     
    boolean tengoPropietario() {
       //pract3
       boolean tengopro = false;
       if(titulo.tengoPropietario()){
           tengopro = true;
       }            
       return tengopro;
    }
    */
             
}
