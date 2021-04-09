package Algoritmos;

import ClassesControle.FilaPrioridade;
import ClassesControle.Nodo;
import Funcoes.Transformacoes;
import java.util.ArrayList;
import java.util.List;

public class AEstrela {
    
    private FilaPrioridade fp;
    private List<Nodo> resultadoCaminho;
    private int numvisitados;
    private List<Nodo> visitados;//lista dos nodos já visitados
    private List<int[]> listavisitados;
    private Nodo inicial;
    private int[] dest;
    public AEstrela(int[] lista,int[] dest)
    {
        fp = new FilaPrioridade();
        inicial = new Nodo(lista);
        this.dest = dest;
        inicial.setValor(Transformacoes.calculaDistancia(inicial,dest)+1);
        //fp.adicionar(inicial);
        visitados = new ArrayList();
        resultadoCaminho = new ArrayList();
        listavisitados = new ArrayList();
        numvisitados = 0;
        
    }
    
    public void resolver()
    {
        Nodo nodo = new Nodo();
        List<Nodo> temp = new ArrayList();
        boolean finished = false;
        
        visitados.add(inicial);
        listavisitados.add(inicial.getLista());
        inicial.adicionaCaminho(getVisitados().indexOf(inicial));
        fp.adicionar(inicial);
        
        int menor = 100;
        int add = 0;
        
        while(!fp.isEmpty() && !finished)
        {
            nodo = fp.remover();
            if(fp.isEmpty())
                System.out.println("0");
            numvisitados++;
            if(nodo.getValor() > nodo.getCaminho().size())
            {
                temp = Transformacoes.getCaminhos(nodo);
                for(Nodo n : temp)
                {
                    if(!contem(n.getLista()))
                    {
                        n.setCaminho(nodo.getCaminho());
                        n.setValor(Transformacoes.calculaDistancia(n,dest)+n.getCaminho().size()+1);
                        n.adicionaCaminho(visitados.size());
                        visitados.add(n);
                        listavisitados.add(n.getLista());
                        fp.adicionar(n);
                        add++;
                    }
                }
            }
            else
            {
                finished = true;
                calculaCaminhoSolucao(nodo.getCaminho());
            }
            
        }
        System.out.println(add+"  "+numvisitados);
        System.out.println("");
    }
    
    private void calculaCaminhoSolucao(List<Integer> lista)
    {
        resultadoCaminho.clear();
        for(int index : lista)
            resultadoCaminho.add(getVisitados().get(index));
    }
    
    public int getNumVisitas()
    {
        return getVisitados().size();
    }

    public int[][] getResultadoCaminho() {
        
        int[] caminho[] = new int[resultadoCaminho.size()][];
        System.out.println("TAMANHO: " + resultadoCaminho.size());
        for(int i = 0; i < resultadoCaminho.size(); i++)
            caminho[i] = resultadoCaminho.get(i).getLista();
        
        return caminho; 
    }
    
    private boolean contem(int[] vetor)
    {
        int i;
        boolean temp;
        for(int[] comp : listavisitados)
        {
            i = 0;
            while(i<10 && comp[i] == vetor[i])i++;
            
            temp = i == 10;
            if(temp)
                return true;
        }
        return false;
    }
    
    public List<Nodo> getVisitados() {
        return visitados;
    }
    
    public int getNumVisitados()
    {
        return numvisitados;
    }
    
    public int getProfundidade()
    {
        return resultadoCaminho.size();
    } 
}