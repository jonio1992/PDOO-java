/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modeloqytetet;

import java.util.ArrayList;
import java.util.Scanner;
import static modeloqytetet.TipoSorpresa.*;
import static modeloqytetet.Sorpresa.*;
/**
 *
 * @author jonio
 * guia de bolsillo : https://www.oreilly.com/library/view/java-8-pocket/9781491901083/ch01.html
 * 
 */
public class PruebaQytetet {
    
    private static ArrayList<Sorpresa> mazoprueba;
    private static final Scanner in = new Scanner (System.in);
    
    // Método 1: Sorpresas que tienen un valor mayor que 0.
    private static ArrayList<Sorpresa> sorpresasValorMayor0(ArrayList<Sorpresa> mazo){
        System.out.println("\nInvoco a sorpresasValorMayor0 : \n");
        
        ArrayList<Sorpresa> arrayValor = new ArrayList();
        
        for(int i = 0; i < mazo.size(); i++){            
            if(mazo.get(i).getValor() > 0){
               arrayValor.add(mazo.get(i));
            }
        }        
        return arrayValor;
        
}
    // Método 2: Sorpresas de TipoSorpresa IRACASILLA.
    private static ArrayList<Sorpresa> sorpresastipoIrCasilla(ArrayList<Sorpresa> mazo){
        System.out.println("\nInvoco a sorpresastipoIrCasilla : \n");
        
        ArrayList<Sorpresa> arrayValor = new ArrayList();

        for(int i = 0; i < mazo.size(); i++){            
            if(mazo.get(i).getTipo() == IRACASILLA){
               arrayValor.add(mazo.get(i));
            }
        }        
        return arrayValor;
        
}
    // Método 3: Sorpresas del TipoSorpresa especificado en el argumento del método.
    private static ArrayList<Sorpresa> sorpresastipoArgumento(ArrayList<Sorpresa> mazo, TipoSorpresa Tsorpresa){
        System.out.println("\nInvoco a sorpresastipoArgumento de tipo "+ Tsorpresa +": \n");
        
        ArrayList<Sorpresa> arrayValor = new ArrayList();

        for(int i = 0; i < mazo.size(); i++){            
            if(mazo.get(i).getTipo() == Tsorpresa){
               arrayValor.add(mazo.get(i));
            }
        }        
        return arrayValor;
        
}
    
    //metodo p2
    public static ArrayList<String> getNombreJugadores() { //m�todo para pedir el nombre de los jugadores
        String lectura;
        boolean valido = true; 
        ArrayList<String> nombres = new ArrayList();
        do{
            System.out.print("Escribe el numero de jugadores: (de 2 a 4): ");
            lectura = in.nextLine();  //lectura de teclado
            if(Integer.parseInt(lectura)<2 || Integer.parseInt(lectura) >4){
                valido = false;
                System.out.print("Numero de jugadores no valido\n");
            }else{
                valido =true;
            }
        }while(!valido);
        for (int i = 1; i <= Integer.parseInt(lectura); i++) { //solicitud del nombre de cada jugador
          System.out.print("Nombre del jugador " + i + ": ");
          nombres.add (in.nextLine());
        }
        return nombres;
    }
    
    public static void pract1() {
    //p1
        //apartado 5.a
        Qytetet juego = Qytetet.getInstance();
        //juego.inicializarCartasSorpresa();
       // prueba.getMazo();
        
        //apartado 5.b
        for (int i = 0; i < juego.mazo.size(); i++){
            System.out.println("\nCon toString\n"+juego.mazo.get(i).toString());
            System.out.println("\nSin toString\n" + juego.mazo.get(i)); 
        }
        
        //apartado 5.d
        //imprimir metodo 1
        mazoprueba=sorpresasValorMayor0(juego.mazo);
        for (int i = 0; i < mazoprueba.size(); i++)
            System.out.println("imprimir < 0: \n" + mazoprueba.get(i).toString());
        //imprimir metodo 2
        mazoprueba=sorpresastipoIrCasilla(juego.mazo);
        for (int i = 0; i < mazoprueba.size(); i++)
            System.out.println("imprimir is a casilla: \n" + mazoprueba.get(i).toString());
        //imprimir metodo 3
        for (TipoSorpresa p : TipoSorpresa.values()){
            mazoprueba=sorpresastipoArgumento(juego.mazo, p);
            for (int i = 0; i < mazoprueba.size(); i++)
                System.out.println("imprimir de tipoSorpresa: "+p+"\n" + mazoprueba.get(i).toString());
        }
        
        
        //sesion 2
        Tablero tablero = juego.getTablero();
        System.out.println("\nTablero ----------------------------");
        System.out.println(tablero.toString()); 
    }
    public static void pract2() {
    //p2
    
    /*lectura automatica*/
    /* ArrayList<String> jugadores = new ArrayList();
        jugadores.add("Jug1");
        jugadores.add("Jug2");
        jugadores.add("Jug3");
    */  
   
    /*Lectura manual*/    
        ArrayList<String> jugadores = new ArrayList();
        jugadores = getNombreJugadores();  
    
        Qytetet qytetet = Qytetet.getInstance();
        qytetet.inicializarJuego(jugadores);
        System.out.print(qytetet);
    }
        
    public static void pract3() {
        //p3
        PruebaQytetet prueba = new PruebaQytetet();
        Qytetet juego = Qytetet.getInstance();
        ArrayList<String> nombres = prueba.getNombreJugadores();
        
        juego.inicializarJuego(nombres);
        
        System.out.println(juego.getJugadores());
        
        int desplazamiento = juego.tirarDado();
        System.out.println("+++++++++++++mi desplazamiento " + desplazamiento);
        System.out.println("El jugador " + juego.getJugadorActual().getNombre());
        juego.mover(4);
        juego.comprarTituloPropiedad();
        juego.edificarCasa(4);
        System.out.println(juego.obtenerCasillaJugadorActual());
        System.out.println(juego.getJugadorActual());
        juego.siguienteJugador();
        desplazamiento = juego.tirarDado();
        System.out.println("El jugador " + juego.getJugadorActual().getNombre() + " se desplaza: " + desplazamiento + " casillas.");
        juego.mover(4);
        System.out.println(juego.getJugadorActual());
        juego.siguienteJugador();
        juego.mover(3);
        System.out.println(juego.getJugadorActual());
        System.out.println(juego.getCartaActual());
        juego.aplicarSorpresa();
        juego.venderPropiedad(4);
        System.out.println(juego.getJugadorActual());
        juego.obtenerRanking();
        
        ArrayList<Jugador> jugadores = juego.getJugadores();
        
        for(Jugador player: jugadores)
            System.out.println(player);
    }
    
    public static void pract4() {
        /*Haz las mismas pruebas que en la práctica anterior.
        Para asegurarte de que se prueba la clase Especulador,
        coloca las cartas CONVERTIRME al principio del mazo 
        (sin barajar) y fuerza a que el jugador caiga en la 
        primera casilla SORPRESA. Otra posibilidad es convertir
        todos o algunos jugadores antes de terminar el método 
        inicializarJugadores de Qytetet.*/
        
        /*Hay un tipo de calle especial, calle social, de forma 
        que se crea un jardín público junto a cada casa al edificarla
        y un centro social por cada hotel al edificarlo. El precio 
        que un propietario debe pagar por edificar una casa con 
        jardín y un hotel con centro social será la mitad del precio
        de edificación especificado.*/
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    //pract1();
    //pract2();
    pract3();
    //pract4();
      
    
    }     
}
