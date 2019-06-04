/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.*;
import static modeloqytetet.EstadoJuego.*;

/**
 *
 * @author jonio
 */
public class Qytetet {
    //Sorpresa(String texto, int valor, TipoSorpresa tipo)
   private static final Qytetet instance = new Qytetet();
   public ArrayList<Sorpresa> mazo; 
   private static Tablero tablero;//Tablero tablero.inicializarTablero()???   
   public int MAX_JUGADORES = 4;
   int NUM_SORPRESAS = 12;//10;
   public int NUM_CASILLAS = 20;
   int PRECIO_LIBERTAD = 200;
   int SALDO_SALIDA = 1000;   
   private Sorpresa cartaActual;
   private Jugador jugadorActual;
   private ArrayList<Jugador> jugadores;
   private Dado dado;
   private EstadoJuego estadoJuego;
   
   private Qytetet() {
    // La implementación que corresponda.        
        mazo = new ArrayList();        
        cartaActual = null;
        jugadorActual = null;
        jugadores = new ArrayList();
        dado = dado.getInstance();
        estadoJuego = null;
    }
   public static Qytetet getInstance() {
        return instance;
    }
   /*Cuando necesitemos acceder a la instancia de MiClase:
        MiClase mc= MiClase.getInstance();*/
     

    public ArrayList<Sorpresa> getMazo(){
        ArrayList<Sorpresa> result = new ArrayList<>();
            for(int i = 0; i < mazo.size(); i++){
                //System.out.println(mazo.get(i).toString());
                //result(i)=mazo(i)
            }
            
        return result;
    }
    
    //cambiar a private, dejar en public para pruebas
    private void inicializarCartasSorpresa() {
        /*
            1 SALIRCARCEL
            3 IRACASILLA
            2 PORJUGADOR
            2 PORCASAHOTEL            
            2 PAGARCOBRAR
        */
        //SALIRCARCEL //se queda con la carta y si sirve para salir
        mazo.add(new Sorpresa (
                "Un fan anónimo ha pagado tu fianza. "
                + "Sales de la cárcel",
                2, 
                TipoSorpresa.SALIRCARCEL));
        
        //IRACASILLA  funciona todo
        mazo.add(new Sorpresa (
                "La liga antisuperstición te envía de viaje al número 13",
             13, //13//no manda a la 5 manda a la 0
             TipoSorpresa.IRACASILLA));
        
        
        mazo.add(new Sorpresa (
                "Viaja a la casilla de salida",
             0, //bien
             TipoSorpresa.IRACASILLA));
       
        mazo.add(new Sorpresa (
                "Te han pillado sin pagar tus impuesto de propiedades, vas a la carcel!",
             tablero.getCarcel().getNumeroCasilla(), //no encarcela?, no manda a la 7
             TipoSorpresa.IRACASILLA));
        
        
        
        //PORJUGADOR //va bien
            //positiva
        mazo.add(new Sorpresa (
                "Es tu cumpleanios!, recibes 20 ",
             -20, //mirar valores
             TipoSorpresa.PORJUGADOR));
             //neativa
        mazo.add(new Sorpresa (
                "Tus compañeros te han pillado mangando, devuelves 50 a la banca",
             50,  //mirar estos valores
             TipoSorpresa.PORJUGADOR));
        
        //PORCASAHOTEL //funciona
            //positiva
        mazo.add(new Sorpresa (
                "Ayuda a las reparaciones de tus propiedades",
             -50, //no suma
             TipoSorpresa.PORCASAHOTEL));
             //neativa
        mazo.add(new Sorpresa (
                "Impuesto de propiedades",
             50, //no aplica
             TipoSorpresa.PORCASAHOTEL));
        
        //PAGARCOBRAR
            //positiva
        mazo.add(new Sorpresa (
                "Error de la banca, cobras 50",
             50, //bien
             TipoSorpresa.PAGARCOBRAR));
             //neativa
        mazo.add(new Sorpresa (
                "Gastos minibar, -50", //bien
             -50, 
             TipoSorpresa.PAGARCOBRAR));
        
        //CONVERTIRME
       mazo.add(new Sorpresa (
                "CONVERTIRME 1",
             3000, 
             TipoSorpresa.CONVERTIRME));//sale mensaje de convertirse
             
       mazo.add(new Sorpresa (
                "CONVERTIRME 2",
             5000, 
             TipoSorpresa.CONVERTIRME));
               
       
        Collections.shuffle(mazo);
        
        
    }

