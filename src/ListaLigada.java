import java.util.Iterator;

public class ListaLigada implements Iterable<Pessoa> {
    public class No{
        Pessoa pessoa;
        public No proximo;

        public No(Pessoa pessoa){
            this.pessoa = pessoa;
            this.proximo = null;
        }
    }
        public Iterator<Pessoa> iterator(){
            return new ListaLigadaIterator(primeiro);
        }

    private No primeiro;
    private No ultimo;

    public No getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(No primeiro) {
        this.primeiro = primeiro;
    }

    public No getUltimo() {
        return ultimo;
    }

    public void setUltimo(No ultimo) {
        this.ultimo = ultimo;
    }

    public ListaLigada(){
        this.primeiro = null;
        this.ultimo = null;
    }

    public boolean estaVazia(){
        return primeiro == null;
    }
    public void adicionarPessoa(Pessoa pessoa){
        No novoNo = new No(pessoa);
            if(estaVazia()){
                primeiro = novoNo;
                ultimo = novoNo;
            } else {
                ultimo.proximo = novoNo;
                ultimo = novoNo;
            }
    }
}
