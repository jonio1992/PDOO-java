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
public class Especulador extends Jugador{
    private int fianza;
    
    Especulador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza = fianza;
    }
    @Override
    protected void pagarImpuesto(){
        super.modificarSaldo(-super.getCasillaActual().getCoste()/2);
    }
    
    @Override
    protected Especulador convertirme(int fianza){
        return this;    
    }
    
    @Override
    protected boolean deboIrACarcel(){
      //  boolean aux = false;
       // if(super.deboIrACarcel() == true && pagarFianza() == false)
       // aux = true; 
        
        return super.deboIrACarcel() && !this.pagarFianza();//aux;
    
    }
    
    private boolean pagarFianza(){
        boolean aux = this.tengoSaldo(fianza);
        if(aux)
            this.modificarSaldo(-fianza);
        
        return aux;
    }
    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        int numCasas = titulo.getNumCasas();
        int costedificar = titulo.getPrecioEdificar();
        boolean puedo = false;
        if(numCasas < 8 && super.tengoSaldo(costedificar))
            puedo = true;
        
        return puedo;
    
    }
    
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        int numCasas = titulo.getNumCasas();
        int numHoteles = titulo.getNumHoteles();
        int costedificar = titulo.getPrecioEdificar();
        boolean puedo = false;
        
        if(numCasas >= 4 && numHoteles < 8 && super.tengoSaldo(costedificar))
            puedo = true;
        
        return puedo;
    
    }
    
    @Override
    public String toString(){
        return  super.toString() 
                + "\nFianza: " + fianza + "\n";
    }
    
}
