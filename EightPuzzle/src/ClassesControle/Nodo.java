package ClassesControle;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    
    private int valor;
    private int[] lista;
    private List<Integer> caminho;
    
    public Nodo() {
        lista = new int[10];
        caminho = new ArrayList();
    }

    public Nodo(int valor, int[] lista) {
        this.valor = valor;
        this.lista = lista;
        caminho = new ArrayList();
    }

    public Nodo(int[] lista) {
        this.lista = lista;
        caminho = new ArrayList();
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public List<Integer> getCaminho()
    {
        return caminho;
    }
    
    public void setCaminho(List<Integer> caminho )
    {
        this.caminho = new ArrayList(caminho);
    }
    
    public void adicionaCaminho(int index)
    {
        caminho.add(index);
    }

    public int[] getLista() {
        return lista;
    }

    public void setLista(int[] lista) {
        this.lista = lista;
    }
}