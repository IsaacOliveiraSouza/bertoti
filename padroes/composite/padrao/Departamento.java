import java.util.ArrayList;
import java.util.List;

public class Departamento implements Componente {
    private String nome;
    private List<Componente> componentes = new ArrayList<>();

    public Departamento(String nome) {
        this.nome = nome;
    }

    public void adicionar(Componente c) {
        componentes.add(c);
    }

    public void remover(Componente c) {
        componentes.remove(c);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Departamento: " + nome);
        for (Componente c : componentes) {
            c.exibirDetalhes(); // trata folhas e compostos de forma uniforme
        }
    }
}
