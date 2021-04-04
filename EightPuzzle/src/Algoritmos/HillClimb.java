/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import ClassesControle.Nodo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Heitor
 */
public class HillClimb {
    private Nodo inicio;
    private List<Nodo> resultadoCaminho;
    private int profundidade;
    
    public HillClimb(int lista[])
    {
        this.inicio = new Nodo(lista);
        inicio.setValor(calculaDistancia(inicio));
    }
    
    public void resolver()
    {
        List<Nodo> resultadoCaminhoAux = new ArrayList();
        resultadoCaminho = null;
        hillClimb(inicio,inicio,0,resultadoCaminhoAux);
        System.out.println("");
        if(resultadoCaminho == null)
        {
            //Mensagem de erro
        }
    }
    private Nodo hillClimb(Nodo lanterior, Nodo atual,int auxprof,List<Nodo> resultadoCaminhoAux)
    {
        Nodo result = new Nodo();
        List<Integer> del = new ArrayList();
        List<Nodo> caminhos = getCaminhos(atual);
        resultadoCaminhoAux.add(atual);
        auxprof++;
        int i = 0;
        caminhos.sort(Comparator.comparing(Nodo::getValor));
        for (Nodo caminho : caminhos) 
        {
            caminho.setValor(calculaDistancia(caminho));
            if(atual.getValor() <= caminho.getValor() || caminho.getLista() == lanterior.getLista())
                del.add(i);
            i++;
        }
        del.sort(Comparator.reverseOrder());
        for (int d : del) 
            caminhos.remove(d);
        
        boolean b = true;
        for (int j = 0; j < caminhos.size() && b; j++) 
        {
            if(caminhos.get(j).getValor()==0)
            {
                
                result = caminhos.get(j);
                
                b = false;
                resultadoCaminhoAux.add(caminhos.get(j));
                auxprof++;
                
            }
            else
            {
                
                result = hillClimb(atual,caminhos.get(j),auxprof,resultadoCaminhoAux);
                
                if(result.getValor() == 0)
                {
                    b = false;
                    //resultadoCaminhoAux.add(result);
                    auxprof++;
                    
                }
            }
        }
        if(!b)
        {
            profundidade = resultadoCaminhoAux.size();
            resultadoCaminho = resultadoCaminhoAux;
            
            
        }
        return result;
    }
    private int calculaDistancia(Nodo n)
    {
        int soma = 0,j=0,k=0;
        
        for (int i = 1; i < 10; i++) 
        {    
            switch(n.getLista()[i])
            {
                case 1:
                    soma += j+k;
                break;
                case 2:
                    soma += j+Math.abs(k-1);
                break;
                case 3:
                    soma += j+Math.abs(k-2);
                break;
                case 4:
                    soma += Math.abs(j-1)+k;
                break;
                case 5:
                    soma += Math.abs(j-1)+Math.abs(k-1);
                break;
                case 6:
                    soma += Math.abs(j-1)+Math.abs(k-2);
                break;
                case 7:
                    soma += Math.abs(j-2)+k;
                break;
                case 8:
                    soma += Math.abs(j-2)+Math.abs(k-1);
                break;
                case 9:
                    soma +=Math.abs(j-2)+Math.abs(k-2);
                break;
                
            }
            
            k++;
            if(k==3)
            {
                k = 0;
                j++;
            }
        }
        
        return soma;
    }
    private static List<Nodo> getCaminhos(Nodo n)
    {
        List<Nodo> caminhos = new ArrayList();
        
        switch(n.getLista()[0])
        {
            case 1:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),2)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),4)));
                    break;
            case 2:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),1)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),3)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    break;
            case 3:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),2)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),6)));
                    break;
            case 4:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),1)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),7)));
                    break;
            case 5:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),2)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),4)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),6)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),8)));
                    break;
            case 6:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),3)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),9)));
                    break;
            case 7:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),4)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),8)));
                    break;
            case 8:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),5)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),7)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),9)));
                    break;
            case 9:
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),6)));
                    caminhos.add(new Nodo(fazerMovimento(n.getLista(),8)));
                    break;
        }
        return caminhos;
    }
    private static int[] fazerMovimento(int lista[],int pos)
    { 
        int aux;
        int vazia = lista[0];
        int []alist = new int[lista.length];
        alist = lista.clone();
        aux = lista[vazia];
        alist[vazia] = lista[pos];
        alist[pos] = aux;
        alist[0] = pos;
        return alist;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public int[][] getResultadoCaminho() {
        int[] caminho[] = new int[profundidade][];
        
        for(int i =0;i<profundidade;i++)
        {
            caminho[i] = resultadoCaminho.get(i).getLista();
        }
        return caminho;
    }
    public int[] getPosResultadoCaminho(int j)
    {
        return resultadoCaminho.get(j).getLista();
    }
    
    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }
    public int[] getResultado()
    {
        return resultadoCaminho.get(resultadoCaminho.size()-1).getLista();
    }
}

