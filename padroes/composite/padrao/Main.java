// PADRÃO: o cliente usa apenas a interface Componente, sem distinção entre leaf e composite
public class Main {
    public static void main(String[] args) {
        Componente alice = new Funcionario("Alice", 5000);
        Componente bob = new Funcionario("Bob", 4000);
        Componente carlos = new Funcionario("Carlos", 6000);

        Departamento dev = new Departamento("Desenvolvimento");
        dev.adicionar(alice);
        dev.adicionar(bob);

        Departamento rh = new Departamento("RH");
        rh.adicionar(carlos);

        Departamento empresa = new Departamento("Empresa");
        empresa.adicionar(dev);
        empresa.adicionar(rh);

        // Trata toda a hierarquia de forma uniforme
        empresa.exibirDetalhes();
    }
}