    public Tablero getTablero() {
        return tablero;
    }
    
    private void inicializarTablero(){
        tablero = new Tablero();
    }
    
    //p2
    
    void actuarSiEnCasillaEdificable(){
        //pract3
        boolean deboPagar = jugadorActual.deboPagarAlquiler();
        
        if(deboPagar){
            jugadorActual.pagarAlquiler();
            if(jugadorActual.getSaldo() <= 0){
                setEstadoJuego(estadoJuego.ALGUNJUGADORENBANCARROTA);
            }        
        }
        Casilla casilla = obtenerCasillaJugadorActual();
        
        boolean tengoPropietario = casilla.tengoPropietario();
        
        if(estadoJuego != estadoJuego.ALGUNJUGADORENBANCARROTA){
            if(tengoPropietario){
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            }else{
                setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
            }
        }
    }

    void actuarSiEnCasillaNoEdificable(){
        //pract3
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        Casilla casillaActual = jugadorActual.getCasillaActual();
        
        if(casillaActual.getTipo() == TipoCasilla.IMPUESTO){
            jugadorActual.pagarImpuesto();
            if(jugadorActual.getSaldo() <= 0){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
        }
        
        if(casillaActual.getTipo() == TipoCasilla.JUEZ){
            encarcelarJugador();
        }else {
            if(casillaActual.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.remove(0);
                setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
                               
            }
        }
        
        
    }

    public void aplicarSorpresa(){
        //prac3
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        if(cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
            mazo.add(cartaActual); 
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){ 
            jugadorActual.modificarSaldo(cartaActual.getValor());
            if(jugadorActual.getSaldo() <= 0){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
        }
        else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
            /*int valor = dado.getValor();
            Boolean casillaCarcel = tablero.esCasillaCarcel(valor);
            if(casillaCarcel == true ){
                encarcelarJugador();
            }else{
                mover(valor);
            }            */
             int valor = cartaActual.getValor();
                boolean casillaCarcel = tablero.esCasillaCarcel(valor);
                if(casillaCarcel){
                    encarcelarJugador();
                    
                }
                else{
                    mover(valor);
                    setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
                }
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){ 
            int cantidad = cartaActual.getValor();
            int numeroTotal = jugadorActual.cuantasCasasHotelesTengo();
            jugadorActual.modificarSaldo(cantidad*numeroTotal);
            
            if(jugadorActual.getSaldo() <= 0){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
            }
        }
        else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            
            for(Jugador jugador:jugadores){
                if( jugador != jugadorActual){
                    jugador.modificarSaldo(cartaActual.getValor());
                }
                if(jugadorActual.getSaldo() <= 0){
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }                
                
                jugador.modificarSaldo(-cartaActual.getValor());
                
                if(jugadorActual.getSaldo() <= 0){
                    setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                }
                
            }       
        }else if ((cartaActual.getTipo() == TipoSorpresa.CONVERTIRME)){
           Especulador espec = new Especulador(jugadorActual, cartaActual.getValor());
           jugadores.set(jugadores.indexOf(jugadorActual), espec);
           jugadorActual = espec;
        }          
    }

    public boolean cancelarHipoteca(int numeroCasilla){
        
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        boolean esEdificable = casilla.soyEdificable();        
        boolean hipotecada = titulo.getHipotecada();
        boolean cancelar = false;

        if (esEdificable && hipotecada)//condicion: casilla edificable que es propiedad del jugadorActual y que está hipotecada.
            cancelar = jugadorActual.cancelarHipoteca(titulo);
      
        estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
      
        return cancelar;
    }

