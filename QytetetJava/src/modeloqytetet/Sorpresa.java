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
public class Sorpresa {
    private String texto;
    private int valor;
    private TipoSorpresa tipo;
    

    public Sorpresa(String texto, int valor, TipoSorpresa tipo){
            this.texto=texto;
            this.valor=valor;
            this.tipo=tipo;
        }

    public String getTexto() {
        return texto;
    }

    public int getValor(){
        return valor;
    }

    public TipoSorpresa getTipo(){
           return tipo;
    }

    @Override
    public String toString() {
        return "Sorpresa {\n" + "texto=" + texto + ",\n valor=" + 
        Integer.toString(valor) + ",\n tipo=" + tipo + "\n}";
    }

}//clase
