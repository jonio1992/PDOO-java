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
public class TituloPropiedad {

    private int alquilerBase;//indicar el precio base (sin tener en cuenta las edificaciones) que debe pagar quien caiga en la casilla
    private float factorRevalorizacion;//indicar cuánto se revaloriza el título de propiedad en el periodo transcurrido entre su compra y su venta
    private int hipotecaBase;// Indicar cuál es el valor base de su hipoteca
    private Boolean hipotecada;//Indicar si el título de propiedad está hipotecado o no,
    private String nombre;//indicar el nombre de la calle
    private int numCasas;//indicar el número de hoteles y casas edificados en ese título
    private int numHoteles;//indicar el número de hoteles y casas edificados en ese título,
    private int precioCompra;//indicar su precio de compra
    private int precioEdificar;//indicar cuánto cuesta edificar casas y hoteles
    
    //var ref
    //Casilla casilla;
    Jugador propietario;
    
    //constructor
    public TituloPropiedad(String nombre, int precioCompra, int alquilerBase,
                            float factorRevalorizacion, int hipotecaBase,
                             int precioEdificar ){
        this.nombre=nombre;
        hipotecada=false;
        this.precioCompra=precioCompra;
        this.alquilerBase=alquilerBase;
        this.factorRevalorizacion=factorRevalorizacion;
        this.hipotecaBase=hipotecaBase;
        this.precioEdificar=precioEdificar;
        numHoteles=0;
        numCasas=0;
        //casilla = null;//poner a salida?
        propietario = null;
                        
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public int getAlquilerBase() {
        return alquilerBase;
    }

    public float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    public int getHipotecaBase() {
        return hipotecaBase;
    }

    public int getPrecioEdificar() {
        return precioEdificar;
    }
    
    public boolean getHipotecada(){
        return hipotecada;
    } 
    
    public int getNumCasas(){
        return numCasas;
    } 

    public int getNumHoteles(){
        return numHoteles;
    } 
    
    public String toString() {
        return "TituloPropiedad {\n" + "nombre=" + nombre +
                ",\n hipotecada=" + hipotecada + 
                ",\n precio=" + precioCompra + 
                ",\n alquilerBase=" + alquilerBase + 
                ",\n factorRevalorizacion=" + factorRevalorizacion + 
                ",\n hipotecaBase=" + hipotecaBase +
                ",\n precioEdificar=" + precioEdificar +
                ",\n NumeroDeCasas=" + numCasas +
                ",\n NumeroDeHoteles=" + numHoteles +
                //",\n Propietario=" + propietario.toString() +
                "\n}";
    } 
    
    
//p2
int calcularCosteCancelar(){
    //pract3
    int costeCancelar = calcularCosteHipotecar();
    costeCancelar = (int) (costeCancelar + (costeCancelar * 0.1));//10%
    return costeCancelar;
} 

int calcularCosteHipotecar(){
    //pract3
    return (int)(hipotecaBase + numCasas * 0.5 * hipotecaBase + numHoteles * hipotecaBase);
} 

int calcularImporteAlquiler(){
    //pract3
    int calculo = 0;
    
    calculo = alquilerBase +(int)(this.numCasas*0.5 + this.numHoteles*2);
    
    return calculo;
} 

int calcularPrecioVenta(){
    //pract3
    int precioVenta;
    precioVenta =(int) (precioCompra + (numCasas + numHoteles) * precioEdificar * factorRevalorizacion);
    return precioVenta;
} 

boolean cancelarHipoteca(){
    hipotecada = false;
    return true;
} 
/*
void cobrarAlquiler(int coste){
    throw new UnsupportedOperationException("Sin implementar");
} 
*/

void edificarCasa(){
    //pract3
    numCasas++;
} 

void edificarHotel(){
    //pract3
    numCasas -= 4;
    numHoteles++;
} 

Jugador getPropietario(){//implementar p2
    return propietario;
} 

int hipotecar(){
    //pract3
    setHipotecada(true);
    int costeHipoteca = calcularCosteHipotecar();
    return costeHipoteca;
} 

int pagarAlquiler(){
    //pract3
    int costeAlquiler = calcularImporteAlquiler();
    return costeAlquiler;
} 

boolean propietarioEncarcelado(){
    //prac3
    boolean proencar = false;
    
    if(propietario.getEncarcelado())
        proencar = true;
    
    return proencar;
} 

void setHipotecada(boolean hipotecada){
    //pract3
    this.hipotecada = hipotecada;
} 

void setPropietario(Jugador propietario){//implementar p2
    this.propietario = propietario;
} 

boolean tengoPropietario(){
    boolean tengopro = false;
    
    if(propietario != null){
        tengopro = true;
    }
    
    return tengopro;
} 

}
