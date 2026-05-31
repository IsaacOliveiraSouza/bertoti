package controller;

import model.*;
import view.EmpresaView;

public class EmpresaController {

    private Departamento empresa;
    private EmpresaView view;

    public EmpresaController(EmpresaView view) {
        this.view = view;
        this.empresa = construirEstrutura();
    }

    // Monta a hierarquia Composite com Funcionarios usando diferentes Strategies
    private Departamento construirEstrutura() {
        // --- Departamento de Desenvolvimento ---
        Departamento dev = new Departamento("Desenvolvimento");
        dev.adicionar(new Funcionario("Alice",   8000, new CalculoCLT()));
        dev.adicionar(new Funcionario("Bob",    10000, new CalculoPJ()));
        dev.adicionar(new Funcionario("Carlos",  1500, new CalculoEstagiario()));

        // --- Departamento de RH ---
        Departamento rh = new Departamento("RH");
        rh.adicionar(new Funcionario("Diana",  6000, new CalculoCLT()));
        rh.adicionar(new Funcionario("Eduardo",7000, new CalculoPJ()));

        // --- Empresa (raiz Composite) ---
        Departamento empresa = new Departamento("Empresa XYZ");
        empresa.adicionar(dev);
        empresa.adicionar(rh);

        return empresa;
    }

    public void exibirEstrutura() {
        view.exibirHierarquia(empresa);
    }

    public void exibirFolhaTotal() {
        view.exibirTotalFolha(empresa);
    }

    // Strategy: troca o regime de um funcionário em tempo de execução
    public void trocarRegimeFuncionario(String nomeDept, String nomeFuncionario, EstrategiaCalculo novaEstrategia) {
        for (Componente c : empresa.getComponentes()) {
            if (c instanceof Departamento dept && dept.getNome().equals(nomeDept)) {
                for (Componente filho : dept.getComponentes()) {
                    if (filho instanceof Funcionario f && f.getNome().equals(nomeFuncionario)) {
                        f.setEstrategia(novaEstrategia);
                        view.exibirFuncionarioAtualizado(f);
                        return;
                    }
                }
            }
        }
        System.out.println("Funcionário não encontrado.");
    }

    // Observer: envia comunicado a todos inscritos de um departamento
    public void enviarComunicado(String nomeDept, String mensagem) {
        for (Componente c : empresa.getComponentes()) {
            if (c instanceof Departamento dept && dept.getNome().equals(nomeDept)) {
                view.exibirComunicadoEnviado(dept, mensagem);
                dept.enviarComunicado(mensagem);
                return;
            }
        }
        System.out.println("Departamento não encontrado.");
    }
}