    public boolean comprarTituloPropiedad(){
        //pract3
        boolean comprartitulo = jugadorActual.comprarTituloPropiedad();
        if(comprartitulo){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        return comprartitulo;
    }

    public boolean edificarCasa(int numeroCasilla){
        //pract3
        boolean edificada = false;
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        edificada = jugadorActual.edificarCasa(titulo);
        if (edificada){
            estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
        }
        return edificada;
    }

    public boolean edificarHotel(int numeroCasilla){
       Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
       TituloPropiedad titulo = casilla.getTitulo();
       boolean edificada = jugadorActual.edificarHotel(titulo);
       
       if(edificada)
           estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
       
       return edificada;
    }

    private void encarcelarJugador(){
        //pract3
        Casilla casillaCarcel = tablero.getCarcel();
        /*
        if(!jugadorActual.tengoCartaLibertad()){
            
            jugadorActual.irACarcel(casillaCarcel);
            estadoJuego = EstadoJuego.JA_ENCARCELADO;
        }
        else{
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
            estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
        }*/
        if(jugadorActual.deboIrACarcel()){
            
            jugadorActual.irACarcel(casillaCarcel);
            estadoJuego = EstadoJuego.JA_ENCARCELADO;
        }
        else{
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
            estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
        }
        
        
    }
    
    public EstadoJuego getEstadoJuego(){
        //practica3
        return estadoJuego;
    }
    

    public Sorpresa getCartaActual(){ //implementar p2
        return cartaActual;
    }

    Dado getDado(){ //implementar p2
        return dado;
    }

    public Jugador getJugadorActual(){ //implementar p2
        return jugadorActual;
    }

    public ArrayList<Jugador> getJugadores() {//[2..MAX_JUGADORES] //implementar p2
        return jugadores;
    }

    public int getValorDado(){
        return dado.getValor();
    }

    public void hipotecarPropiedad(int numeroCasilla){
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad titulo = casilla.getTitulo();
        jugadorActual.hipotecarPropiedad(titulo);
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
    }

    public void inicializarJuego(ArrayList<String> nombres){ //implementar p2
        if(nombres.size() >= 2 && nombres.size() <= MAX_JUGADORES && mazo.size() <= NUM_SORPRESAS){
            inicializarJugadores(nombres);
            inicializarTablero();
            inicializarCartasSorpresa();
            salidaJugadores();
            
            
        }
        else{
            throw new UnsupportedOperationException("O el numero de jugadores es incorrecto (debe ser de 2 a " + MAX_JUGADORES + " jugadores)"
            + "o el numero de cartas es incorrecto (deben ser menor que " + NUM_SORPRESAS + ")");           
        }
    }

    private void inicializarJugadores(ArrayList<String> nombres/*[2..MAX_JUGADORES]*/){ //implementar p2
               
        /*for(String nombre:nombres){
            jugadores.add(new Jugador(nombre));
        }*/Jugador jugador_aux = null;
        for (int i = 0 ; i < nombres.size(); i++){
            jugador_aux = new Jugador(nombres.get(i));
            jugadores.add(jugador_aux);
        }
    }    

    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        //pract3
         if(metodo == MetodoSalirCarcel.TIRANDODADO){
            int resultado = tirarDado();
            if(resultado >= 5)
                jugadorActual.setEncarcelado(false);
        }else if(metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
        }
         
        boolean libre = jugadorActual.getEncarcelado();
        if(!libre)
            estadoJuego = EstadoJuego.JA_PREPARADO;
        else
            estadoJuego = EstadoJuego.JA_ENCARCELADO;
        return libre;
    }

    public void jugar(){
        int numdado = tirarDado();
        Casilla casillaaux = tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), 3);//numdado);
        mover(casillaaux.getNumeroCasilla());
              
    }

    void mover(int numCasillaDestino){
        //pract3
        Casilla casillaInicial = jugadorActual.getCasillaActual();
        Casilla casillaFinal = tablero.obtenerCasillaNumero(numCasillaDestino);
        jugadorActual.setCasillaActual(casillaFinal);
        
        if(numCasillaDestino < casillaInicial.getNumeroCasilla())
            jugadorActual.modificarSaldo(SALDO_SALIDA);
        if(casillaFinal.soyEdificable()){
            actuarSiEnCasillaEdificable();
        }else
            actuarSiEnCasillaNoEdificable();
    }

    public Casilla obtenerCasillaJugadorActual(){
        //pract3
        return jugadorActual.getCasillaActual();
    }

    public ArrayList<Casilla> obtenerCasillasTablero(){//[NUM_CASILLAS]
        //pract3
        return tablero.getCasillas();
    }
