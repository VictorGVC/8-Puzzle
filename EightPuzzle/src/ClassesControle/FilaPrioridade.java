/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesControle;

/**
 *
 * @author Heitor
 */
public class FilaPrioridade {
    FilaNo inicio;
    FilaNo fim;
    public FilaPrioridade()
    {
        inicio = fim = null;
    }
    public void adicionar(Nodo nodo)
    {
        FilaNo filano = new FilaNo(nodo,nodo.getValor());
        if(inicio == null)
        {
            filano.setAnt(null);
            filano.setProx(null);
            inicio = fim = filano;
            
        }
        else
        {
            FilaNo aux = fim;
            while(aux != null && filano.getPrioridade() < aux.getPrioridade())
            {
                aux = aux.getAnt();
            }
            if(aux == null)
            {
                filano.setAnt(null);
                filano.setProx(inicio);
                inicio.setAnt(filano);
                inicio = filano;
                
            }
            else
            {
                if(aux == fim)
                {
                    filano.setAnt(aux);
                    filano.setProx(null);
                    aux.setProx(filano);
                    fim = filano;
                }
                else
                {
                    
                    filano.setAnt(aux);
                    filano.setProx(aux.getProx());
                    aux.setProx(filano);
                    filano.getProx().setAnt(filano);
                }
            }
        }
    }
    public Nodo remover()
    {
        Nodo aux = inicio.getNodo();
        inicio = inicio.getProx();
        
        if(inicio == null)
            fim = null;
        else
        {
            inicio.setAnt(null);
        }
        return aux;
    }
    public boolean isEmpty()
    {
        return inicio == null;
    }
    
}
