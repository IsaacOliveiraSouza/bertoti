import controller.EmpresaController;
import model.CalculoPJ;
import view.EmpresaView;

public class Main {
    public static void main(String[] args) {

        EmpresaView view = new EmpresaView();
        EmpresaController controller = new EmpresaController(view);

        // 1. Exibe a hierarquia completa (Composite)
        controller.exibirEstrutura();

        // 2. Exibe o total da folha
        controller.exibirFolhaTotal();

        // 3. Troca o regime do estagiário para CLT (Strategy em tempo de execução)
        System.out.println("\n--- Promovendo Carlos de Estagiário para CLT ---");
        controller.trocarRegimeFuncionario("Desenvolvimento", "Carlos", new CalculoPJ());

        // 4. Exibe estrutura atualizada
        controller.exibirEstrutura();

        // 5. Envia comunicado para o departamento de RH (Observer)
        controller.enviarComunicado("RH", "Reunião geral na sexta-feira às 14h!");

        // 6. Envia comunicado para o departamento de Desenvolvimento (Observer)
        controller.enviarComunicado("Desenvolvimento", "Deploy em produção cancelado esta semana.");
    }
}
