/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesControle;

/**
 *
 * @author vicga
 */
public class Nodo {
    private int valor;
    private int[] lista;

    public Nodo() {
        lista = new int[10];
    }

    public Nodo(int valor, int[] lista) {
        this.valor = valor;
        this.lista = lista;
    }

    public Nodo(int[] lista) {
        this.lista = lista;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int[] getLista() {
        return lista;
    }

    public void setLista(int[] lista) {
        this.lista = lista;
    }
}
