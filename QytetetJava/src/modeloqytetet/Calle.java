/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author jonio
 */
public class Calle extends Casilla{
    
    private TituloPropiedad titulo;
    
    public Calle(int numeroCasilla, TituloPropiedad titulo) {
        super(numeroCasilla, titulo.getPrecioCompra());
        this.titulo = titulo;
    }
    
     public void asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
    }
    
    @Override
    protected TipoCasilla getTipo(){
        return TipoCasilla.CALLE;
    }

    @Override
    protected TituloPropiedad getTitulo(){
        return titulo;
    }    
    
    public int pagarAlquiler(){
        int costeAlquiler = titulo.pagarAlquiler();
        return costeAlquiler;
    }
    
    private void setTitulo(TituloPropiedad titulo){
        this.titulo = titulo;
    }

    @Override
    protected boolean soyEdificable(){
        //Devuelve cierto sólo si es una casilla de tipo CALLE.
        return true;
    }
    
    @Override
    public boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    
    @Override
    public String toString(){
        return super.toString() + "\n - Tipo: CALLE\n - Titulo:\n" + titulo;
    }
    
}
