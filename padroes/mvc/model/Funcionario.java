package model;

// Leaf (Composite) + Observer
public class Funcionario implements Componente, Inscrito {
    private String nome;
    private double salarioBase;
    private EstrategiaCalculo estrategia; // Strategy

    public Funcionario(String nome, double salarioBase, EstrategiaCalculo estrategia) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaCalculo estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaCalculo getEstrategia() {
        return estrategia;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double calcularSalario() {
        return estrategia.calcular(salarioBase);
    }

    @Override
    public void exibir(String indent) {
        System.out.printf("%sFuncionário: %-15s | Regime: %-30s | Líquido: R$ %.2f%n",
                indent, nome, estrategia.descricao(), calcularSalario());
    }

    // Observer
    @Override
    public void receberComunicado(String mensagem) {
        System.out.println("  [" + nome + "] recebeu: " + mensagem);
    }
}
