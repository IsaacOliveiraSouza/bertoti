package view;

import model.Componente;
import model.Departamento;
import model.Funcionario;

public class EmpresaView {

    public void exibirHierarquia(Componente raiz) {
        System.out.println("\n========== ESTRUTURA DA EMPRESA ==========");
        raiz.exibir("");
        System.out.println("==========================================\n");
    }

    public void exibirFuncionarioAtualizado(Funcionario f) {
        System.out.printf(">>> Regime de '%s' atualizado para: %s | Novo líquido: R$ %.2f%n",
                f.getNome(), f.getEstrategia().descricao(), f.calcularSalario());
    }

    public void exibirComunicadoEnviado(Departamento d, String mensagem) {
        System.out.println("\n>>> Comunicado enviado ao departamento '" + d.getNome() + "': " + mensagem);
    }

    public void exibirTotalFolha(Componente raiz) {
        System.out.printf("%nFolha de pagamento total: R$ %.2f%n", raiz.calcularSalario());
    }
}
