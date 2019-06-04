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
public class OtraCasilla extends Casilla{
    
    private TipoCasilla tipo;
    
    public OtraCasilla(int numeroCasilla, TipoCasilla tipo, int coste ) {
        super(numeroCasilla, coste);
        this.tipo = tipo;
    }
    
     @Override
    protected TipoCasilla getTipo() {
        return tipo;
    }
    
    @Override
    protected boolean soyEdificable(){
        return false;
    }
    
    @Override
    protected TituloPropiedad getTitulo(){
        return null;
    }    
   
    @Override
    public boolean tengoPropietario(){
        return false;
    }
    
    @Override
    public String toString(){
        return super.toString() + "\n - Tipo: " + tipo;
    }

    
    
}
