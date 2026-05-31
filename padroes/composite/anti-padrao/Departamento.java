import java.util.ArrayList;
import java.util.List;


public class Departamento {
    private String nome;
    private List<Funcionario> funcionarios = new ArrayList<>();
    private List<Departamento> subDepartamentos = new ArrayList<>();

    public Departamento(String nome) {
        this.nome = nome;
    }

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public void adicionarSubDepartamento(Departamento d) {
        subDepartamentos.add(d);
    }

    public void exibirDetalhes() {
        System.out.println("Departamento: " + nome);
        // Loop separado para funcionários
        for (Funcionario f : funcionarios) {
            f.exibirDetalhes();
        }
        // Loop separado para sub-departamentos
        for (Departamento d : subDepartamentos) {
            d.exibirDetalhes();
        }
    }
}
