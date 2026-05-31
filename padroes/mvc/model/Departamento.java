package model;

import java.util.ArrayList;
import java.util.List;

// Composite (Composite Pattern) + Subject (Observer Pattern)
public class Departamento implements Componente {
    private String nome;
    private List<Componente> componentes = new ArrayList<>(); // Composite
    private List<Inscrito> inscritos = new ArrayList<>();     // Observer

    public Departamento(String nome) {
        this.nome = nome;
    }

    // --- Composite ---
    public void adicionar(Componente c) {
        componentes.add(c);
        // Se for um Funcionario, inscreve automaticamente nas notificações
        if (c instanceof Inscrito) {
            inscritos.add((Inscrito) c);
        }
    }

    public void remover(Componente c) {
        componentes.remove(c);
        if (c instanceof Inscrito) {
            inscritos.remove(c);
        }
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double calcularSalario() {
        return componentes.stream().mapToDouble(Componente::calcularSalario).sum();
    }

    @Override
    public void exibir(String indent) {
        System.out.println(indent + "Departamento: " + nome
                + String.format(" | Folha total: R$ %.2f", calcularSalario()));
        for (Componente c : componentes) {
            c.exibir(indent + "  ");
        }
    }

    // --- Observer ---
    public void enviarComunicado(String mensagem) {
        System.out.println("\n[Comunicado para " + nome + "]: " + mensagem);
        for (Inscrito i : inscritos) {
            i.receberComunicado(mensagem);
        }
    }
}
