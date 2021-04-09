package Algoritmos;

import ClassesControle.Nodo;
import Funcoes.Transformacoes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HillClimb {
    
    private Nodo inicio;
    private List<Nodo> resultadoCaminho;
    private int profundidade;
    private int passos;
    private List<int[]>listavisitados;
    private int[] dest;
    public HillClimb(int lista[],int dest[])
    {
        this.dest = dest;
        passos = 1;
        this.inicio = new Nodo(lista);
        inicio.setValor(Transformacoes.calculaDistancia(inicio,dest));
        listavisitados = new ArrayList();
    }

    public int getPassos() {
        return passos;
    }
    
    public void resolver()
    {
        List<Nodo> resultadoCaminhoAux = new ArrayList();
        resultadoCaminho = null;
        hillClimb(inicio,inicio,0,resultadoCaminhoAux);
        profundidade = resultadoCaminho.size();
        System.out.println("");
        if(resultadoCaminho == null)
        {
            //Mensagem de erro
        }
    }
    
    private Nodo hillClimb(Nodo lanterior, Nodo atual,int auxprof,List<Nodo> resultadoCaminhoAux)
    {
        Nodo result = atual;
        List<Nodo> del = new ArrayList();
        List<Nodo> caminhos = getCaminhos(atual);
        passos += caminhos.size();
        resultadoCaminhoAux.add(atual);
        int i = 0;
        listavisitados.add(atual.getLista());
       
        for (Nodo caminho : caminhos) 
        {
            caminho.setValor(Transformacoes.calculaDistancia(caminho,dest));
            if(contem(caminho.getLista()))
                del.add(caminho);
        }
        
        for (Nodo d : del) 
           caminhos.remove(d);
        caminhos.sort(Comparator.comparing(Nodo::getValor));
        boolean b = true;
        
        for (int j = 0; j < caminhos.size() && b; j++) 
        {
            if(caminhos.get(j).getValor()==0)
            {
                auxprof++;
                result = caminhos.get(j);
                
                b = false;           
                resultadoCaminhoAux.add(caminhos.get(j));
            }
            else
            {
                auxprof++;
                result = hillClimb(atual,caminhos.get(j),auxprof,resultadoCaminhoAux);
                
                if(result.getValor() == 0)
                {
                    b = false;
                    //resultadoCaminhoAux.add(result);
                }
                else
                    resultadoCaminhoAux.remove(resultadoCaminhoAux.size()-1);
            }
        }
        if(resultadoCaminho == null)
            resultadoCaminho = resultadoCaminhoAux;
        else if(resultadoCaminho.get(resultadoCaminho.size()-1).getValor() > resultadoCaminhoAux.get(resultadoCaminhoAux.size()-1).getValor())
            resultadoCaminho = resultadoCaminhoAux;
            
        if(!b)
            resultadoCaminho = resultadoCaminhoAux;
        
        return result;
    }
    
    private int calculaDistancia(Nodo n)
    {
        int soma = 0, j = 0, k = 0;
        
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
            }
            
            k++;
            if(k == 3)
            {
                k = 0;
                j++;
            }
        }
        
        return soma;
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
        
        for(int i = 0; i < profundidade; i++)
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