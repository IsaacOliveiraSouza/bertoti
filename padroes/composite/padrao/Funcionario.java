public class Funcionario implements Componente {
    private String nome;
    private double salario;

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Funcionário: " + nome + " | Salário: R$" + salario);
    }
}
