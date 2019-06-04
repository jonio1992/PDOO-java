/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.Random;

/**
 *
 * @author jonio
 */
public class Dado {
    private static final Dado instance = new Dado();
    private int valor;
    // El constructor privado asegura que no se puede instanciar
    // desde otras clases
    private Dado() {
    // La implementación que corresponda.
    }
    public static Dado getInstance() {
        return instance;
    }
    
    int tirar(){
      /*  Random gennumerodado = new Random();
        return (gennumerodado.nextInt(6)+1);*/
      valor = (int) Math.floor(Math.random()*6+1);
        return valor;
    } 

    public int getValor() {//implementar p2
        return valor;
    }
    
}
