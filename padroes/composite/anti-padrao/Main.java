public class Main {
    public static void main(String[] args) {
        Funcionario alice = new Funcionario("Alice", 5000);
        Funcionario bob = new Funcionario("Bob", 4000);
        Funcionario carlos = new Funcionario("Carlos", 6000);

        Departamento dev = new Departamento("Desenvolvimento");
        dev.adicionarFuncionario(alice);
        dev.adicionarFuncionario(bob);

        Departamento rh = new Departamento("RH");
        rh.adicionarFuncionario(carlos);

        Departamento empresa = new Departamento("Empresa");
        empresa.adicionarSubDepartamento(dev);
        empresa.adicionarSubDepartamento(rh);

        // Problema: não é possível tratar Funcionario e Departamento de forma uniforme
        empresa.exibirDetalhes();
    }
}
