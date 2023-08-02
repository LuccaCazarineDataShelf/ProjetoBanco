import java.util.Iterator;

public class ListaLigadaIterator implements Iterator<Pessoa> {
    private ListaLigada.No atual;

    public ListaLigadaIterator(ListaLigada.No primeiro) {
        atual = primeiro;

    }

    public boolean hasNext(){
        return atual != null;
    }
    public Pessoa next(){
        Pessoa pessoa = atual.pessoa;
        atual = atual.proximo;
        return pessoa;
    }
}
