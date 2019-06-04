/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;
import java.lang.Comparable;
/**
 *
 * @author jonio
 */

//practica 3 poner implements Comparable para hacer q un obj jugador se pueda comparar con otro.
public class Jugador implements Comparable{
    private boolean encarcelado/* = false*/;
    private String nombre;
    private int saldo;// = 7.500;
    
    //var ref
    private Casilla casillaActual;
    private Sorpresa cartaLibertad;
    private ArrayList<TituloPropiedad> propiedades;
    
public Jugador(String nombre){
    this.nombre = nombre;
    encarcelado=false;
    saldo = 7500;
    casillaActual = null;
    propiedades = new ArrayList();
    cartaLibertad=null;
}
        
boolean cancelarHipoteca(TituloPropiedad titulo){
    //pract3
    boolean cancelar = false;
    int costeCancelar = titulo.calcularCosteCancelar();
    boolean tengoSaldo = tengoSaldo(costeCancelar);
    boolean esDeMiPropiedad = esDeMiPropiedad(titulo);

    if(tengoSaldo && esDeMiPropiedad){
        
        cancelar = titulo.cancelarHipoteca(); //true;
        cancelar = true;
    }

    return cancelar;
} 

boolean comprarTituloPropiedad(){
    //pract3
    boolean comprado = false;
    int costeCompra = casillaActual.getCoste();
    int cantidad = costeCompra;
    if(costeCompra < saldo){
        TituloPropiedad titulo =  casillaActual.getTitulo();
        if(titulo.getPropietario() == null){//evitar comprar otro jugador si esta comprado      
            titulo.setPropietario(this);
            comprado = true;
            propiedades.add(titulo);
            modificarSaldo(-cantidad);
            
        }else
            comprado = false;        
    }   
    return comprado;
}

int cuantasCasasHotelesTengo(){
    //practica 3
    int numCasHot = 0;
    
        for (TituloPropiedad propiedadI : propiedades) {
            numCasHot += propiedadI.getNumCasas() + propiedadI.getNumHoteles();
        }
    
    return numCasHot;
}

boolean deboPagarAlquiler(){
    //pract3
    TituloPropiedad titulo = casillaActual.getTitulo();
    boolean esDeMiPropiedad = esDeMiPropiedad(titulo);
    boolean tienePropietario = false;
    boolean encarcelado = true;
    boolean estaHipotecada = true;
    boolean deboPagar = false;
    
    if(!esDeMiPropiedad){
        tienePropietario = titulo.tengoPropietario();
        if(tienePropietario){
            encarcelado = titulo.propietarioEncarcelado();
            if(!encarcelado){
                estaHipotecada = titulo.getHipotecada();
            }
        }
    }
    deboPagar = !esDeMiPropiedad & tienePropietario & !encarcelado & !estaHipotecada;
    return deboPagar;
}

Sorpresa devolverCartaLibertad(){   
    Sorpresa aux;
    aux = cartaLibertad;
    cartaLibertad = null;    
    return aux;
}

protected boolean edificarCasa(TituloPropiedad titulo){
    //pract3, prac4
  
        boolean edificado = false;
        
        if(puedoEdificarCasa(titulo)){
            int costeEdificarCasa = titulo.getPrecioEdificar();
            boolean tengoSaldo = this.tengoSaldo(costeEdificarCasa);
            
            if(tengoSaldo){
                titulo.edificarCasa();
                this.modificarSaldo(-costeEdificarCasa);
                edificado = true;
            }
        }
        
        return edificado;
} 

protected boolean edificarHotel(TituloPropiedad titulo){
    //pract3
    boolean edificado = false;
    int numCasas = titulo.getNumCasas();
    int numHoteles = titulo.getNumHoteles();
    if(puedoEdificarHotel(titulo)){
        int costeEdificarHotel = titulo.getPrecioEdificar();
        boolean tengoSaldo = this.tengoSaldo(costeEdificarHotel);

        if(tengoSaldo){
            titulo.edificarHotel();
            this.modificarSaldo(-costeEdificarHotel);
            edificado = true;
        }
    }

    return edificado;
} 

private void eliminarDeMisPropiedades(TituloPropiedad titulo){
    //pract3
    propiedades.remove(titulo);
    titulo.setPropietario(null);
} 

private boolean esDeMiPropiedad(TituloPropiedad titulo){
    //prac3
    boolean mipropiedad = false;
    for(TituloPropiedad tituloI : propiedades){
        if(titulo == tituloI){
            mipropiedad = true;
        }
    }
    
    return mipropiedad;
} 

boolean estoyEnCalleLibre(){
    //pract3
    if(casillaActual.soyEdificable() && !casillaActual.getTitulo().tengoPropietario())
        return true;
    else
        return false;
}

Sorpresa getCartaLibertad(){//implementar p2
    return cartaLibertad;
}

Casilla getCasillaActual(){//implementar p2
    return casillaActual;
}

boolean getEncarcelado(){//implementar p2
    return encarcelado;
}

String getNombre(){//implementar p2
    return nombre;
}

ArrayList<TituloPropiedad> getPropiedades(){//implementar p2
    return propiedades;
}

int getSaldo(){//implementar p2
    return saldo;
}

void hipotecarPropiedad(TituloPropiedad titulo){
    //pract3
    int costeHipoteca = titulo.hipotecar();
    modificarSaldo(costeHipoteca);
    
}

void irACarcel(Casilla casilla){
    //pract3
    setCasillaActual(casilla);
    this.setEncarcelado(true);
}

void modificarSaldo(int cantidad){
    //prac3
      
    this.saldo = this.saldo + cantidad;
    
    
}

int obtenerCapital(){
    //practica 3
    int capital = 0;
    //saldo + valor propiedad--> array propiedades.precio +num casas +numotelees * precioedificacion, if hipotecada,restar
    for (TituloPropiedad propiedadI : propiedades) {       
        
        if(propiedadI.getHipotecada()){
            capital = saldo - propiedadI.getHipotecaBase();
        }else{
            capital = saldo + 
                      propiedadI.getPrecioCompra() + 
                      cuantasCasasHotelesTengo()  * propiedadI.getPrecioEdificar();
        }   
    }
    
    return capital;
}

ArrayList<TituloPropiedad> obtenerPropiedades(boolean estadoHipoteca){
    //pract3
    ArrayList<TituloPropiedad> mispropiedades = null;
    
    for(TituloPropiedad propiedadI : propiedades){
    
        if(estadoHipoteca == propiedadI.getHipotecada()){
            mispropiedades.add(propiedadI);
        }   
    }
    
    return mispropiedades;   
}

void pagarAlquiler(){
    //pract3
    int costeAlquiler = casillaActual.getCoste();
    modificarSaldo(-costeAlquiler);
}
protected void pagarImpuesto(){
    //practica3
    //saldo = saldo - casillaActual.getCoste();
    int costeAlq = casillaActual.getCoste();
    modificarSaldo(-costeAlq);
}

void pagarLibertad(int cantidad){
    boolean tengoSaldo = tengoSaldo(cantidad);
        if(tengoSaldo){
            setEncarcelado(false);
            modificarSaldo(-cantidad);
        }
}

void setCartaLibertad(Sorpresa carta){//implementar p2
    if(carta.getTipo() == TipoSorpresa.SALIRCARCEL)
    this.cartaLibertad = carta;
}

void setCasillaActual(Casilla casilla){//implementar p2
    this.casillaActual = casilla;
} 

void setEncarcelado(boolean encarcelado){//implementar p2
    this.encarcelado = encarcelado;
}

boolean tengoCartaLibertad(){
    //prac3    
    boolean cartalibertad = false;
    
    if(cartaLibertad != null)
        cartalibertad = true;
    
    return cartalibertad;
}

protected boolean tengoSaldo(int cantidad){
    //prac3
    boolean sitengosaldo = false;
    
        if(saldo > cantidad){
            sitengosaldo = true;
        }
    
    return sitengosaldo;
}

void venderPropiedad(Casilla casilla){
    TituloPropiedad titulo = casilla.getTitulo();
    eliminarDeMisPropiedades(titulo);
    int precioVenta = titulo.calcularPrecioVenta();
    modificarSaldo(precioVenta);    
} 

//practica 3
@Override
public int compareTo(Object otroJugador) {
    
    int otroCapital = ((Jugador)otroJugador).obtenerCapital();
    return otroCapital - obtenerCapital();

}

protected Especulador convertirme(int fianza) {//p4
    Especulador especulador = new Especulador(this, fianza);
    // this.setFactorEspeculador(especulador.getFactorEspeculador());
    return especulador;
}

protected boolean deboIrACarcel() {//p4
    boolean aux = tengoCartaLibertad();
    aux = !aux;
    return aux;
}

protected Jugador(Jugador otroJugador){//p4
    this.nombre = otroJugador.nombre;
    this.encarcelado = otroJugador.encarcelado;
    this.saldo = otroJugador.saldo;
    this.casillaActual = otroJugador.casillaActual;
    this.propiedades = otroJugador.propiedades;
    this.casillaActual = otroJugador.casillaActual;
    this.cartaLibertad = otroJugador.cartaLibertad;
    
}

protected boolean puedoEdificarCasa(TituloPropiedad titulo) {//p4
    int numCasas = titulo.getNumCasas();
    return numCasas < 4;
}

protected boolean puedoEdificarHotel(TituloPropiedad titulo) {//p4
    int numCasas = titulo.getNumCasas(), numHoteles = titulo.getNumHoteles();
    return numCasas == 4 && numHoteles < 4;
}


public String toString(){
    
        int capital = this.obtenerCapital();
        return "Nombre: " + nombre + "\n" +
               "Está encarcelado? " + encarcelado + "\n" +
               "Saldo: " + saldo + "\n" +
               "Tiene carta libertad: " + cartaLibertad + "\n" +
               "Propiedades: " + propiedades.toString() + "\n" +
               "Casilla Actual: " + casillaActual.getNumeroCasilla() + "\n" +
               "Capital: " + capital ;
    }       
        
}
