/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistatextualqytetet;

import controladorqytetet.ControladorQytetet;
import controladorqytetet.OpcionMenu;
import java.util.ArrayList;
import java.util.Scanner;
import modeloqytetet.Qytetet;

/**
 *
 * @author jonio
 */
public class VistaTextualQytetet {
    static Qytetet modelo;
    static ControladorQytetet controlador;

    public VistaTextualQytetet() {
        modelo = Qytetet.getInstance();
        controlador = ControladorQytetet.getInstance();
    }
    
    public ArrayList<String> obtenerNombreJugadores(){
        ArrayList<String> nombres = new ArrayList();
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce el numero de jugadores: ");
        int n = sc.nextInt();
        if(n <= modelo.MAX_JUGADORES && n >= 2){
            String s;
            s = sc.nextLine();
            for (int i = 0 ; i < n ; i++ ){
                System.out.print("Nombre jugador " + i + ": ");
                s = sc.nextLine();
                nombres.add(s);
            }
        }
        return nombres;
    }
    
    public int elegirCasilla(int opcionMenu){
        
        ArrayList casillas = controlador.obtenerCasillasValidas(opcionMenu);               
        ArrayList<String> casillass = new ArrayList<>();
        
        if(casillas == null)
            return -1;
        else{
            System.out.print("\nIndique la CALLE que desea cambiar/vender: \n Calles: \n");
            
            for(int i = 0; i < casillas.size(); ++i){
                //System.out.print(casillas[i] + "entra aqui ");
                System.out.print(casillas.get(i) + " ");
                casillass.add(Integer.toString((int) casillas.get(i)));
                
            }
            
            return Integer.parseInt(this.leerValorCorrecto(casillass));
        }
    }
    
    public String leerValorCorrecto(ArrayList<String> valoresCorrectos){
        String orden = "";
        boolean correcto = false;
        Scanner sc = new Scanner(System.in);        
        
        while(!correcto){
            System.out.print("\nIntroduce tu orden: ");
            orden = sc.nextLine();
            
            for(int i = 0; i < valoresCorrectos.size() && !correcto; ++i){
                if(orden.equals(valoresCorrectos.get(i)))
                    correcto = true;
            }
            
            if(!correcto)
                System.out.println("Orden no válida, vuelve a intentarlo.");
        }
        
        return orden;
    }
    
    public int elegirOperacion(){
        ArrayList<Integer> op = controlador.obtenerOperacionesJuegoValidas();
        ArrayList<String> ops = new ArrayList<>();
        
        System.out.print("\nÓrdenes disponibles: ");
        for(int num: op){
            System.out.print(OpcionMenu.values()[num] + "(" + num + ")" + " ");
            ops.add(Integer.toString(num));
        }
        
        
        return Integer.parseInt(this.leerValorCorrecto(ops));
    }
    
    public static void main(String []args){
        VistaTextualQytetet ui = new VistaTextualQytetet();
        controlador.setNombreJugadores(ui.obtenerNombreJugadores());
        int operacionElegida, casillaElegida = 0;
        boolean necesitaElegirCasilla;
        
        do{
            operacionElegida = ui.elegirOperacion();
            necesitaElegirCasilla = controlador.necesitaElegirCasilla(operacionElegida);
            
            if (necesitaElegirCasilla)
                casillaElegida = ui.elegirCasilla(operacionElegida);
            
            if (!necesitaElegirCasilla || casillaElegida >= 0)
                System.out.println(controlador.realizarOperacion(operacionElegida,casillaElegida));
        }while(true);
    }
    
}