/*
    public int[] obtenerPropiedadesJugador(){//[0..*]
        //prac3
        
       int contador = 0;
       ArrayList<TituloPropiedad> jugtitulos = jugadorActual.getPropiedades();
       ArrayList<Casilla> recorrercasillas = tablero.getCasillas();
       int[] titujug = new int[jugtitulos.size()+1];
       for(int i = 0; i < jugtitulos.size(); i++){
           for(int j = 0; j < recorrercasillas.size(); j++){
               if(jugtitulos.get(i) == recorrercasillas.get(j).getTitulo())
                 titujug[contador] = recorrercasillas.get(j).getNumeroCasilla();
                 contador++;
                
           }
       }
        
        return titujug;
            
    }

    public int[] obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){//[0..*]
            //prac3
            
            int contador = 0;
            ArrayList<TituloPropiedad> jugtitulos = jugadorActual.getPropiedades();
            ArrayList<Casilla> recorrercasillas = tablero.getCasillas();
            int[] titujug = new int[jugtitulos.size()+1] ; ;
            for(int i = 0; i < jugtitulos.size(); i++){
                for(int j = 0; j < recorrercasillas.size(); j++){
                     if((jugtitulos.get(i) == recorrercasillas.get(j).getTitulo()) && (jugtitulos.get(i).getHipotecada() == estadoHipoteca)  )
                        titujug[contador] = recorrercasillas.get(j).getNumeroCasilla();
                        contador++;
           }
       }          
            
        return titujug;       
    }
*/
    
     public ArrayList<Integer> obtenerPropiedadesJugador(){//[0..*]
        //prac3
        
       int contador = 0;
       ArrayList<TituloPropiedad> jugtitulos = jugadorActual.getPropiedades();
       ArrayList<Casilla> recorrercasillas = tablero.getCasillas();
       ArrayList<Integer> titujug = new ArrayList() ;
       for(int i = 0; i < jugtitulos.size(); i++){
           for(int j = 0; j < recorrercasillas.size(); j++){
               if(jugtitulos.get(i) == recorrercasillas.get(j).getTitulo())
                 titujug.add(recorrercasillas.get(j).getNumeroCasilla());
                 //contador++;
           }
       }
        
        return titujug;
       
       
    }

    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){//[0..*]
            //prac3
            
            int contador = 0;
            ArrayList<TituloPropiedad> jugtitulos = jugadorActual.getPropiedades();
            ArrayList<Casilla> recorrercasillas = tablero.getCasillas();
            ArrayList<Integer> titujug = new ArrayList()  ;
            for(int i = 0; i < jugtitulos.size(); i++){
                for(int j = 0; j < recorrercasillas.size(); j++){
                     if((jugtitulos.get(i) == recorrercasillas.get(j).getTitulo()) && (jugtitulos.get(i).getHipotecada() == estadoHipoteca)  )
                        titujug.add(recorrercasillas.get(j).getNumeroCasilla()) ;
                       // contador++;
           }
       }          
            
        return titujug;
            
        
    }
    

    public void obtenerRanking(){
       //Comparator<? super Jugador> c = null;
       Collections.sort(jugadores);
       
    }

    public int obtenerSaldoJugadorActual(){
        //prac3
        return jugadorActual.getSaldo();
    }

    private void salidaJugadores(){
        //prac3
        
        for(Jugador jug:jugadores){
            jug.setCasillaActual(tablero.obtenerCasillaNumero(0));
        }        
        Random genprimer = new Random();
        jugadorActual = jugadores.get(genprimer.nextInt(jugadores.size()));     
              
        setEstadoJuego(JA_PREPARADO);
    }

    private void setCartaActual(Sorpresa cartaActual){ //implementar p2
            this.cartaActual=cartaActual;
    }
    
    private void setEstadoJuego(EstadoJuego ej){
    //practica 3
        this.estadoJuego = ej;
    
    }
    
    public void siguienteJugador(){
        //prac3
        int posjugctual = jugadores.indexOf(jugadorActual);       
        
        if(posjugctual == jugadores.size()-1){
            jugadorActual = jugadores.get(0);
        }
        else{
            jugadorActual = jugadores.get(posjugctual+1);
        }
        
        if(jugadorActual.getEncarcelado()){
            estadoJuego = JA_ENCARCELADOCONOPCIONDELIBERTAD;
        
        } else{
            estadoJuego = JA_PREPARADO;
        }       
    }

    int tirarDado(){
        //pract3
        return dado.tirar();
    }

    public void venderPropiedad(int numeroCasilla){
        //pract3
        Casilla casilla = tablero.obtenerCasillaNumero(numeroCasilla);
        jugadorActual.venderPropiedad(casilla);
        estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
        
}
    
    public boolean jugadorActualEnCalleLibre(){
        //p5
        return jugadorActual.estoyEnCalleLibre();
    }
    
    public boolean jugadorActualEncarcelado(){
        //p5
        return jugadorActual.getEncarcelado();
    }    
    

    public String toString(){
    
    return "El juego Qutetet se compone de : \n" +
            "********************************Mazo: ********************************\n" +
            mazo +
            "********************************Tablero: ********************************\n" +
            tablero +
            "********************************Jugadores********************************\n" + 
            jugadores
            ;
    }
}
