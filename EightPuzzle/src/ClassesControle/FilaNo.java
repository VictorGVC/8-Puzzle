package ClassesControle;

public class FilaNo {
    
    private FilaNo prox;
    private FilaNo ant;
    private Nodo nodo;
    private int prioridade;
    
    public FilaNo(Nodo nodo, int prio)
    {
        prioridade = prio;
        this.nodo = nodo;
    }

    public FilaNo getProx() {
        return prox;
    }

    public void setProx(FilaNo prox) {
        this.prox = prox;
    }

    public FilaNo getAnt() {
        return ant;
    }

    public void setAnt(FilaNo ant) {
        this.ant = ant;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
}